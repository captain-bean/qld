package com.marshall.benjy.qld.core.engine.render.ecs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.marshall.benjy.qld.core.engine.render.DevCamera;
import com.marshall.benjy.qld.core.engine.render.ModelLoader;
import com.marshall.benjy.qld.core.engine.render.ModelRenderer;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.GameObject;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.RenderTestEntity;
import com.marshall.benjy.qld.core.engine.render.ecs.system.RenderQueueSystem;
import com.marshall.benjy.qld.core.engine.render.ecs.system.UpdateSystem;
import com.marshall.benjy.qld.core.engine.render.DevEnvironment;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

public class Scene {

    public DevCamera camera;
    private Engine ecsEngine;
    private RenderQueueSystem renderQueueSystem;
    private UpdateSystem updateSystem;
    private Environment environment;
    private float deltaTime = 0;
    private Instant last = Instant.now();

    public Scene(DevCamera camera) {
        ModelLoader.createStaticLoader();
        ecsEngine = new Engine();
        renderQueueSystem = new RenderQueueSystem(camera);
        updateSystem = new UpdateSystem();
        environment = DevEnvironment.instance();
        ecsEngine.addSystem(renderQueueSystem);
        ecsEngine.addSystem(updateSystem);
    }


    public void update() {
        ecsEngine.update(deltaTime);
        Instant end = Instant.now();
        deltaTime = Duration.between(last, end).toMillis() / 1000.0f;
        last = Instant.now();
    }

    public <T extends Entity> void addEntities(Collection<T> entities) {
        for (T entity : entities) {
            ecsEngine.addEntity(entity);
        }
    }

    public void addEntity(Entity entity) {
            ecsEngine.addEntity(entity);
    }
}
