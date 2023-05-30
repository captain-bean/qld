package com.marshall.benjy.qld.core.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.marshall.benjy.qld.core.game.control.MainController;
import com.marshall.benjy.qld.core.game.state.GameState;
import com.marshall.benjy.qld.core.game.render.ScreenRenderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);

    private GameState state;
    private ScreenRenderer renderer;
    private MainController controller;

    public Application() {
        logger.info("Initializing app...");

        Gdx.graphics.setUndecorated(true);
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);

        state = new GameState();
        renderer = new ScreenRenderer(state);
        controller = new MainController(state, renderer);
    }

    public void render() {
        renderer.render();
    }

    public void dispose() {
        renderer.dispose();
    }
}
