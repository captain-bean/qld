package com.marshall.benjy.qld.core.game.state;

import com.marshall.benjy.qld.core.engine.datatype.Position;
import com.marshall.benjy.qld.core.game.state.tile.Tile;
import com.marshall.benjy.qld.core.game.state.tile.TileTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Level {

    private Tile[][] tiles;

    private List<Consumer<Tile>> tileDestroyedListeners = new ArrayList<>();

    public Level(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    private void setTile(Position position, Tile newTile) {
        tiles[position.getX()][position.getZ()] = newTile;
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
        if(position.getX() < 0 || position.getZ() < 0 || position.getX() >= tiles.length || position.getZ() >= tiles[0].length) {
            return false;
        }
        return true;
    }

}
