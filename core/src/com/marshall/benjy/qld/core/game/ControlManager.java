package com.marshall.benjy.qld.core.game;

import com.badlogic.gdx.Gdx;
import com.marshall.benjy.qld.core.game.control.KeyboardInputHandler;

public class ControlManager {

    private GameState state;
    public ControlManager(GameState state) {
        this.state = state;
        Gdx.input.setInputProcessor(new KeyboardInputHandler());

    }

    public GameState acceptInputs(GameState gameState) {
        // Look for inputs


        return gameState;
    }
}
