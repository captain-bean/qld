package com.marshall.benjy.qld.core.engine.render.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.marshall.benjy.qld.core.engine.render.ModelLoader;
import com.marshall.benjy.qld.core.engine.render.ModelTexturer;

public class ModelComponent implements Component {
    private String modelPath = "";
    private ModelInstance model;

    private String texturePath = "";

    private boolean updated = false;

    public void setModel(Model model){
        this.model = new ModelInstance(model);
        updated = true;
    }

    public void setModel(String modelPath){
        this.modelPath = modelPath;
        ModelLoader.staticLoader.loadModel(modelPath);
        updated = true;
    }

    public ModelInstance getInstance(){
        if(model == null || updated){
            model = ModelLoader.staticLoader.getModelInstance(modelPath);
            if(!texturePath.isEmpty()){
                ModelTexturer.addTexture(model,texturePath, TextureAttribute.Diffuse);//TODO add support for other texture types
            }
            if (model != null) {
                updated = false;
            }
        }

        return model;
    }



    public void setTexturePath(String path){ //Sets diffuse texture path
        texturePath = path;
        if(model != null){
            ModelTexturer.addTexture(model,texturePath, TextureAttribute.Diffuse);
        }
    }

    public void setTexturePath(String path, long type){
        texturePath = path;
        if(model != null){
            ModelTexturer.addTexture(model,texturePath, type);
        }
    }

}
