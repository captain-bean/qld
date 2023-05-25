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

	private AssetManager assetManager;
	private Camera cam;
	private String texturePath;
	private Environment environment;
	private Shader shader;
	private ModelBatch modelBatch;

	private PointLight light;
	
	public DefaultRenderer(String path, AssetManager assetManager, Camera cam, ModelBatch modelBatch) {

		this.assetManager = assetManager;
		this.modelBatch = modelBatch;
		this.cam = cam;

		if (path != null) {
			this.texturePath = "Models/" + path;
			assetManager.load(texturePath, Model.class);
		}
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
		light = new PointLight().set(1.0f, 1.0f, 1.0f, -50f, 100f, 50f, 7000f);
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
