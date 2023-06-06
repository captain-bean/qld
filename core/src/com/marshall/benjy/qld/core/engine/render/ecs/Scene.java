package com.marshall.benjy.qld.core.engine.render.ecs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.marshall.benjy.qld.core.engine.render.ModelLoader;
import com.marshall.benjy.qld.core.engine.render.ModelRenderer;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.GameObject;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.RenderTestEntity;
import com.marshall.benjy.qld.core.engine.render.ecs.system.RenderQueueSystem;
import com.marshall.benjy.qld.core.engine.render.ecs.system.UpdateSystem;
import com.marshall.benjy.qld.core.engine.render.DevEnvironment;

import java.time.Duration;
import java.time.Instant;

public class Scene {

    private Engine ecsEngine;
    private RenderQueueSystem renderQueueSystem;
    private UpdateSystem updateSystem;
    private Environment environment;

    private float deltaTime = 0;
    private Instant last = Instant.now();

    public Scene(){
        ModelLoader.createStaticLoader();
        ecsEngine = new Engine();
        renderQueueSystem = new RenderQueueSystem();
        updateSystem = new UpdateSystem();
        environment = DevEnvironment.instance();
        ecsEngine.addSystem(renderQueueSystem);
        ecsEngine.addSystem(updateSystem);

        for(int i = 0; i <10; i++) {
            ecsEngine.addEntity(new RenderTestEntity());
        }
    }


    public void update(){
        ecsEngine.update(deltaTime);
        Instant end = Instant.now();
        deltaTime = Duration.between(last,end).toMillis() / 1000.0f;
        last = Instant.now();
    }


}
