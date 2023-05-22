package com.marshall.benjy.qld.core.tile.renderer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.marshall.benjy.qld.core.tile.Tile;
import com.marshall.benjy.qld.core.tile.TileTypes;

import java.util.function.Consumer;

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

    public Model getTileModel() {
        if (tileModel != null) {
            return tileModel;
        }

        if(assetManager.isLoaded(texturePath)){
            tileModel = assetManager.get(texturePath);
        }

        System.out.println(texturePath);

        return tileModel;
    }
}
