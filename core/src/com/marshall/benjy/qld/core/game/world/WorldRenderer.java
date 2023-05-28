package com.marshall.benjy.qld.core.game.world;

import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.marshall.benjy.qld.core.game.GameState;
import com.marshall.benjy.qld.core.game.world.World;
import com.marshall.benjy.qld.core.shaders.DefaultShader;

public class WorldRenderer {

	private Shader shader;
	private ModelBatch modelBatch;
	private World world;
	
	public WorldRenderer(World world, ModelBatch modelBatch) {
		this.world = world;
		this.modelBatch = modelBatch;
		
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
