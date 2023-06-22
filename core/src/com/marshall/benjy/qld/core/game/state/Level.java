package com.marshall.benjy.qld.core.game.state;

import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.state.tile.Tile;
import com.marshall.benjy.qld.core.game.state.tile.TileTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Level {

    private Tile[][] tiles;
    private Position startPosition;
    private Position endPosition;

    public Level(Tile[][] tiles, Position startPosition, Position endPosition) {
        this.tiles = tiles;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() { return endPosition; }
    public void setTile(Position position, Tile newTile) {
        tiles[position.getX()][position.getZ()] = newTile;
    }

}
