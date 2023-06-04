package com.marshall.benjy.qld.core.engine.ecs.component;


import com.badlogic.ashley.core.Component;

import java.util.function.Function;

public class UpdateComponent implements Component {
    private final Function<Float,Void> updateMethod;

    public UpdateComponent(Function<Float,Void> updateMethod){
        this.updateMethod = updateMethod;
    }

    public void update(float deltaTime){
        if(updateMethod != null){
            updateMethod.apply(deltaTime);
        }
    }


}
