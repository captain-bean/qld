package com.marshall.benjy.qld.core.game.render;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.GameObject;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.QLDEntity;
import com.marshall.benjy.qld.core.engine.state.Constants;
import com.marshall.benjy.qld.core.game.logic.control.LevelController;
import com.marshall.benjy.qld.core.game.render.tile.TileEntity;
import com.marshall.benjy.qld.core.game.render.tile.TileTypePaths;
import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.tile.Tile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LevelRenderer {

	private static final Logger logger = LogManager.getLogger(LevelRenderer.class);
	private Level level;
	public List<QLDEntity> instances = new ArrayList<>();
	private TileTypePaths tileRenderer;

	public LevelRenderer(Level level) {
		this.level = level;
		this.tileRenderer = new TileTypePaths();
	}

	public void updateInstances() {
		instances.clear();
		for (int x = 0; x < level.getTiles().length; x++) {
			for (int z = 0; z < level.getTiles()[x].length; z++) {
				Tile workingTile = level.getTiles()[x][z];
				String modelPath = "Models/" + tileRenderer.getTilePaths(workingTile);
				if (modelPath == null) {
					logger.warn("Tile model not loaded, aborting draw");
					return;
				}
				TileEntity tileInstance = new TileEntity(modelPath);
				tileInstance.translate(x * Constants.SCALE, 0, z * Constants.SCALE);
				instances.add(tileInstance);
			}
		}
	}

	public List<QLDEntity> getInstances() {
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
