package com.marshall.benjy.qld.core.game.render.shaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.graphics.GL20.GL_TEXTURE_2D;

public class DefaultShader implements Shader {
	ShaderProgram program;
	RenderContext context;

	int materialAmbient, materialDiffuse, materialSpecular, materialShiny,materialTextureDiffuse;
	int projectionMatrix, worldMatrix,viewPosition;

	Texture noTexture;

    private static final Logger logger = LogManager.getLogger(DefaultShader.class);
    
	@Override
	public void init() {
		String vert = Gdx.files.internal("Shaders/Default.vert").readString();
		String frag = Gdx.files.internal("Shaders/Default.frag").readString();
		noTexture = new Texture("Textures/default.png");



		program = new ShaderProgram(vert, frag);
		if (!program.isCompiled())
			throw new GdxRuntimeException(program.getLog());


		materialTextureDiffuse = program.getUniformLocation("material.diffuseMap");
		program.setUniformi(materialTextureDiffuse,0);
		materialAmbient = program.getUniformLocation("material.ambient");
		materialDiffuse = program.getUniformLocation("material.diffuse");
		materialSpecular = program.getUniformLocation("material.specular");
		materialShiny = program.getUniformLocation("material.shininess");


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
		renderable.meshPart.render(program);

	}

	private void bindUniforms(Renderable renderable) {
		
		program.setUniformMatrix(worldMatrix, renderable.worldTransform);


		Material material = renderable.material;

		ColorAttribute diffColorAttribute = (ColorAttribute) material.get(ColorAttribute.Diffuse);
		program.setUniformf(materialDiffuse, diffColorAttribute.color.r, diffColorAttribute.color.g, diffColorAttribute.color.b,
				diffColorAttribute.color.a);

		ColorAttribute ambColorAttribute = (ColorAttribute) material.get(ColorAttribute.Ambient);
		program.setUniformf(materialAmbient, ambColorAttribute.color.r, ambColorAttribute.color.g, ambColorAttribute.color.b);

		ColorAttribute specColorAttribute = (ColorAttribute) material.get(ColorAttribute.Specular);
		program.setUniformf(materialSpecular, specColorAttribute.color.r, specColorAttribute.color.g, specColorAttribute.color.b);

		FloatAttribute floatAttribute = ((FloatAttribute)material.get(FloatAttribute.Shininess));
		program.setUniformf(materialShiny, floatAttribute.value);


		//Bind Texture if available otherwise use default texture
		if(material.has(TextureAttribute.Diffuse)) {
			TextureAttribute diffuseTex = (TextureAttribute) material.get(TextureAttribute.Diffuse);
			Texture texture = diffuseTex.textureDescription.texture;
			gl.glActiveTexture(GL20.GL_TEXTURE0);
			texture.bind();
			program.setUniformi("hasTexture",1);
		}else{

			gl.glActiveTexture(GL20.GL_TEXTURE0);
			noTexture.bind();

			program.setUniformi("hasTexture",0);
		}

		bindLights(renderable);

	}

	private void bindLights(Renderable renderable){
		Color colorLightAmb = ((ColorAttribute) (renderable.environment.get(ColorAttribute.AmbientLight))).color;

		if(renderable.environment.has(PointLightsAttribute.Type)) {
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

		if(renderable.environment.has(DirectionalLightsAttribute.Type)) {
			Array<DirectionalLight> dirlights = ((DirectionalLightsAttribute) renderable.environment
					.get(DirectionalLightsAttribute.Type)).lights;

			program.setUniformi("dirLightsSize", dirlights.size);
			for (int i = 0; i < dirlights.size; i++) {
				program.setUniformf("dirLights[" + i + "].direction", dirlights.get(i).direction.x,dirlights.get(i).direction.y,dirlights.get(i).direction.z);
				program.setUniformf("dirLights[" + i + "].diffuse", dirlights.get(i).color.r, dirlights.get(i).color.g, dirlights.get(i).color.b);

				program.setUniformf("dirLights[" + i + "].ambient", colorLightAmb.r, colorLightAmb.g, colorLightAmb.b);
				program.setUniformf("dirLights[" + i + "].specular", dirlights.get(i).color.r, dirlights.get(i).color.g, dirlights.get(i).color.b);

			}
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