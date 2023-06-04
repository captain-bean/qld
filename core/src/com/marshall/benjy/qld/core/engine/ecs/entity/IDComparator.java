package com.marshall.benjy.qld.core.engine.ecs.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.marshall.benjy.qld.core.engine.ecs.component.IDComponent;

import java.util.Comparator;
import java.util.UUID;

public class IDComparator implements Comparator<Entity> {

    private ComponentMapper<IDComponent> cmID;

    public IDComparator(){
        cmID = ComponentMapper.getFor(IDComponent.class);
    }

    @Override
    public int compare(Entity entity0, Entity entity1) {
        UUID id0 = cmID.get(entity0).id;
        UUID id1 = cmID.get(entity1).id;

        return id0.compareTo(id1);
    }
}
