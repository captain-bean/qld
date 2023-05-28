package com.marshall.benjy.qld.core.game.level;

import com.marshall.benjy.qld.core.datatype.Position;
import com.marshall.benjy.qld.core.game.tile.Tile;
import com.marshall.benjy.qld.core.game.tile.TileTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Level {

    private String name;
    private Tile[][] tiles;

    private List<Consumer<Tile>> tileDestroyedListeners = new ArrayList<>();

    public Level(String name, Tile[][] tiles) {
        this(tiles);
        this.name = name;
    }

    public Level(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    private void setTile(Position position, Tile newTile) {
        tiles[position.getX()][position.getY()] = newTile;
    }

    public void blowUp(Position position) {
        setTile(position, new Tile(TileTypes.BLOWED_UP));
        for(Consumer<Tile> listener : tileDestroyedListeners) {
            listener.accept(null);
        }
    }

    public void addTileDestroyedListener(Consumer<Tile> destroyedListener) {
        this.tileDestroyedListeners.add(destroyedListener);
    }

    public boolean validPlayerPosition(Position position) {
        if(position.getX() < 0 || position.getY() < 0) {
            return false;
        }
        return true;
    }

    public String getName(){
        return name;
    }

}
