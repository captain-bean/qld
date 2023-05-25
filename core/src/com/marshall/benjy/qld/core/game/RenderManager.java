package com.marshall.benjy.qld.core.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.marshall.benjy.qld.core.shaders.TestShader;
import com.marshall.benjy.qld.core.game.level.Level;
import com.marshall.benjy.qld.core.game.level.LevelGenerator;
import com.marshall.benjy.qld.core.game.level.LevelRenderer;

public class RenderManager {

    private AssetManager assetManager;
    private LevelRenderer levelRenderer;
    private ModelBatch modelBatch;
    private Shader shader;

    public RenderManager() {
        assetManager = new AssetManager();

        Level level = LevelGenerator.testLevel();
        levelRenderer = new LevelRenderer(level, assetManager);

        modelBatch = new ModelBatch();
        
        shader = new TestShader();
        shader.init();

        assetManager.finishLoading();
    }
    public void render(GameState state) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        modelBatch.begin(state.getWorld().getCamera());
        modelBatch.render(levelRenderer.getInstances(), state.getWorld().getEnvironment());
        modelBatch.end();
    }

    public void dispose() {
        modelBatch.dispose();
        assetManager.dispose();
    }

}


