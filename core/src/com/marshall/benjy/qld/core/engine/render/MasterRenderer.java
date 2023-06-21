package com.marshall.benjy.qld.core.engine.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.HdpiMode;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.render.shaders.DefaultShader;
import com.marshall.benjy.qld.core.engine.render.shaders.QLDShaderProvider;
import com.marshall.benjy.qld.core.engine.state.Constants;
import com.marshall.benjy.qld.core.game.Application;
import com.marshall.benjy.qld.core.game.QLDConfig;
import net.mgsx.gltf.loaders.glb.GLBAssetLoader;
import net.mgsx.gltf.loaders.glb.GLBLoader;
import net.mgsx.gltf.loaders.gltf.GLTFLoader;
import net.mgsx.gltf.scene3d.attributes.PBRCubemapAttribute;
import net.mgsx.gltf.scene3d.attributes.PBRTextureAttribute;
import net.mgsx.gltf.scene3d.lights.DirectionalLightEx;
import net.mgsx.gltf.scene3d.scene.Scene;
import net.mgsx.gltf.scene3d.scene.SceneAsset;
import net.mgsx.gltf.scene3d.scene.SceneManager;
import net.mgsx.gltf.scene3d.scene.SceneSkybox;
import net.mgsx.gltf.scene3d.utils.IBLBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.Gdx.gl30;
import static com.badlogic.gdx.graphics.GL20.*;
import static com.badlogic.gdx.graphics.GL20.GL_TEXTURE_2D;
import static com.badlogic.gdx.graphics.GL30.GL_RED;
import static com.badlogic.gdx.graphics.GL30.GL_RGBA16F;

public class MasterRenderer {

    private static final Logger logger = LogManager.getLogger(MasterRenderer.class);
    private static final boolean USE_GLTF_RENDERER = true;

    long postprocessingEffects = 0;

    private boolean writeToFrameBuffer = true;
    private FrameBuffer frameBuffer;
    private SpriteBatch spriteBatch;
    private ModelRenderer modelRenderer;
    private Skin skin;
    private Stage stage;

    private Label fpsLabel;
    private ShaderProgram program;

    private IntBuffer noiseTexture,ssaoFBO,ssaoColorBuffer,gPosition;
    private FloatBuffer ssaoKernel;



    //GLTF RENDERER
    private SceneManager sceneManager;
    private SceneAsset sceneAsset;
    private Scene scene;
    private PerspectiveCamera camera;
    private Cubemap diffuseCubemap;
    private Cubemap environmentCubemap;
    private Cubemap specularCubemap;
    private Texture brdfLUT;
    private float time;
    private SceneSkybox skybox;
    private DirectionalLightEx light;




    private static final int MAX_SPRITES = 100;

    public MasterRenderer(ModelRenderer renderer){
        if(USE_GLTF_RENDERER){
            ShaderProgram.prependVertexCode = "#version 330 core\n";
            ShaderProgram.prependFragmentCode = "#version 330 core\n";
            sceneManager = new SceneManager();
            ShaderProgram.prependVertexCode = "";
            ShaderProgram.prependFragmentCode = "";

            sceneAsset = new GLBLoader().load(Gdx.files.internal("Models/GLTF format/Alien Slime.glb"));
            //sceneAsset = new GLTFLoader().load(Gdx.files.internal("Models/GLTF format/blockDirt.gltf"));


            scene = new Scene(sceneAsset.scene);
            scene.modelInstance.transform.scale(Constants.SCALE,Constants.SCALE,Constants.SCALE);
            sceneManager.addScene(scene);
            sceneManager.environment = DevEnvironment.instance();

            //TODO find way to remove? - possibly unneeded
            light = new DirectionalLightEx();
            light.direction.set(1, -3, 1).nor();
            light.color.set(Color.WHITE);
            sceneManager.environment.add(light);


            // setup quick IBL (image based lighting)
            IBLBuilder iblBuilder = IBLBuilder.createOutdoor(light);
            environmentCubemap = iblBuilder.buildEnvMap(1024);
            diffuseCubemap = iblBuilder.buildIrradianceMap(256);
            specularCubemap = iblBuilder.buildRadianceMap(10);
            iblBuilder.dispose();

            // This texture is provided by the library, no need to have it in your assets.
            Texture brdfLUT = new Texture(Gdx.files.classpath("net/mgsx/gltf/shaders/brdfLUT.png"));

            sceneManager.setAmbientLight(0f);
            sceneManager.environment.set(new PBRTextureAttribute(PBRTextureAttribute.BRDFLUTTexture, brdfLUT));
            sceneManager.environment.set(PBRCubemapAttribute.createSpecularEnv(specularCubemap));
            sceneManager.environment.set(PBRCubemapAttribute.createDiffuseEnv(diffuseCubemap));


            // setup skybox
            skybox = new SceneSkybox(environmentCubemap);
            sceneManager.setSkyBox(skybox);
        }else {
            setSpriteBatch();

            modelRenderer = renderer;
            skin = new Skin(Gdx.files.internal("Skins/vhs/skin/vhs-ui.json"));
            frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true, true);

            fpsLabel = new Label(Gdx.graphics.getFramesPerSecond() + "", skin);
            stage = new Stage(new ScreenViewport(), spriteBatch);

            //setupSSAO();

        }

    }

    public void render(){
        if(USE_GLTF_RENDERER){
            renderGLTF();
            return;
        }


        if(writeToFrameBuffer){
            HdpiUtils.setMode(HdpiMode.Pixels);

            frameBuffer.begin();
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        modelRenderer.Render();
        fpsLabel.setText(Gdx.graphics.getFramesPerSecond());
        fpsLabel.setPosition(0,Gdx.graphics.getHeight() - fpsLabel.getHeight());
        stage.addActor(fpsLabel);
        stage.act();
        stage.draw();


        if(writeToFrameBuffer){
            frameBuffer.end();


            spriteBatch.begin();
            spriteBatch.draw(frameBuffer.getColorBufferTexture(),0,0,frameBuffer.getWidth(),frameBuffer.getHeight(),0,0,1,1);
            spriteBatch.end();
            HdpiUtils.setMode(HdpiMode.Logical);
        }

    }

    private void renderGLTF() {



        scene.modelInstance.transform.rotate(0,1,0,1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        sceneManager.update(Gdx.graphics.getDeltaTime());
        sceneManager.render();
    }

    public void enqueue(int shader, ModelInstance... instances){
        modelRenderer.enqueue(shader,instances);
    }
    public void enqueue(int shader, ModelInstance instance){
        if(!USE_GLTF_RENDERER)
            modelRenderer.enqueue(shader,instance);
    }

    //TODO add 2D renderer implementation

    private void setSpriteBatch(){
        ShaderProgram.prependVertexCode = "#version 330 core\n";
        ShaderProgram.prependFragmentCode = "#version 330 core\n";
        spriteBatch = new SpriteBatch();
        ShaderProgram.prependVertexCode = "";
        ShaderProgram.prependFragmentCode = "";

    }

    public void setCamera(DevCamera camera){
        if(USE_GLTF_RENDERER){
            sceneManager.setCamera(camera.getCamera());
            return;
        }
        modelRenderer.setCamera(camera);

    }












    private void setupSSAO() { //TODO get SSAO working


         gPosition = BufferUtils.newIntBuffer(1);

        gl.glGenTextures(1, gPosition);
        gl.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA16F, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                0, GL_RGBA, GL_FLOAT, null);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
         ssaoKernel = BufferUtils.newFloatBuffer(64 * 3);
        for (int i = 0; i < 64; ++i) {
            Vector3 sample = new Vector3(
                    (float) Math.random() * 2.0f - 1.0f,
                    (float) Math.random() * 2.0f - 1.0f,
                    (float) Math.random()
            );


            sample = sample.nor();
            //sample.scl((float)Math.random());

            float scale = (float) i / 64.0f;
            scale = lerp(0.1f, 1.0f, scale * scale);
            sample.scl(scale);
            ssaoKernel.put(sample.x);
            ssaoKernel.put(sample.y);
            ssaoKernel.put(sample.z);
        }

         noiseTexture = BufferUtils.newIntBuffer(1);
        gl.glGenTextures(1, noiseTexture);
        gl.glBindTexture(GL_TEXTURE_2D, noiseTexture.get(0));
        gl.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA16F, 4, 4, 0, GL_RGB, GL_FLOAT, ssaoKernel);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

         ssaoFBO = BufferUtils.newIntBuffer(1);
        gl.glGenFramebuffers(1, ssaoFBO);
        gl.glBindFramebuffer(GL_FRAMEBUFFER, ssaoFBO.get(0));

         ssaoColorBuffer = BufferUtils.newIntBuffer(1);
        gl.glGenTextures(1, ssaoColorBuffer);
        gl.glBindTexture(GL_TEXTURE_2D, ssaoColorBuffer.get(0));
        gl.glTexImage2D(GL_TEXTURE_2D, 0, GL_RED, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                0, GL_RED, GL_FLOAT, null);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        gl.glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, ssaoColorBuffer.get(0), 0);


    }

    private float lerp(float a, float b, float f) {
        return a + f * (b - a);
    }
}
