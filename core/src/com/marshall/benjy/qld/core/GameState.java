package com.marshall.benjy.qld.core;

import com.marshall.benjy.qld.core.level.Level;
import com.marshall.benjy.qld.core.level.LevelGenerator;

public class GameState {

    private Level level;

    public GameState() {
        this.level = LevelGenerator.testLevel();
    }

    public Level getLevel() {
        return level;
    }

}
