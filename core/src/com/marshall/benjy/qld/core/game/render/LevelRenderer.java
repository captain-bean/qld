package com.marshall.benjy.qld.core.game.render;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.marshall.benjy.qld.core.game.Constants;
import com.marshall.benjy.qld.core.game.render.tile.TileRenderer;
import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.tile.Tile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LevelRenderer {

	private static final Logger logger = LogManager.getLogger(LevelRenderer.class);
	private Level level;
	public List<ModelInstance> instances = new ArrayList<>();
	private TileRenderer tileRenderer;

	public LevelRenderer(Level level, AssetManager assetManager) {
		this.level = level;
		this.tileRenderer = new TileRenderer(assetManager);

		this.level.addTileDestroyedListener((tile) -> updateInstances());
	}

	public void updateInstances() {
		instances.clear();
		for (int x = 0; x < level.getTiles().length; x++) {
			for (int z = 0; z < level.getTiles()[x].length; z++) {
				Tile workingTile = level.getTiles()[x][z];
				Model tileModel = tileRenderer.getTileModel(workingTile);
				if (tileModel == null) {
					logger.warn("Tile model not loaded, aborting draw");
					return;
				}
				ModelInstance tileInstance = new ModelInstance(tileModel);
				tileInstance.transform.setToTranslation(x * Constants.SCALE, 0, z * Constants.SCALE);
				instances.add(tileInstance);
			}
		}
	}

	public List<ModelInstance> getInstances() {
		if (!instances.isEmpty()) {
			return instances;
		}

		try {
			updateInstances();
			return instances;
		} catch (Exception ex) {
			System.out.println(ex);
			instances.clear();
			return Collections.emptyList();
		}
	}

}
