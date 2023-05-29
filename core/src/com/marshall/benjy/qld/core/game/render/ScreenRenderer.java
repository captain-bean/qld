package com.marshall.benjy.qld.core.game.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.marshall.benjy.qld.core.game.render.shaders.DefaultShader;
import com.marshall.benjy.qld.core.game.state.GameState;

public class ScreenRenderer {
    private AssetManager assetManager;

    private LevelRenderer levelRenderer;
    private PlayerRenderer playerRenderer;

    private ModelBatch modelBatch;
    private Shader shader;
    private Camera camera;
    private Environment environment;
    
    public ScreenRenderer(GameState state) {
        assetManager = new AssetManager();

        levelRenderer = new LevelRenderer(state.getLevel(), assetManager);
        playerRenderer = new PlayerRenderer(state.getPlayer(), "bomb.g3db", assetManager);

        modelBatch = new ModelBatch();

        shader = new DefaultShader();
        shader.init();

        camera = DevCamera.instance();

        environment = DevEnvironment.instance();

        assetManager.finishLoading();
    }
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(camera);
        modelBatch.render(levelRenderer.getInstances(), environment, shader);
        modelBatch.render(playerRenderer.getPlayerModelInstance(), environment, shader);
        modelBatch.end();
    }

    public void dispose() {
        modelBatch.dispose();
        assetManager.dispose();
    }

}


