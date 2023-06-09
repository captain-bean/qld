package com.marshall.benjy.qld.core.game;

import com.marshall.benjy.qld.core.game.logic.control.QLDController;
import com.marshall.benjy.qld.core.game.logic.generator.QLDGameStateFactory;
import com.marshall.benjy.qld.core.engine.render.ecs.Scene;
import com.marshall.benjy.qld.core.game.render.QLDRenderState;
import com.marshall.benjy.qld.core.game.render.QLDRenderer;
import com.marshall.benjy.qld.core.game.render.TextRenderer;
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

        state = QLDGameStateFactory.development();
        renderer = new QLDRenderState(state);
        controller = new QLDController(state, renderer);
    }

    public void render() {
        renderer.render();
    }

    public void dispose() {
        renderer.dispose();
    }

    public void resize(int width, int height){
        renderer.resize(width, height);
    }
}
