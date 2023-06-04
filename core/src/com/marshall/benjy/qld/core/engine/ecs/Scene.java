package com.marshall.benjy.qld.core.engine.ecs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.marshall.benjy.qld.core.engine.ecs.system.RenderQueueSystem;
import com.marshall.benjy.qld.core.engine.ecs.system.UpdateSystem;
import com.marshall.benjy.qld.core.engine.render.DevEnvironment;

import java.time.Duration;
import java.time.Instant;
import java.util.Timer;

public class Scene {

    private Engine ecsEngine;
    private RenderQueueSystem renderQueueSystem;
    private UpdateSystem updateSystem;
    private Environment environment;

    private float deltaTime = 0;
    private Instant last;

    public Scene(){
        ecsEngine = new Engine();
        renderQueueSystem = new RenderQueueSystem();
        updateSystem = new UpdateSystem();
        environment = DevEnvironment.instance();
        ecsEngine.addSystem(renderQueueSystem);
        ecsEngine.addSystem(updateSystem);
    }


    public void update(){
        ecsEngine.update(deltaTime);
        Instant end = Instant.now();
        deltaTime = Duration.between(last,end).toMillis() / 1000.0f;
        last = Instant.now();
    }


}
