package com.marshall.benjy.qld.core.engine.render.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class TransformComponent implements Component{

    public Vector3 position = new Vector3(0,0,0);
    public Vector3 scale = new Vector3(1.0f, 1.0f,1.0f);
    public Vector3 rotation = new Vector3(0,0,0);

}
