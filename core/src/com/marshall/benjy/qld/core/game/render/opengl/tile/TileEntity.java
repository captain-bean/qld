package com.marshall.benjy.qld.core.game.render.opengl.tile;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.marshall.benjy.qld.core.engine.render.ecs.component.IDComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.component.ModelComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.component.TransformComponent;
import com.marshall.benjy.qld.core.engine.render.ecs.entity.GameObject;

public class TileEntity extends GameObject {

    public TileEntity(String path){
        super();
        getComponent(ModelComponent.class).setModel(path);
    }

    public void setTileModel(String path){
        getComponent(ModelComponent.class).setModel(path);
    }

    public void translate(Vector3 translation){

        getComponent(TransformComponent.class).transform.setToTranslation(translation);
    }

    public void translate(float x,float y,float z){
        getComponent(TransformComponent.class).transform.setToTranslation(x,y,z);
    }


}
