package com.marshall.benjy.qld.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.utils.ScreenUtils;
import com.marshall.benjy.qld.Driver;
import com.marshall.benjy.qld.core.level.Level;
import com.marshall.benjy.qld.core.level.LevelGenerator;
import com.marshall.benjy.qld.core.level.LevelRenderer;
import com.marshall.benjy.qld.core.tile.TileTypes;

import java.util.ArrayList;

public class RenderManager {

    private AssetManager assetManager;

    private LevelRenderer levelRenderer;
    private PerspectiveCamera cam;
    private ModelBatch modelBatch;
    private Environment environment;
    private CameraInputController camController;

    private boolean initialized = false;

    public RenderManager() {
        assetManager = new AssetManager();

        Level level = LevelGenerator.testLevel();
        levelRenderer = new LevelRenderer(level, assetManager);

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);

        modelBatch = new ModelBatch();

    }
    public void render(GameState state) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        camController.update();

        modelBatch.begin(cam);
        modelBatch.render(levelRenderer.getInstances(), environment);
        modelBatch.end();
    }

    public void dispose() {
        modelBatch.dispose();
        assetManager.dispose();
    }

}


