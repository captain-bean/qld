package com.marshall.benjy.qld.core.game.level;

import com.marshall.benjy.qld.core.game.tile.Tile;

public class Level {

    private String name;
    private Tile[][] tiles;

    public Level(String name, Tile[][] tiles) {
        this.name = name;
        this.tiles = tiles;
    }

    public Level(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public String getName(){
        return name;
    }

}
