package com.marshall.benjy.qld.core.game.control;

import com.badlogic.gdx.Gdx;
import com.marshall.benjy.qld.core.game.render.ScreenRenderer;
import com.marshall.benjy.qld.core.game.state.GameState;

public class MainController {

    private GameState state;
    private ScreenRenderer renderer;
    public MainController(GameState state, ScreenRenderer renderer) {
        this.state = state;
        this.renderer = renderer;

        Gdx.input.setInputProcessor(new KeyboardInputReceiver(state, renderer));
    }
}
