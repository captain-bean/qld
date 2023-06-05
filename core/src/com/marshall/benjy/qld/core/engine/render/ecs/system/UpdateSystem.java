package com.marshall.benjy.qld.core.engine.render.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.marshall.benjy.qld.core.engine.render.ecs.component.UpdateComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.IDComparator;

public class UpdateSystem extends SortedIteratingSystem {

    ComponentMapper<UpdateComponent> updateComponentMapper;

    public UpdateSystem(){
        super(Family.all(UpdateComponent.class).get(), new IDComparator());
        updateComponentMapper = ComponentMapper.getFor(UpdateComponent.class);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        UpdateComponent uc = updateComponentMapper.get(entity);
        uc.update(deltaTime);
    }

}
