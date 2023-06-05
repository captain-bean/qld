package com.marshall.benjy.qld.core.engine.render.ecs.entity;

import com.badlogic.gdx.graphics.g3d.Model;
import com.marshall.benjy.qld.core.engine.render.ecs.component.ModelComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.component.ShaderComponent;

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
