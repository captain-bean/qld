package com.marshall.benjy.qld.core.game.render.opengl;

import com.marshall.benjy.qld.core.engine.render.ecs.component.UpdateComponent;
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

public class TileGroup extends QLDEntity {

	private static final Logger logger = LogManager.getLogger(TileGroup.class);
	private Level level;
	private Map<Position, QLDEntity> tileEntities = new HashMap<>();
	private List<Position> dirtyList = new ArrayList<>();
	private boolean masterDirty = true;
	private TileTypePaths tileTypes;

	public TileGroup(Level level) {
		this.level = level;
		this.tileTypes = new TileTypePaths();
		add(new UpdateComponent((delta) -> {
			update();
		}));
	}

	public void updateAllTiles() {
		for (int x = 0; x < level.getTiles().length; x++) {
			for (int z = 0; z < level.getTiles()[x].length; z++) {
				Tile workingTile = level.getTiles()[x][z];
				String modelPath = "Models/" + tileTypes.getTilePaths(workingTile);
				TileEntity tileInstance;
				if (tileEntities.containsKey(new Position(x,z))) {
					tileInstance = (TileEntity) tileEntities.get(new Position(x,z));
					tileInstance.setTileModel(modelPath);
					tileEntities.remove(new Position(x,z));
				} else {
					tileInstance = new TileEntity(modelPath);
					tileInstance.translate(x * Constants.SCALE, 0, z * Constants.SCALE);
					tileInstance.setShader(DefaultShader.SHADER_ID);
				}

				tileEntities.put(new Position(x,z),tileInstance);
			}
		}
	}

	public void update() {
		if(masterDirty) {
			try {
				updateAllTiles();
				masterDirty = false;
			} catch (Exception ex) {
				logger.error("Error while initializing level instances: ", ex);
				tileEntities.clear();
			}
		}

		if (!tileEntities.isEmpty()) {
			for(Position p : dirtyList) {
				updateTileEntity(p);
			}
			dirtyList.clear();
		}
	}

	private Collection<QLDEntity> getTileEntities() {
		return tileEntities.values();
	}

	public void markPositionDirty(Position position) {
		dirtyList.add(position);
	}

	public void markDirty() {
		masterDirty = true;
	}

	private void updateTileEntity(Position position){
		TileEntity tileInstance = (TileEntity) tileEntities.get(position);
		int x = position.getX();
		int z = position.getZ();
		Tile workingTile = level.getTiles()[x][z];
		String modelPath = "Models/" + tileTypes.getTilePaths(workingTile);
		tileInstance.setTileModel(modelPath);
		tileInstance.translate(x * Constants.SCALE, 0, z * Constants.SCALE);
	}

}
