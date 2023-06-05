package com.marshall.benjy.qld.core.engine.render;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class ModelLoader {

    private AssetManager assetManager;

    public static ModelLoader staticLoader;

    public ModelLoader(AssetManager assetManager){
            this.assetManager = assetManager;
    }

    public ModelLoader(){
            this.assetManager = new AssetManager();
    }

    public static void createStaticLoader(){
        staticLoader = new ModelLoader();
    }

    public static void createStaticLoader(AssetManager manager){
        staticLoader = new ModelLoader(manager);
    }

    public void loadModel(String filename){

        if(assetManager.isLoaded(filename,Model.class))
           return;

        assetManager.load(filename, Model.class);

    }

    /**
    @return null if model isnt loaded yet
     */
    public Model getModel(String filename){
        if(assetManager.isLoaded(filename,Model.class))
            return assetManager.get(filename,Model.class);

        return null;
    }

    /**
     @return null if model isnt loaded yet
     */
    public ModelInstance getModelInstance(String filename){
        if(assetManager.isLoaded(filename,Model.class))
            return new ModelInstance(assetManager.get(filename,Model.class));

        return null;
    }

    public ModelInstance getModelInstance(Model model){
        return new ModelInstance(model);
    }

    /**
     * Forces Loading of Model (not for production use)
     * @param filename
     * @return new Model (cannot be null)
     */
    public Model getModelNow(String filename){

        if(assetManager.isLoaded(filename,Model.class))
            return assetManager.get(filename,Model.class);

        assetManager.finishLoadingAsset(filename);
        return assetManager.get(filename,Model.class);
    }

    /**
     * Forces Loading of Model (not for production use)
     * @param filename
     * @return new Model Instance (cannot be null unless ModelLoader has never been initialized)
     */
    public ModelInstance getModelInstanceNow(String filename){


        if(assetManager.isLoaded(filename,Model.class))
            return new ModelInstance(assetManager.get(filename,Model.class));

        assetManager.finishLoadingAsset(filename);
        return new ModelInstance(assetManager.get(filename,Model.class));
    }

    public void finishLoading(){
        assetManager.finishLoading();
    }

    public void finishLoadingAsset(String filename){
        assetManager.finishLoadingAsset(filename);
    }

    public AssetManager getAssetManager(){
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager){
            this.assetManager = assetManager;
    }


}
