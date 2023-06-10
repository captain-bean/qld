package com.marshall.benjy.qld.core.engine.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
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

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.graphics.GL20.*;
import static com.badlogic.gdx.graphics.GL20.GL_TEXTURE_2D;
import static com.badlogic.gdx.graphics.GL30.GL_RED;
import static com.badlogic.gdx.graphics.GL30.GL_RGBA16F;

public class MasterRenderer {

    long postprocessingEffects = 0;

    private boolean writeToFrameBuffer = true;
    private FrameBuffer frameBuffer;
    private SpriteBatch spriteBatch;
    private ModelRenderer modelRenderer;
    private Skin skin;
    private Stage stage;

    private Label fpsLabel;
    private ShaderProgram program;
    private static final int MAX_SPRITES = 100;
    public MasterRenderer(ModelRenderer renderer){
        String vertexShader = "#version 330 core\n"
                + "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
                + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
                + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
                + "uniform mat4 u_projTrans;\n" //
                + "varying vec4 v_color;\n" //
                + "varying vec2 v_texCoords;\n" //
                + "\n" //
                + "void main()\n" //
                + "{\n" //
                + "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
                + "   v_color.a = v_color.a * (255.0/254.0);\n" //
                + "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
                + "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
                + "}\n";
        String fragmentShader = "#version 330 core\n"
                + "#ifdef GL_ES\n" //
                + "#define LOWP lowp\n" //
                + "precision mediump float;\n" //
                + "#else\n" //
                + "#define LOWP \n" //
                + "#endif\n" //
                + "varying LOWP vec4 v_color;\n" //
                + "varying vec2 v_texCoords;\n" //
                + "uniform sampler2D u_texture;\n" //
                + "void main()\n"//
                + "{\n" //
                + "  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n" //
                + "}";

        program = new ShaderProgram(vertexShader, fragmentShader);
        spriteBatch = new SpriteBatch(500, program);

        modelRenderer = renderer;
        skin = new Skin(Gdx.files.internal("Skins/vhs/skin/vhs-ui.json"));
        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), true,true);

        fpsLabel = new Label(Gdx.graphics.getFramesPerSecond() + "", skin);
    }

    public void render(){

        if(writeToFrameBuffer){
            HdpiUtils.setMode(HdpiMode.Pixels);
            frameBuffer.begin();
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        modelRenderer.Render();
        stage = new Stage();
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

    public void enqueue(int shader, ModelInstance... instances){
        modelRenderer.enqueue(shader,instances);
    }
    public void enqueue(int shader, ModelInstance instance){
        modelRenderer.enqueue(shader,instance);
    }

    //TODO add 2D renderer implementation














    private void setupSSAO() {


        IntBuffer gPosition = BufferUtils.newIntBuffer(1);

        gl.glGenTextures(1, gPosition);
        gl.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA16F, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                0, GL_RGBA, GL_FLOAT, null);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        FloatBuffer ssaoKernel = BufferUtils.newFloatBuffer(64 * 3);
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

        IntBuffer noiseTexture = BufferUtils.newIntBuffer(1);
        gl.glGenTextures(1, noiseTexture);
        gl.glBindTexture(GL_TEXTURE_2D, noiseTexture.get(0));
        gl.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA16F, 4, 4, 0, GL_RGB, GL_FLOAT, ssaoKernel);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        IntBuffer ssaoFBO = BufferUtils.newIntBuffer(1);
        gl.glGenFramebuffers(1, ssaoFBO);
        gl.glBindFramebuffer(GL_FRAMEBUFFER, ssaoFBO.get(0));

        IntBuffer ssaoColorBuffer = BufferUtils.newIntBuffer(1);
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
