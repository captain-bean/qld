package com.marshall.benjy.qld.core.game.generator;

import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.tile.Tile;

import static com.marshall.benjy.qld.core.game.generator.LevelGenerator.pave;

public class LegacyLevelGenerator {

    public static Level generateLegacyLevel(int width, int height) {
        boolean[][] layout = legacyGenerateLevel(width, height);

        Tile[][] tiles = new Tile[width][height];
        for(int i = 0; i < layout.length; i++) {
            Tile[] tileLine = new Tile[width];
            for(int j = 0; j < layout[i].length; j++) {
                if(layout[i][j] == true) {
                    tileLine[j] = LevelGenerator.pave();
                } else {
                    tileLine[j] = LevelGenerator.wood();
                }
            }
            tiles[i] = tileLine;
        }

        return new Level(tiles);
    }

    private static boolean[][] legacyGenerateLevel(int width, int height){
        boolean[][] layout = new boolean[width][height];
        int numOfSquares = width * height;
        do {
            for(int x = 0; x < width; x++){
                for(int y = 0; y < height; y++){
                    layout[x][y] = true;
                }
            }
            int fx = width;
            int fy = height / 2;
            int numToFill = (int) (width * height * .7);
            boolean uie = false, die = false, rie = false, lie = false;
            for(int i = 0; i <= numToFill; i++){
                uie = false;
                die = false;
                rie = false;
                lie = false;
                if(checkOutOfBoundsUp(layout, fx, fy) == false){
                    if(layout[fx][fy - 1] == true){
                        uie = true;
                    }
                }
                if(checkOutOfBoundsDown(layout, fx, fy) == false){
                    if(layout[fx][fy + 1] == true){
                        die = true;
                    }
                }
                if(checkOutOfBoundsRight(layout, fx, fy) == false){
                    if(layout[fx + 1][fy] == true){
                        rie = true;
                    }
                }
                if(checkOutOfBoundsLeft(layout, fx, fy) == false){
                    if(layout[fx - 1][fy] == true){
                        lie = true;
                    }
                }
                int temp = (int) (Math.random() * 4);
                if(uie && temp == 0){
                    fy--;
                    layout[fx][fy] = false;
                } else if(die && (temp == 0 || temp == 1)){
                    fy++;
                    layout[fx][fy] = false;
                } else if(lie && (temp == 0 || temp == 1 || temp == 2)){
                    fx--;
                    layout[fx][fy] = false;
                } else if(rie){
                    fx++;
                    layout[fx][fy] = false;
                }

            }
        } while(checkEmpty(layout, width, height) < (int) (width * height * .4));

        return layout;
    }

    public static boolean checkOutOfBoundsLeft(boolean[][] level, int fx, int fy){
        try{
            boolean throwAway = level[fx - 1][fy];
            return false;
        } catch(Exception ex){
            return true;
        }
    }
    public static boolean checkOutOfBoundsRight(boolean[][] level, int fx, int fy){
        try{
            boolean throwAway = level[fx + 1][fy];
            return false;
        } catch(Exception ex){
            return true;
        }
    }
    public static boolean checkOutOfBoundsUp(boolean[][] level, int fx, int fy){
        try{
            boolean throwAway = level[fx][fy - 1];
            return false;
        } catch(Exception ex){
            return true;
        }
    }
    public static boolean checkOutOfBoundsDown(boolean[][] level, int fx, int fy){
        try{
            boolean throwAway = level[fx][fy + 1];
            return false;
        } catch(Exception ex){
            return true;
        }
    }

    private static int checkEmpty(boolean[][] level, int width, int height){
        int temp = 0;
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(level[x][y] == false){
                    temp++;
                }
            }
        }
        return temp;
    }
}
