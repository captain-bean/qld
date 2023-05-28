package com.marshall.benjy.qld.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.marshall.benjy.qld.core.control.ControlManager;
import com.marshall.benjy.qld.core.game.GameState;
import com.marshall.benjy.qld.core.render.RenderManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);

    private GameState state;
    private RenderManager renderManager;
    private ControlManager controlManager;

    public Application() {
        logger.info("Initializing app...");

        Gdx.graphics.setUndecorated(true);
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);

        state = new GameState();
        renderManager = new RenderManager(state);
        controlManager = new ControlManager(state);
    }

    public void render() {
        renderManager.render(state);
    }

    public void dispose() {
        renderManager.dispose();
    }
}
