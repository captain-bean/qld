package com.marshall.benjy.qld.core.game.render.api;

import com.marshall.benjy.qld.core.game.QLDConfig;
import com.marshall.benjy.qld.core.game.render.QLDRenderer;
import com.marshall.benjy.qld.core.game.render.dev.TextRenderer;
import com.marshall.benjy.qld.core.game.render.opengl.OpenGLRenderer;
import com.marshall.benjy.qld.core.game.state.api.StateManager;
import com.marshall.benjy.qld.core.game.state.api.QLDGameState;
import org.apache.camel.CamelContext;

public class RenderManager {

    private QLDRenderer renderer;
    private RenderBridge renderBridge;

    public RenderManager(CamelContext context, QLDConfig config, StateManager stateManager) {
        QLDGameState state = stateManager.getState();
        renderer = getRenderer(state, config);
        renderBridge = new RenderBridge(renderer, context);
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

    private QLDRenderer getRenderer(QLDGameState state, QLDConfig config) {
        QLDRenderer pick;
        if(!config.isHeadless()) {
            pick = new OpenGLRenderer(state);
        } else {
            pick = new TextRenderer(state);
        }
        return pick;
    }
}
