package com.marshall.benjy.qld.core.render.renderer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.marshall.benjy.qld.core.game.tile.TileTypes;
import com.marshall.benjy.qld.core.game.tile.Tile;

import java.util.HashMap;
import java.util.Map;

public class TileRenderer {
    private Map<TileTypes, TileTypeRenderer> typeRenderers = new HashMap<>();

    public TileRenderer(AssetManager assetManager) {
        typeRenderers.put(TileTypes.PAVEMENT,
                new TileTypeRenderer(TileTypes.PAVEMENT, "blockSnow.g3db", assetManager));

        typeRenderers.put(TileTypes.WOOD,
                new TileTypeRenderer(TileTypes.WOOD, "block.g3db", assetManager));

        typeRenderers.put(TileTypes.BLOWED_UP,
                new TileTypeRenderer(TileTypes.BLOWED_UP, "blockSnowHexagonLow.g3db", assetManager));
    }

    public Model getTileModel(Tile tile) {
        return typeRenderers.get(tile.getType()).getTileModel();
    }

}
