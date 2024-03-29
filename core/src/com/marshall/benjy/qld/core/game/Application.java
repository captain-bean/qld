package com.marshall.benjy.qld.core.game;

import com.marshall.benjy.qld.core.game.director.Director;
import com.marshall.benjy.qld.core.game.input.InputPublisher;
import com.marshall.benjy.qld.core.game.render.api.RenderManager;
import com.marshall.benjy.qld.core.game.state.api.QLDGameState;
import com.marshall.benjy.qld.core.game.state.api.StateManager;
import com.marshall.benjy.qld.core.game.state.generator.QLDGameStateFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);

    private StateManager stateManager;
    private RenderManager renderManager;
    private InputPublisher inputPublisher;

    public Application(QLDConfig config) {
        logger.info("Initializing app...");

        CamelContext context = new DefaultCamelContext();
        context.start();

        QLDGameState initialState = QLDGameStateFactory.development();

        stateManager = new StateManager(context, initialState);

        renderManager = new RenderManager(context, config, stateManager);

        inputPublisher = new InputPublisher(context);

        Director director = new Director(context, stateManager);
    }

    public void render() {
        renderManager.render();
    }

    public void dispose() {
        renderManager.dispose();
    }

    public void resize(int width, int height){
        renderManager.resize(width, height);
    }
}
