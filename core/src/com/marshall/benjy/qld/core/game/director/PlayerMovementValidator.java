package com.marshall.benjy.qld.core.game.director;

import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.state.api.QLDGameState;
import com.marshall.benjy.qld.core.game.state.datatype.tile.Tile;
import com.marshall.benjy.qld.core.game.state.datatype.tile.TileTypes;

public class PlayerMovementValidator {

    public static boolean validMove(QLDGameState state, Position position) {
        Tile[][] tiles = state.getLevel().getTiles();
        if(position.getX() < 0 || position.getZ() < 0 || position.getX() >= tiles.length || position.getZ() >= tiles[0].length) {
            return false;
        }
        TileTypes tileType = tiles[position.getX()][position.getZ()].getType();
        if(tileType == TileTypes.BLOWED_UP || tileType == TileTypes.WOOD) {
            return false;
        }
        return true;
    }

}
