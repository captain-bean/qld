package com.marshall.benjy.qld.core.engine.render.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.marshall.benjy.qld.core.engine.render.ecs.component.ModelComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.component.ShaderComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.component.TransformComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.ZComparator;
import com.marshall.benjy.qld.core.engine.render.ModelRenderer;

public class RenderQueueSystem extends SortedIteratingSystem{

    private int index = 0;

    public RenderQueueSystem(){
        super(Family.all(ModelComponent.class, ShaderComponent.class, TransformComponent.class).get(), new ZComparator());
    }


    //Gets entities in order from back to front
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        int shaderId = entity.getComponent(ShaderComponent.class).shaderID;
        ModelInstance modelInstance = entity.getComponent(ModelComponent.class).getInstance();
        if(modelInstance != null) {
            ModelRenderer.Static_Renderer.enqueue(shaderId, modelInstance);
            modelInstance.transform = entity.getComponent(TransformComponent.class).transform;
        }
        index--;
        if(index <= 0)
            ModelRenderer.Static_Renderer.Render();
    }

    @Override
    public void update(float deltaTime) {
        index = getEntities().size();
        super.update(deltaTime);

    }
}
