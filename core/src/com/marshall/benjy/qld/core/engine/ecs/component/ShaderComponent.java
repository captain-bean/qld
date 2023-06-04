package com.marshall.benjy.qld.core.engine.ecs.component;

import com.badlogic.ashley.core.Component;

public class ShaderComponent implements Component {

    public int shaderID = 0;

    public ShaderComponent(int id){
        shaderID = id;
    }

    public void setShaderID(int shaderID) {
        this.shaderID = shaderID;
    }
}
