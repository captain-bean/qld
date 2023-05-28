package com.marshall.benjy.qld.core.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.marshall.benjy.qld.core.game.state.GameState;

public class RenderManager {
    private AssetManager assetManager;
    private LevelRenderer levelRenderer;
    private PlayerRenderer playerRenderer;
    private ModelBatch modelBatch;
    private WorldRenderer worldRenderer;
    
    public RenderManager(GameState state) {
        assetManager = new AssetManager();

        levelRenderer = new LevelRenderer(state.getLevel(), assetManager);
        playerRenderer = new PlayerRenderer(state.getPlayer(), "bomb.g3db", assetManager);

        modelBatch = new ModelBatch();
        worldRenderer = new WorldRenderer(state.getWorld(), modelBatch);

        assetManager.finishLoading();
    }
    public void render(GameState state) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(state.getWorld().getCamera());
        worldRenderer.render(levelRenderer.getInstances());
        worldRenderer.render(playerRenderer.getPlayerModelInstance());
        modelBatch.end();
    }

    public void dispose() {
        modelBatch.dispose();
        assetManager.dispose();
    }

}


