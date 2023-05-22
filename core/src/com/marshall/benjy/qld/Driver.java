package com.marshall.benjy.qld;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.ScreenUtils;

public class Driver extends ApplicationAdapter {
	public PerspectiveCamera cam;
	public Model model;
	public AssetManager assets;
	public ModelBatch modelBatch;
	public ArrayList<ModelInstance> instances = new ArrayList<ModelInstance>();
	public Environment environment;
	public CameraInputController camController;
	public boolean loading;
	
	
	
	public Tile[][] tiles = { //Simple map example 
			
		{Tile.Wood,		Tile.Wood,		Tile.Pavement,	Tile.Pavement,	Tile.Pavement},
		{Tile.Pavement,	Tile.Wood,		Tile.Pavement,	Tile.Pavement,	Tile.Pavement},
		{Tile.Pavement,	Tile.Wood,		Tile.Wood,		Tile.Pavement,	Tile.Pavement},
		{Tile.Pavement,	Tile.Pavement,	Tile.Wood,		Tile.Pavement,	Tile.Pavement},
		{Tile.Pavement,	Tile.Pavement,	Tile.Wood,		Tile.Pavement,	Tile.Pavement}
	
	};
	
	              
	enum Tile {
		Pavement("Plate_Sidewalk_01.obj"), Wood("Plate_Wood_01.obj");
		
		String path = "";
		
		Tile(String path){
			this.path = path;
		}
	}

			
			
	@Override
	public void create() {
		modelBatch = new ModelBatch();

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(10f, 10f, 10f);
		cam.lookAt(0, 0, 0);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();

		assets = new AssetManager();
		assets.load("Models/Plate_Pavement_01.obj", Model.class);
		assets.load("Models/Plate_Wood_01.obj", Model.class);
		loading = true;

		
		
		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);
	}

	private void doneLoading() {

		Model tilePavement = assets.get("Models/Plate_Pavement_01.obj", Model.class);
		
		Model tileWood = assets.get("Models/Plate_Wood_01.obj", Model.class);
		
		float scale = 3f;
		for (int x = 0; x < tiles.length; x++ ) {
			for (int z = 0; z < tiles[x].length; z++) {
				Model tile;
				if(tiles[x][z] == Tile.Pavement) {
					tile = tilePavement;
				}else {
					 tile = tileWood;
				}
				ModelInstance tileInstance = new ModelInstance(tile);
				tileInstance.transform.setToTranslation(x * scale, 0, z * scale);
				instances.add(tileInstance);
			}
		}
		loading = false;
	}

	@Override
	public void render() {
		if (loading && assets.update())
			doneLoading();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		camController.update();

		modelBatch.begin(cam);
		modelBatch.render(instances, environment);
		modelBatch.end();
		;
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
		instances.clear();
		assets.dispose();
	}
}
