package com.marshall.benjy.qld.core.engine.render.ecs.component;


import com.badlogic.ashley.core.Component;

import java.util.function.Consumer;
import java.util.function.Function;

public class UpdateComponent implements Component {
    private final Consumer<Float> updateMethod;

    public UpdateComponent(Consumer<Float> updateMethod){
        this.updateMethod = updateMethod;
    }

    public void update(float deltaTime){
        if(updateMethod != null){
            updateMethod.accept(deltaTime);
        }
    }


}
