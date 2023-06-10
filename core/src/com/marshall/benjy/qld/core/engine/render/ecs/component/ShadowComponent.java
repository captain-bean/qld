package com.marshall.benjy.qld.core.engine.render.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.marshall.benjy.qld.core.engine.render.ModelLoader;
import com.marshall.benjy.qld.core.engine.render.ModelTexturer;

public class ShadowComponent implements Component {

    private String modelPath = "";
    private ModelInstance model;

    private String texturePath = "Textures/black.png";

    private boolean updated = false;

    public Matrix4 transform = new Matrix4();
    public enum ShadowType{
        NONE(""),
        ROUND("Models/rectangle.obj"),
        RECTANGLE("Models/rectangle.obj");

        String path;
        ShadowType(String s){
            path = s;
        }
    }
    public void setShadowType(ShadowType rectangle){
        this.modelPath = rectangle.path;
        ModelLoader.staticLoader.loadModel(modelPath);
        updated = true;
    }

    public void setShadowType(String modelPath){
        this.modelPath = modelPath;
        ModelLoader.staticLoader.loadModel(modelPath);
        updated = true;
    }

    public ModelInstance getInstance(){
        if(model == null || updated){
            model = ModelLoader.staticLoader.getModelInstance(modelPath);


            if (model != null) {
                transform = model.transform;
                updated = false;
            }
        }
        ModelTexturer.addTexture(model,"Textures/shadow.png",TextureAttribute.Diffuse);
        return model;
    }


}
