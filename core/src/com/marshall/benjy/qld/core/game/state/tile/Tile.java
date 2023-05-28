package com.marshall.benjy.qld.core.game.state.tile;

public class Tile {

    private TileTypes type;

    public Tile(TileTypes type) {
        this.type = type;
    }

    public TileTypes getType(){
        return type;
    }
}
