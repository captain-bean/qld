package com.marshall.benjy.qld.core.shaders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DirectionalLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.PointLightsAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.marshall.benjy.qld.Game;

public class TestShader implements Shader {
	ShaderProgram program;
	Camera camera;
	RenderContext context;

	int materialAmbient, materialDiffuse, materialSpecular, materialShiny;
	int projectionMatrix, worldMatrix,viewPosition;
	

    private static final Logger logger = LogManager.getLogger(TestShader.class);
    
	@Override
	public void init() {
		String vert = Gdx.files.internal("Shaders/test.vert").readString();
		String frag = Gdx.files.internal("Shaders/default.frag").readString();
		program = new ShaderProgram(vert, frag);
		if (!program.isCompiled())
			throw new GdxRuntimeException(program.getLog());
		
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
		this.camera = camera;
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

		Array<PointLight> lights = ((PointLightsAttribute) renderable.environment
				.get(PointLightsAttribute.Type)).lights;
		
		Color colorLightAmb = ((ColorAttribute) (renderable.environment.get(ColorAttribute.AmbientLight))).color;

		program.setUniformf("pointLightsSize", lights.size);
		
		float constant = 1f;
		for(int i = 0; i < lights.size; i++) {
			program.setUniformf("pointLights["+i+"].position", lights.get(i).position);
			program.setUniformf("pointLights["+i+"].diffuse", lights.get(i).color.r , lights.get(i).color.g , lights.get(i).color.b );
			program.setUniformf("pointLights["+i+"].ambient", colorLightAmb.r,colorLightAmb.g, colorLightAmb.b);
			program.setUniformf("pointLights["+i+"].specular", lights.get(i).color.r, lights.get(i).color.g, lights.get(i).color.b);
			program.setUniformf("pointLights["+i+"].constant", 1f);
			program.setUniformf("pointLights["+i+"].linear",  constant / (float)Math.pow(lights.get(i).intensity,2)); 
			program.setUniformf("pointLights["+i+"].quadratic", constant / (float)Math.pow(lights.get(i).intensity,3));
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
