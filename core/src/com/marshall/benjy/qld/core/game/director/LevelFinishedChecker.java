package com.marshall.benjy.qld.core.game.director;

import com.marshall.benjy.qld.core.game.state.datatype.Level;
import com.marshall.benjy.qld.core.game.state.datatype.tile.Tile;
import com.marshall.benjy.qld.core.game.state.datatype.tile.TileTypes;

public class LevelFinishedChecker {

    public static boolean isFinished(Level level) {
        boolean finished = true;
        Tile[][] tiles = level.getTiles();
        for(Tile[] tileArr : tiles) {
            for(Tile tile : tileArr) {
                if(tile.getType() == TileTypes.PAVEMENT) {
                    finished = false;
                }
            }
        }
        return finished;
    }
}
