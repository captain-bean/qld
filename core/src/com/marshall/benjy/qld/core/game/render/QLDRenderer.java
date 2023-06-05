package com.marshall.benjy.qld.core.game.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.marshall.benjy.qld.core.engine.datatype.Constants;
import com.marshall.benjy.qld.core.engine.render.DevCamera;
import com.marshall.benjy.qld.core.engine.render.DevEnvironment;
import com.marshall.benjy.qld.core.engine.render.shaders.DefaultShader;
import com.marshall.benjy.qld.core.game.state.QLDGameState;

public class QLDRenderer {
    private AssetManager assetManager;
    private LevelRenderer levelRenderer;
    private PlayerRenderer playerRenderer;
    private ModelBatch modelBatch;
    private Shader shader;
    private DevCamera camera;
    private Environment environment;


    public QLDRenderer(QLDGameState state) {
        assetManager = new AssetManager();

        camera = new DevCamera();

        levelRenderer = new LevelRenderer(state.getLevel(), assetManager);
        playerRenderer = new PlayerRenderer(state.getPlayer(), camera.getCamera(), "rectangle.obj", assetManager);

        modelBatch = new ModelBatch();

        shader = new DefaultShader();
        shader.init();

        environment = DevEnvironment.instance();
        assetManager.finishLoading();
    }
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(camera.getCamera());

        modelBatch.render(levelRenderer.getInstances(), environment, shader);
        modelBatch.render(playerRenderer.getPlayerModelInstance(), environment, shader);

        modelBatch.end();
    }

    public void dispose() {
        modelBatch.dispose();
        assetManager.dispose();
    }

    public Camera getCamera() {
        return camera.getCamera();
    }

    public void resize(int width, int height){
        camera.resize(width, height);
    }
}


