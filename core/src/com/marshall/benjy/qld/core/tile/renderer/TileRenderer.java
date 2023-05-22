package com.marshall.benjy.qld.core.tile.renderer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.marshall.benjy.qld.core.tile.Tile;
import com.marshall.benjy.qld.core.tile.TileTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TileRenderer {
    private Map<TileTypes, TileTypeRenderer> typeRenderers = new HashMap<>();

    public TileRenderer(AssetManager assetManager) {
        typeRenderers.put(TileTypes.PAVEMENT,
                new TileTypeRenderer(TileTypes.PAVEMENT, "Plate_Sidewalk_01.obj", assetManager));

        typeRenderers.put(TileTypes.WOOD,
                new TileTypeRenderer(TileTypes.WOOD, "Plate_Wood_01.obj", assetManager));
    }

    public Model getTileModel(Tile tile) {
        return typeRenderers.get(tile.getType()).getTileModel();
    }

}
