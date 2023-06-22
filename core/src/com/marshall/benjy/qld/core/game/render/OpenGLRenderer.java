package com.marshall.benjy.qld.core.game.render;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.render.DevCamera;
import com.marshall.benjy.qld.core.engine.render.DevEnvironment;
import com.marshall.benjy.qld.core.engine.render.ModelLoader;
import com.marshall.benjy.qld.core.engine.render.ecs.Scene;
import com.marshall.benjy.qld.core.engine.render.ecs.component.ModelComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.component.TransformComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.GameObject;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.QLDEntity;
import com.marshall.benjy.qld.core.engine.render.shaders.DefaultShader;
import com.marshall.benjy.qld.core.engine.render.shaders.QLDShader;
import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.state.QLDGameState;

import java.util.Collection;

public class OpenGLRenderer implements QLDRenderer {
    private AssetManager assetManager;
    private LevelRenderer levelRenderer;
    private PlayerEntity playerRenderer;
    private ModelBatch modelBatch;
    private QLDShader shader, spriteShader;
    private DevCamera camera;
    private Environment environment;
    private Scene scene;

    private ModelLoader modelLoader;

    private boolean sceneInit;

    public OpenGLRenderer(QLDGameState state) {
        camera = new DevCamera();

        scene = new Scene();
        modelLoader = new ModelLoader();

        levelRenderer = new LevelRenderer(state.getLevel());
        playerRenderer = new PlayerEntity(state.getPlayer(), camera.getCamera(), "Models/rectangle.obj");

        shader = new DefaultShader();
        shader.init();

        environment = DevEnvironment.instance();
        levelRenderer.updateInstances();

        GameObject skybox = new GameObject();
        skybox.setModel("Models/skybox.obj");
        skybox.setShader(shader.SHADER_ID);
        skybox.getComponent(ModelComponent.class).setTexturePath("Textures/skybox.jpg");
        skybox.getComponent(TransformComponent.class).transform.translate(0,-200f,0).scale(1000,-1000,1000);

        Collection<QLDEntity> tiles = levelRenderer.getInstances();
        if(!tiles.isEmpty()) {
            scene.addEntities(levelRenderer.getInstances());
            sceneInit = true;
        }
        playerRenderer.setShader(shader.SHADER_ID);
        scene.addEntity(playerRenderer);
        scene.addEntity(skybox);
        scene.setCamera(camera);

    }
    public void render() {
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

    @Override
    public void onTileUpdated(Position position) {
        levelRenderer.onTileUpdated(position);
    }


    public void updatePlayerInstance() {
        this.playerRenderer.updateModelInstance();
    }

    @Override
    public void onLevelChanged() {
        // TODO implement
    }

    public Collection<QLDEntity> getLevelEntities(){
        return levelRenderer.getInstances();
    }

    public void sceneInit() {
        Collection<QLDEntity> tiles = levelRenderer.getInstances();
        if(!tiles.isEmpty()) {
            scene.addEntities(levelRenderer.getInstances());
            sceneInit = true;
        }
    }
}


