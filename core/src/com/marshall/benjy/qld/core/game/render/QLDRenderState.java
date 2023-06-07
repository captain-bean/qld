package com.marshall.benjy.qld.core.game.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.render.DevCamera;
import com.marshall.benjy.qld.core.engine.render.DevEnvironment;
import com.marshall.benjy.qld.core.engine.render.ModelLoader;
import com.marshall.benjy.qld.core.engine.render.ModelRenderer;
import com.marshall.benjy.qld.core.engine.render.ecs.Scene;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.QLDEntity;
import com.marshall.benjy.qld.core.engine.render.shaders.DefaultShader;
import com.marshall.benjy.qld.core.engine.render.shaders.QLDShader;
import com.marshall.benjy.qld.core.engine.render.shaders.QLDShaderProvider;
import com.marshall.benjy.qld.core.game.state.QLDGameState;

import java.util.List;

public class QLDRenderState implements QLDRenderer {
    private AssetManager assetManager;
    private LevelRenderer levelRenderer;
    private PlayerRenderer playerRenderer;
    private ModelBatch modelBatch;
    private QLDShader shader;
    private DevCamera camera;
    private Environment environment;
    private Scene scene;

    private ModelLoader modelLoader;

    private boolean sceneInit;

    public QLDRenderState(QLDGameState state) {
        camera = new DevCamera();

        scene = new Scene();
        modelLoader = new ModelLoader();

        levelRenderer = new LevelRenderer(state.getLevel());
        playerRenderer = new PlayerRenderer(state.getPlayer(), camera.getCamera(), "Models/rectangle.obj");

        shader = new DefaultShader();
        shader.init();

        environment = DevEnvironment.instance();
        levelRenderer.updateInstances();


        List<QLDEntity> tiles = levelRenderer.getInstances();
        if(!tiles.isEmpty()) {
            scene.addEntities(levelRenderer.getInstances());
             sceneInit = true;
        }

        ModelRenderer.Static_Renderer.setCamera(camera);
    }
    public void render() {
        ModelRenderer.Static_Renderer.enqueue(shader.SHADER_ID,playerRenderer.getPlayerModelInstance());
        if(sceneInit){
            scene.update();
        }else {
            sceneInit();
        }
}

    public void dispose() {
    }

    public void moveCamera(MoveCameraCommand command) {
        camera.moveCamera(command);
    }

    public void resize(int width, int height){
        camera.resize(width, height);
    }

    public void updateLevelInstances() {
        this.levelRenderer.updateInstances();
    }

    public void updatePlayerInstance() {
        this.playerRenderer.updateModelInstance();
    }

    public List<QLDEntity> getLevelEntities(){
        return levelRenderer.getInstances();
    }

    public void sceneInit() {
        List<QLDEntity> tiles = levelRenderer.getInstances();
        if(!tiles.isEmpty()) {
            scene.addEntities(levelRenderer.getInstances());
            sceneInit = true;
        }
    }
}

