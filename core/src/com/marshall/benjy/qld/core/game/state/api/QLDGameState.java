package com.marshall.benjy.qld.core.game.state.api;

import com.marshall.benjy.qld.core.game.state.datatype.GameContext;
import com.marshall.benjy.qld.core.game.state.generator.LegacyLevelGenerator;
import com.marshall.benjy.qld.core.game.state.datatype.Level;
import com.marshall.benjy.qld.core.game.state.datatype.Player;

public class QLDGameState {
    private Level level;
    private Player player;

    private GameContext context;

    public QLDGameState(Level level) {
        this.level = level;
        this.player = new Player(level.getStartPosition());
        this.context = new GameContext();
    }

    public Level getLevel() {
        return level;
    }

    public Player getPlayer() {
        return player;
    }

    protected void setLevel(Level level) {
        this.level = level;
    }
}
