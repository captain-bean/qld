package com.marshall.benjy.qld.core.game.render.opengl;

import com.marshall.benjy.qld.core.engine.render.ecs.entity.QLDEntity;
import com.marshall.benjy.qld.core.engine.render.shaders.DefaultShader;
import com.marshall.benjy.qld.core.engine.state.Constants;
import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.render.opengl.tile.TileEntity;
import com.marshall.benjy.qld.core.game.render.opengl.tile.TileTypePaths;
import com.marshall.benjy.qld.core.game.state.datatype.Level;
import com.marshall.benjy.qld.core.game.state.datatype.tile.Tile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class LevelContainer {

	private static final Logger logger = LogManager.getLogger(LevelContainer.class);
	private Level level;
	public Map<Position,QLDEntity> instances = new HashMap<>();
	private TileTypePaths tileTypes;

	public LevelContainer(Level level) {
		this.level = level;
		this.tileTypes = new TileTypePaths();
	}

	public void updateInstances() {
		for (int x = 0; x < level.getTiles().length; x++) {
			for (int z = 0; z < level.getTiles()[x].length; z++) {
				Tile workingTile = level.getTiles()[x][z];
				String modelPath = "Models/" + tileTypes.getTilePaths(workingTile);
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
			logger.error("Error while updating level instances: ", ex);
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
