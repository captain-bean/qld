package com.marshall.benjy.qld.core.game;

import com.marshall.benjy.qld.core.game.control.QLDController;
import com.marshall.benjy.qld.core.game.render.QLDRenderer;
import com.marshall.benjy.qld.core.game.state.QLDGameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);

    private QLDGameState state;
    private QLDRenderer renderer;
    private QLDController controller;

    public Application() {
        logger.info("Initializing app...");

        state = new QLDGameState();
        renderer = new QLDRenderer(state);
        controller = new QLDController(state, renderer);
    }

    public void render() {
        renderer.render();
    }

    public void dispose() {
        renderer.dispose();
    }

    public void resize(int height, int width){
        renderer.resize(height,width);
    }
}
