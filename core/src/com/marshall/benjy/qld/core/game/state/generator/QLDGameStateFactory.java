package com.marshall.benjy.qld.core.game.state.generator;

import com.marshall.benjy.qld.core.game.state.datatype.Level;
import com.marshall.benjy.qld.core.game.state.api.QLDGameState;

public class QLDGameStateFactory {

    public static QLDGameState development() {
        Level level = LegacyLevelGenerator.generateLegacyLevel(15, 15);

        QLDGameState state = new QLDGameState(level);

        return state;
    }
}
