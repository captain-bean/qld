package com.marshall.benjy.qld.core.engine.render.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DirectionalLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.PointLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

import static com.badlogic.gdx.Gdx.gl;

public class ParticleShader extends QLDShader{

    private ShaderProgram program;
    private Texture noTexture;

    private int worldMatrix,projectionMatrix,viewPosition;

    private RenderContext context;

    @Override
    public void init() { //TODO write shader
        String vert = Gdx.files.internal("Shaders/particle.vert").readString();
        String frag = Gdx.files.internal("Shaders/particle.frag").readString();
        noTexture = new Texture("Textures/default.png");


        program = new ShaderProgram(vert, frag);
        if (!program.isCompiled())
            throw new GdxRuntimeException(program.getLog());
        SHADER_ID = program.getHandle();


        worldMatrix = program.getUniformLocation("worldMatrix");
        projectionMatrix = program.getUniformLocation("projectionMatrix");
        viewPosition = program.getUniformLocation("viewPos");
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
        program.setUniformMatrix(worldMatrix, renderable.worldTransform);

        TextureAttribute diffuseTex = (TextureAttribute) renderable.material.get(TextureAttribute.Diffuse);
        Texture texture = diffuseTex.textureDescription.texture;
        gl.glActiveTexture(GL20.GL_TEXTURE1);
        texture.bind();
        program.setUniformi("hasTexture", 1);


        //bindLights(renderable);


    }

    @Override
    public void end() {

    }

    @Override
    public void dispose() {
        program.dispose();
    }

    @Override
    public int compareTo(Shader other) {
        return 0;
    }

    @Override
    public boolean canRender(Renderable instance) {
        return true;
    }

    private void bindLights(Renderable renderable) {
        Color colorLightAmb = new Color(0, 0, 0, 0);

        if (renderable.environment.has(ColorAttribute.AmbientLight)) {
            colorLightAmb = ((ColorAttribute) (renderable.environment.get(ColorAttribute.AmbientLight))).color;
        }
        if (renderable.environment.has(PointLightsAttribute.Type)) {
            Array<PointLight> lights = ((PointLightsAttribute) renderable.environment
                    .get(PointLightsAttribute.Type)).lights;


            program.setUniformi("pointLightsSize", lights.size);


            for (int i = 0; i < lights.size; i++) {
                float linear = 4.7f / lights.get(i).intensity;
                float quadratic = 3.5f * linear + .02f * linear;
                program.setUniformf("pointLights[" + i + "].position", lights.get(i).position);
                program.setUniformf("pointLights[" + i + "].diffuse", lights.get(i).color.r, lights.get(i).color.g, lights.get(i).color.b);

                program.setUniformf("pointLights[" + i + "].ambient", lights.get(i).color.r * .2f, lights.get(i).color.g * .2f, lights.get(i).color.b * .2f);
                program.setUniformf("pointLights[" + i + "].specular", lights.get(i).color.r, lights.get(i).color.g, lights.get(i).color.b);
                program.setUniformf("pointLights[" + i + "].constant", 1f);
                program.setUniformf("pointLights[" + i + "].linear", linear);
                program.setUniformf("pointLights[" + i + "].quadratic", quadratic);
                program.setUniformf("pointLights[" + i + "].intensity", lights.get(i).intensity);
            }
        }

        if (renderable.environment.has(DirectionalLightsAttribute.Type)) {
            Array<DirectionalLight> dirlights = ((DirectionalLightsAttribute) renderable.environment
                    .get(DirectionalLightsAttribute.Type)).lights;

            program.setUniformi("dirLightsSize", dirlights.size);
            for (int i = 0; i < dirlights.size; i++) {
                program.setUniformf("dirLights[" + i + "].direction", dirlights.get(i).direction.x, dirlights.get(i).direction.y, dirlights.get(i).direction.z);
                program.setUniformf("dirLights[" + i + "].diffuse", dirlights.get(i).color.r, dirlights.get(i).color.g, dirlights.get(i).color.b);

                program.setUniformf("dirLights[" + i + "].ambient", colorLightAmb.r, colorLightAmb.g, colorLightAmb.b);
                program.setUniformf("dirLights[" + i + "].specular", dirlights.get(i).color.r, dirlights.get(i).color.g, dirlights.get(i).color.b);

            }
        }
    }

}
