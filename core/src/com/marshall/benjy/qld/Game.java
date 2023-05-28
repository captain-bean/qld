package com.marshall.benjy.qld;

import com.marshall.benjy.qld.core.game.ControlManager;
import com.marshall.benjy.qld.core.game.GameState;
import com.marshall.benjy.qld.core.game.RenderManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game {
    private static final Logger logger = LogManager.getLogger(Game.class);

    private GameState state;
    private RenderManager renderManager;
    private ControlManager controlManager;

    public Game() {
        logger.info("Initializing app...");
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
