package com.marshall.benjy.qld.core.engine.render.shaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.attributes.*;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.nio.IntBuffer;

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.graphics.GL20.*;

public class SpriteShader extends QLDShader {

    private ShaderProgram program;
    private RenderContext context;

    private int materialAmbient, materialDiffuse, materialSpecular, materialShiny, materialTextureDiffuse;
    private int projectionMatrix, worldMatrix, viewPosition;

    private Texture noTexture;

    public static boolean wireFrame = false;
    private static final Logger logger = LogManager.getLogger(DefaultShader.class);

    @Override
    public void init() {
        String vert = Gdx.files.internal("Shaders/Default.vert").readString();
        String frag = Gdx.files.internal("Shaders/sprite.frag").readString();
        noTexture = new Texture("Textures/default.png");


        program = new ShaderProgram(vert, frag);
        if (!program.isCompiled())
            throw new GdxRuntimeException(program.getLog());
        SHADER_ID = program.getHandle();

        QLDShaderProvider.COMPILED_SHADERS.put(SHADER_ID,this);
        materialTextureDiffuse = program.getUniformLocation("spriteTexture");
        program.setUniformi(materialTextureDiffuse, 0);
        worldMatrix = program.getUniformLocation("worldMatrix");
        projectionMatrix = program.getUniformLocation("projectionMatrix");
        viewPosition = program.getUniformLocation("viewPos");
    }

    @Override
    public void dispose() {
        program.dispose();
    }

    @Override
    public void begin(Camera camera, RenderContext context) {
        this.context = context;
        program.bind();
        program.setUniformMatrix(projectionMatrix, camera.combined);
        program.setUniformf(viewPosition, camera.position);
        context.setDepthTest(GL20.GL_LESS);
        context.setCullFace(GL20.GL_BACK);

    }


    @Override
    public void render(Renderable renderable) {
        bindUniforms(renderable);
        if (wireFrame) {
            renderable.meshPart.mesh.render(program, GL20.GL_LINES);
        }
        renderable.meshPart.render(program);

    }

    private void bindUniforms(Renderable renderable) {

        program.setUniformMatrix(worldMatrix, renderable.worldTransform);

        Material material = renderable.material;
        //Bind Texture if available otherwise use default texture
        if (material.has(TextureAttribute.Diffuse)) {
            TextureAttribute diffuseTex = (TextureAttribute) material.get(TextureAttribute.Diffuse);
            Texture texture = diffuseTex.textureDescription.texture;
            gl.glActiveTexture(GL20.GL_TEXTURE0);
            texture.bind();
            program.setUniformi("hasTexture", 1);
        } else {

            gl.glActiveTexture(GL20.GL_TEXTURE0);
            noTexture.bind();
            program.setUniformi("hasTexture", 0);
        }



    }



    @Override
    public void end() {

    }

    @Override
    public int compareTo(Shader other) {
        return 0;
    }

    @Override
    public boolean canRender(Renderable instance) {
        return true;
    }
}
