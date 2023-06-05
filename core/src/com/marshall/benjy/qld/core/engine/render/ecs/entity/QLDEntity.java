package com.marshall.benjy.qld.core.engine.render.ecs.entity;

import com.badlogic.ashley.core.Entity;
import com.marshall.benjy.qld.core.engine.render.ecs.component.IDComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.component.TransformComponent;

public class QLDEntity extends Entity {

    public QLDEntity(){
        super();
        add(new IDComponent());
        add(new TransformComponent());
    }
}
