package com.marshall.benjy.qld;

import java.util.Vector;

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

    public Level testLevel() {
        boolean[][] map = {
                {true, true, true, true, true}
        };
        return new Level(map);
    }
}
