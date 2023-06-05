package com.marshall.benjy.qld.core.game.state;

import com.marshall.benjy.qld.core.game.generator.LevelGenerator;

public class QLDGameState {
    private Level level;
    private Player player;

    public QLDGameState() {
        this(LevelGenerator.testLevel());
    }

    public QLDGameState(Level level) {
        this.level = level;
        this.player = new Player();
    }

    public Level getLevel() {
        return level;
    }
    public Player getPlayer() {
        return player;
    }
}
