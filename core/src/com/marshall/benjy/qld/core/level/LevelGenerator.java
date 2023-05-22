package com.marshall.benjy.qld.core.level;

import com.marshall.benjy.qld.core.RenderManager;
import com.marshall.benjy.qld.core.tile.Tile;
import com.marshall.benjy.qld.core.tile.TileTypes;

public class LevelGenerator {

    /*public Level createLevel(int length, int width, int fill) {
        boolean[][] map = new boolean[length][width];

        int playerX = 0;
        int playerY = 0;
        map[0][0] = true;
        for(int i = 0; i < fill; i++) {
            getMovementChoices();
            pickOne();
            fill();
            next();
        }
        // Reverse find a path until at fill
    }*/

    public static Level testLevel() {
        Tile[][] tiles = {
                {pave(), pave(), pave(), pave(), wood()},
                {pave(), pave(), pave(), wood(), wood()},
                {pave(), pave(), pave(), wood(), pave()},
                {pave(), pave(), pave(), wood(), pave()},
                {pave(), pave(), pave(), wood(), pave()}
        };

        return new Level("Test Level", tiles);
    }

    private static Tile pave() {
        return new Tile(TileTypes.PAVEMENT);
    }

    private static Tile wood() {
        return new Tile(TileTypes.WOOD);
    }
}
