package com.marshall.benjy.qld.core.engine.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.marshall.benjy.qld.core.engine.ecs.component.ModelComponent;
import com.marshall.benjy.qld.core.engine.ecs.component.ShaderComponent;
import com.marshall.benjy.qld.core.engine.ecs.component.TransformComponent;
import com.marshall.benjy.qld.core.engine.ecs.entity.ZComparator;
import com.marshall.benjy.qld.core.engine.render.ModelRenderer;

public class RenderQueueSystem extends SortedIteratingSystem{

    public RenderQueueSystem(){
        super(Family.all(ModelComponent.class, ShaderComponent.class, TransformComponent.class).get(), new ZComparator());
    }


    //Gets entities in order from back to front
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        int shaderId = entity.getComponent(ShaderComponent.class).shaderID;
        ModelInstance modelInstance = entity.getComponent(ModelComponent.class).getInstance();
        ModelRenderer.Static_Renderer.enqueue(shaderId, modelInstance);
    }
}
