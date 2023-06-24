package com.marshall.benjy.qld.core.game.state.datatype;

import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.state.datatype.tile.Tile;

public class Level {

    private Tile[][] tiles;
    private Position startPosition;

    public Level(Tile[][] tiles, Position startPosition) {
        this.tiles = tiles;
        this.startPosition = startPosition;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public void setTile(Position position, Tile newTile) {
        tiles[position.getX()][position.getZ()] = newTile;
    }

}
