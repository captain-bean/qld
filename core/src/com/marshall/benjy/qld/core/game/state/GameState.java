package com.marshall.benjy.qld.core.game.state;

import com.marshall.benjy.qld.core.game.LevelGenerator;
import com.marshall.benjy.qld.core.render.DevEnvironment;

public class GameState {

    private DevEnvironment world;
    private Level level;
    private Player player;

    public GameState() {
        this.world = new DevEnvironment();
        this.level = LevelGenerator.testLevel();
        this.player = new Player();
    }

    public DevEnvironment getWorld() {
        return world;
    }
    public Level getLevel() {
        return level;
    }
    public Player getPlayer() {
        return player;
    }
}
