package com.marshall.benjy.qld.core;

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

public class TestShader implements Shader {
	ShaderProgram program;
	Camera camera;
	RenderContext context;
	int u_projTrans;
	int u_worldTrans;
	int u_color;
	int u_lightPos;
	int u_lightColor;
	int u_lightPower;
	int u_viewPos;
	int u_shininess;
	int u_ambient;

	@Override
	public void init() {
		String vert = Gdx.files.internal("Shaders/test.vert").readString();
		String frag = Gdx.files.internal("Shaders/test.frag").readString();
		program = new ShaderProgram(vert, frag);
		if (!program.isCompiled())
			throw new GdxRuntimeException(program.getLog());
		u_projTrans = program.getUniformLocation("u_projTrans");
		u_worldTrans = program.getUniformLocation("u_worldTrans");
		u_lightPos = program.getUniformLocation("u_lightPos");
		u_lightColor = program.getUniformLocation("u_lightColor");
		u_lightPower = program.getUniformLocation("u_lightPower");
		u_viewPos = program.getUniformLocation("u_viewPos");
		u_shininess = program.getUniformLocation("u_shininess");
		u_color = program.getUniformLocation("u_color");
		u_ambient = program.getUniformLocation("u_ambient");
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
		program.setUniformMatrix(u_projTrans, camera.combined);
		context.setDepthTest(GL20.GL_LESS);
		context.setCullFace(GL20.GL_BACK);
		
	}

	@Override
	public void render(Renderable renderable) {
		program.setUniformMatrix(u_worldTrans, renderable.worldTransform);

		Material material = renderable.material;
		ColorAttribute diffColorAttribute = (ColorAttribute) material.get(ColorAttribute.Diffuse);

		System.out.println("Diffuse Color:" + diffColorAttribute.color.r + ", "+ diffColorAttribute.color.g + ", "+ diffColorAttribute.color.b + ", " + diffColorAttribute.color.a + "");
		program.setUniformf(u_color, diffColorAttribute.color.r, diffColorAttribute.color.g, diffColorAttribute.color.b,
				diffColorAttribute.color.a);
		

		ColorAttribute ambColorAttribute = (ColorAttribute) material.get(ColorAttribute.Ambient);
		System.out.println("Ambient Color:" + ambColorAttribute.color.r + ", "+ ambColorAttribute.color.g + ", "+ ambColorAttribute.color.b + ", " + ambColorAttribute.color.a + "");
		program.setUniformf(u_ambient, ambColorAttribute.color.r, ambColorAttribute.color.g, ambColorAttribute.color.b);
		
		
		
		FloatAttribute floatAttribute = ((FloatAttribute)material.get(FloatAttribute.Shininess));
		program.setUniformf(u_shininess, 60);
		System.out.println(floatAttribute.value);

		Array<PointLight> lights = ((PointLightsAttribute) renderable.environment
				.get(PointLightsAttribute.Type)).lights;
		if (lights.size > 0) {
			program.setUniformf(u_lightPos, lights.get(0).position);
			program.setUniformf(u_lightColor, lights.get(0).color.r, lights.get(0).color.g, lights.get(0).color.b);
			program.setUniformf(u_lightPower, lights.get(0).intensity);
			
		}
		
		program.setUniformf(u_viewPos, camera.position);
		renderable.meshPart.render(program);
		
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
