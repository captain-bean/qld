package com.marshall.benjy.qld.core.engine.render.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.marshall.benjy.qld.core.engine.datatype.ModelLoader;
import com.marshall.benjy.qld.core.engine.render.ModelRenderer;

public class ModelComponent implements Component {
    private String modelPath = "";
    private ModelInstance model;

    public void setModel(Model model){
        this.model = new ModelInstance(model);
    }

    public void setModel(String modelPath){
        this.modelPath = modelPath;
        ModelLoader.staticLoader.loadModel(modelPath);
    }

    public ModelInstance getInstance(){
        if(model == null){
            model = ModelLoader.staticLoader.getModelInstance(modelPath);
        }
        return model;
    }

}
