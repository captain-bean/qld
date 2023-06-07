package com.marshall.benjy.qld.core.game.render.tile;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.marshall.benjy.qld.core.game.state.tile.TileTypes;

public class TileTypeRenderer {

	private AssetManager assetManager;
	private TileTypes type;
	private String texturePath;
	private Model tileModel;

	public TileTypeRenderer(TileTypes type, String textureName, AssetManager assetManager) {
		this.type = type;
		this.texturePath = "Models/" + textureName;
		this.assetManager = assetManager;

		assetManager.load(texturePath, Model.class);
	}

	public ModelInstance getTileInstance() {
		if (tileModel != null) {
			return new ModelInstance(tileModel);
		}

		if (assetManager.isLoaded(texturePath)) {
			tileModel = assetManager.get(texturePath, Model.class);
		}

		return new ModelInstance(tileModel);
	}
}
