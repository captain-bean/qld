package com.marshall.benjy.qld.core.game.render;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.GameObject;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.QLDEntity;
import com.marshall.benjy.qld.core.engine.render.shaders.DefaultShader;
import com.marshall.benjy.qld.core.engine.state.Constants;
import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.logic.control.LevelController;
import com.marshall.benjy.qld.core.game.render.tile.TileEntity;
import com.marshall.benjy.qld.core.game.render.tile.TileTypePaths;
import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.tile.Tile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Entity;

import java.util.*;

public class LevelRenderer {

	private static final Logger logger = LogManager.getLogger(LevelRenderer.class);
	private Level level;
	public Map<Position,QLDEntity> instances = new HashMap();
	private TileTypePaths tileTypes;

	public LevelRenderer(Level level) {
		this.level = level;
		this.tileTypes = new TileTypePaths();
	}

	public void updateInstances() {
		for (int x = 0; x < level.getTiles().length; x++) {
			for (int z = 0; z < level.getTiles()[x].length; z++) {
				Tile workingTile = level.getTiles()[x][z];
				String modelPath = "Models/" + tileTypes.getTilePaths(workingTile);
				if (modelPath == null) {
					logger.warn("Tile model not loaded, aborting draw");
					return;
				}
				TileEntity tileInstance;
				if(instances.containsKey(new Position(x,z))){
					tileInstance = (TileEntity) instances.get(new Position(x,z));
					tileInstance.setTileModel(modelPath);
					instances.remove(new Position(x,z));
				}else{
					tileInstance = new TileEntity(modelPath);
					tileInstance.translate(x * Constants.SCALE, 0, z * Constants.SCALE);
					tileInstance.setShader(DefaultShader.SHADER_ID);
				}


				instances.put(new Position(x,z),tileInstance);
			}
		}
	}

	public Collection<QLDEntity> getInstances() {
		if (!instances.isEmpty()) {
			return instances.values();
		}

		try {
			updateInstances();
			return instances.values();
		} catch (Exception ex) {
			System.out.println(ex);
			instances.clear();
			return Collections.emptyList();
		}
	}

	public void onTileUpdated(Position position){
		TileEntity tileInstance = (TileEntity) instances.get(position);
		int x = position.getX();
		int z = position.getZ();
		Tile workingTile = level.getTiles()[x][z];
		String modelPath = "Models/" + tileTypes.getTilePaths(workingTile);
		tileInstance.setTileModel(modelPath);
		tileInstance.translate(x * Constants.SCALE, 0, z * Constants.SCALE);
	}

}
