package com.marshall.benjy.qld.core.game.logic.generator;

import com.marshall.benjy.qld.core.engine.datatype.Position;
import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.tile.Tile;
import com.marshall.benjy.qld.core.game.state.tile.TileTypes;


public class LegacyLevelGenerator {

    public static Level generateLegacyLevel(int width, int height){
        Tile[][] layout = new Tile[width][height];
        int numOfSquares = width * height;

        int startX = width;
        int startY = height / 2;
        int currentX;
        int currentY;
        do {
            for(int x = 0; x < width; x++){
                for(int y = 0; y < height; y++){
                    layout[x][y] = wood();
                }
            }
            currentX = startX;
            currentY = startY;
            int numToFill = (int) (width * height * .7);
            boolean uie = false, die = false, rie = false, lie = false;
            for(int i = 0; i <= numToFill; i++){
                uie = false;
                die = false;
                rie = false;
                lie = false;
                if(checkOutOfBoundsUp(layout, currentX, currentY) == false){
                    if(layout[currentX][currentY - 1].getType() == TileTypes.WOOD){
                        uie = true;
                    }
                }
                if(checkOutOfBoundsDown(layout, currentX, currentY) == false){
                    if(layout[currentX][currentY + 1].getType() == TileTypes.WOOD){
                        die = true;
                    }
                }
                if(checkOutOfBoundsRight(layout, currentX, currentY) == false){
                    if(layout[currentX + 1][currentY].getType() == TileTypes.WOOD){
                        rie = true;
                    }
                }
                if(checkOutOfBoundsLeft(layout, currentX, currentY) == false){
                    if(layout[currentX - 1][currentY].getType()  == TileTypes.WOOD){
                        lie = true;
                    }
                }
                int temp = (int) (Math.random() * 4);
                if(uie && temp == 0){
                    currentY--;
                    layout[currentX][currentY] = pave();
                } else if(die && (temp == 0 || temp == 1)){
                    currentY++;
                    layout[currentX][currentY] = pave();
                } else if(lie && (temp == 0 || temp == 1 || temp == 2)){
                    currentX--;
                    layout[currentX][currentY] = pave();
                } else if(rie){
                    currentX++;
                    layout[currentX][currentY] = pave();
                }

            }
        } while(checkEmpty(layout, width, height) < (int) (width * height * .4));

        layout[startX - 1][startY] = new Tile(TileTypes.BLOWED_UP);
        return new Level(layout, new Position(startX, startY), new Position(currentX, currentY));
    }

    public static boolean checkOutOfBoundsLeft(Tile[][] level, int fx, int fy){
        try{
            Tile throwAway = level[fx - 1][fy];
            return false;
        } catch(Exception ex){
            return true;
        }
    }
    public static boolean checkOutOfBoundsRight(Tile[][] level, int fx, int fy){
        try{
            Tile throwAway = level[fx + 1][fy];
            return false;
        } catch(Exception ex){
            return true;
        }
    }
    public static boolean checkOutOfBoundsUp(Tile[][] level, int fx, int fy){
        try{
            Tile throwAway = level[fx][fy - 1];
            return false;
        } catch(Exception ex){
            return true;
        }
    }
    public static boolean checkOutOfBoundsDown(Tile[][] level, int fx, int fy){
        try{
            Tile throwAway = level[fx][fy + 1];
            return false;
        } catch(Exception ex){
            return true;
        }
    }

    private static int checkEmpty(Tile[][] level, int width, int height){
        int temp = 0;
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(level[x][y].getType() == TileTypes.WOOD){
                    temp++;
                }
            }
        }
        return temp;
    }

    static Tile pave() {
        return new Tile(TileTypes.PAVEMENT);
    }

    static Tile wood() {
        return new Tile(TileTypes.WOOD);
    }
}
