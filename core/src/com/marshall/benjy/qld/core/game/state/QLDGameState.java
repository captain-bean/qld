package com.marshall.benjy.qld.core.game.state;

import com.marshall.benjy.qld.core.game.logic.generator.LegacyLevelGenerator;

public class QLDGameState {
    private Level level;
    private Player player;

    public QLDGameState() {
        this(LegacyLevelGenerator.generateLegacyLevel(15, 15));
    }

    public QLDGameState(Level level) {
        this.level = level;
        this.player = new Player(level.getStartPosition());
    }

    public Level getLevel() {
        return level;
    }
    public Player getPlayer() {
        return player;
    }
}
