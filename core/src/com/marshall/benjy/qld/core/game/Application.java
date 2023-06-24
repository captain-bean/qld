package com.marshall.benjy.qld.core.game;

import com.marshall.benjy.qld.core.game.input.commands.InputPublisher;
import com.marshall.benjy.qld.core.game.render.api.RenderManager;
import com.marshall.benjy.qld.core.game.state.api.StateManager;
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

        stateManager = new StateManager(context);

        renderManager = new RenderManager(context, config, stateManager);

        inputPublisher = new InputPublisher(context);
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
