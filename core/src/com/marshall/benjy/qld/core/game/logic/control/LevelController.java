package com.marshall.benjy.qld.core.game.logic.control;

import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.QLDGameState;
import com.marshall.benjy.qld.core.game.state.tile.Tile;
import com.marshall.benjy.qld.core.game.state.tile.TileTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LevelController {

    private Level level;

    private QLDGameState state;
    private List<Consumer<Position>> tileDestroyedListeners = new ArrayList<>();
    public LevelController(QLDGameState state) {
        this.state = state;
        this.level = state.getLevel();
    }

    public void playerMoved(Position position) {
        level.setTile(position, new Tile(TileTypes.BLOWED_UP));
        for(Consumer<Position> listener : tileDestroyedListeners) {
            listener.accept(position);
        }
    }

    public void addTileDestroyedListener(Consumer<Position> destroyedListener) {
        this.tileDestroyedListeners.add(destroyedListener);
    }

    public boolean validPlayerPosition(Position position) {
        Tile[][] tiles = level.getTiles();
        if(position.getX() < 0 || position.getZ() < 0 || position.getX() >= tiles.length || position.getZ() >= tiles[0].length) {
            return false;
        }
        TileTypes tileType = tiles[position.getX()][position.getZ()].getType();
        if(tileType == TileTypes.BLOWED_UP || tileType == TileTypes.WOOD) {
            return false;
        }
        return true;
    }

    public void changeLevel(Level level) {
        this.state.changeLevel(level);
        this.level = level;
    }
}
