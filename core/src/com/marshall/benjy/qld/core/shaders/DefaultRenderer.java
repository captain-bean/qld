package com.marshall.benjy.qld.core.shaders;

import java.util.List;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.marshall.benjy.qld.core.game.GameState;

public class DefaultRenderer {


	private String texturePath;
	private Environment environment;
	private Shader shader;
	private ModelBatch modelBatch;

	private PointLight light;
	
	public DefaultRenderer(ModelBatch modelBatch) {

		this.modelBatch = modelBatch;

		
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.2f, 0.2f, 0.2f, 1f));
		light = new PointLight().set(1.0f, 1.0f, 0.0f, 25f, 15f, 25f, 100f);
		environment.add(light);
		light = new PointLight().set(1.0f, 0.0f, 0f, -25f, 15f, 25f, 100f);
		environment.add(light);
		light = new PointLight().set(0.0f, 1.0f, 0f, 25f, 15f, -25f, 100f);
		environment.add(light);
		light = new PointLight().set(0.0f, 0.0f, 1f, -25f, 15f, -25f, 100f);
		environment.add(light);
		light = new PointLight().set(1.0f, 1.0f, 1f, 50f, 15f, 50f, 10f);
		environment.add(light);
		
		shader = new TestShader();
		shader.init();
	}

	public void render(GameState state, List<ModelInstance> instances) {
		update();

		modelBatch.render(instances, environment, shader);
	}

	private void update() {

		
	}
}
