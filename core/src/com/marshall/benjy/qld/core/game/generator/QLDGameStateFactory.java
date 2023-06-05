package com.marshall.benjy.qld.core.game.generator;

import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.QLDGameState;

public class QLDGameStateFactory {

    public static QLDGameState development() {
        Level level = LegacyLevelGenerator.generateLegacyLevel(15, 15);

        QLDGameState state = new QLDGameState(level);

        return state;
    }
}
