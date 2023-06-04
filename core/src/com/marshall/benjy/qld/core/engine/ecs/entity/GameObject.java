package com.marshall.benjy.qld.core.engine.ecs.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g3d.Model;
import com.marshall.benjy.qld.core.engine.ecs.component.IDComponent;
import com.marshall.benjy.qld.core.engine.ecs.component.ModelComponent;
import com.marshall.benjy.qld.core.engine.ecs.component.ShaderComponent;
import com.marshall.benjy.qld.core.engine.ecs.component.TransformComponent;

public class GameObject extends QLDEntity {

    public GameObject(){
        super();
        add(new ModelComponent());
        add(new ShaderComponent(0));
    }

    public void setModel(Model model){
        getComponent(ModelComponent.class).setModel(model);
    }
}
