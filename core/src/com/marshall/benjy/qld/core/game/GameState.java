package com.marshall.benjy.qld.core.game;

import com.marshall.benjy.qld.core.datatype.Position;
import com.marshall.benjy.qld.core.game.level.Level;
import com.marshall.benjy.qld.core.game.level.LevelGenerator;
import com.marshall.benjy.qld.core.game.player.Player;
import com.marshall.benjy.qld.core.game.tile.Tile;
import com.marshall.benjy.qld.core.game.tile.TileTypes;
import com.marshall.benjy.qld.core.game.world.World;

public class GameState {

    private World world;
    private Level level;
    private Player player;

    public GameState() {
        this.world = new World();
        this.level = LevelGenerator.testLevel();
        this.player = new Player();
    }

    public World getWorld() {
        return world;
    }
    public Level getLevel() {
        return level;
    }
    public Player getPlayer() {
        return player;
    }
}
