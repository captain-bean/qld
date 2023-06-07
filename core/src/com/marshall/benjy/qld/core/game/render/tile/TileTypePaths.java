package com.marshall.benjy.qld.core.game.render.tile;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.marshall.benjy.qld.core.game.state.tile.TileTypes;
import com.marshall.benjy.qld.core.game.state.tile.Tile;

import java.util.HashMap;
import java.util.Map;

public class TileTypePaths {
    private Map<TileTypes, String> tilePaths = new HashMap<>();

    public TileTypePaths() {
        tilePaths.put(TileTypes.PAVEMENT, "blockSnow.g3db");

        tilePaths.put(TileTypes.WOOD, "block.g3db");

        tilePaths.put(TileTypes.BLOWED_UP, "blockSnowHexagonLow.g3db");
    }

    public String getTilePaths(Tile tile) {
        return tilePaths.get(tile.getType());
    }

}
