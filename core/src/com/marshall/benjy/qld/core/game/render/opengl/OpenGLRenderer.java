package com.marshall.benjy.qld.core.game.render.opengl;

import com.badlogic.gdx.graphics.g3d.Environment;
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
import com.marshall.benjy.qld.core.game.render.QLDRenderer;
import com.marshall.benjy.qld.core.game.state.api.QLDGameState;

import java.util.Collection;

public class OpenGLRenderer implements QLDRenderer {
    private TileGroup levelContainer;
    private PlayerEntity playerEntity;
    private QLDShader shader, spriteShader;
    private DevCamera camera;
    private Environment environment;
    private Scene scene;

    private ModelLoader modelLoader;

    private boolean sceneInit;

    public OpenGLRenderer(QLDGameState initialState) {
        camera = new DevCamera();

        scene = new Scene(camera);
        modelLoader = new ModelLoader();

        levelContainer = new TileGroup(initialState.getLevel());
        playerEntity = new PlayerEntity(initialState.getPlayer(), camera.getCamera(), "Models/rectangle.obj");

        shader = new DefaultShader();
        shader.init();

        environment = DevEnvironment.instance();
        levelContainer.updateAllTiles();

        GameObject skybox = new GameObject();
        skybox.setModel("Models/skybox.obj");
        skybox.setShader(shader.SHADER_ID);
        skybox.getComponent(ModelComponent.class).setTexturePath("Textures/skybox.jpg");
        skybox.getComponent(TransformComponent.class).transform.translate(0,-200f,0).scale(1000,-1000,1000);

        Collection<QLDEntity> tiles = levelContainer.getInstances();
        if(!tiles.isEmpty()) {
            scene.addEntities(levelContainer.getInstances());
            sceneInit = true;
        }
        playerEntity.setShader(shader.SHADER_ID);
        scene.addEntity(playerEntity);
        scene.addEntity(levelContainer);
        scene.addEntity(skybox);

    }
    public void render() {
        if(sceneInit){
            scene.update();
        }else {
            sceneInit();
        }
}
    public void moveCamera(MoveCameraCommand command) {
        camera.moveCamera(command);
    }

    @Override
    public void dispose() {

    }

    public void resize(int width, int height){
        camera.resize(width, height);
    }

    @Override
    public void onTileUpdated(Position position) {
        levelContainer.markPositionDirty(position);
    }

    public void updatePlayerInstance() {
        this.playerEntity.markNeedsUpdate();
    }

    @Override
    public void onLevelChanged() {
        // TODO implement
    }

    public void sceneInit() {
        Collection<QLDEntity> tiles = levelContainer.getInstances();
        if(!tiles.isEmpty()) {
            scene.addEntities(levelContainer.getInstances());
            sceneInit = true;
        }
    }
}


