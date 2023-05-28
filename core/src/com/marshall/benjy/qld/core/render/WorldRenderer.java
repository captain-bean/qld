package com.marshall.benjy.qld.core.render;

import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.marshall.benjy.qld.core.Constants;
import com.marshall.benjy.qld.core.render.shaders.DefaultShader;

public class WorldRenderer {

	private Shader shader;
	private ModelBatch modelBatch;
	private Camera camera;
	private DevEnvironment world;
	
	public WorldRenderer(DevEnvironment world, ModelBatch modelBatch) {
		this.world = world;
		this.modelBatch = modelBatch;

		this.camera = new PerspectiveCamera(75, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		camera.position.set(Constants.SCALE * 3.25f, Constants.SCALE * 3, Constants.SCALE * 8);
		camera.lookAt(Constants.SCALE * 3, Constants.SCALE * -2, Constants.SCALE * 3);
		camera.near = Constants.SCALE * .001f;
		camera.far = Constants.SCALE * 50f;
		camera.update();
		
		shader = new DefaultShader();
		shader.init();
	}

	public void render(List<ModelInstance> instances) {
		modelBatch.render(instances, world.getEnvironment(), shader);
	}

	public void render(ModelInstance instance) {
		render(Collections.singletonList(instance));
	}

}
