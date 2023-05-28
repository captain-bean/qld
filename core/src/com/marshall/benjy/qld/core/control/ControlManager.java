package com.marshall.benjy.qld.core.control;

import com.badlogic.gdx.Gdx;
import com.marshall.benjy.qld.core.game.state.GameState;

public class ControlManager {

    private GameState state;
    public ControlManager(GameState state) {
        this.state = state;
        Gdx.input.setInputProcessor(new KeyboardInputHandler(state));

    }
}
