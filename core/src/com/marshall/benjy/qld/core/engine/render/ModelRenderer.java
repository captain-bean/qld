package com.marshall.benjy.qld.core.engine.render;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.marshall.benjy.qld.core.engine.render.shaders.QLDShaderProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelRenderer {

    public static ModelRenderer Static_Renderer;

    private List<RenderCall> rendererQueue = new ArrayList<>();

    private ModelBatch modelBatch;

    private DevCamera camera;
    public ModelRenderer(DevCamera camera){
        this.camera = camera;
        modelBatch = new ModelBatch();
        Static_Renderer = this;
    }

    /**
     * Renders one frame all at once
     * only capable of rendering objects that have been Queued
     */
    public void render(){
        modelBatch.begin(camera.getCamera());


        for(RenderCall renderCall : rendererQueue){
            Shader shader = QLDShaderProvider.getShader(renderCall.ShaderID);
            if(!(renderCall.instances.indexOf(null) > -1)) {
                if (shader != null) {
                    modelBatch.render(renderCall.instances, DevEnvironment.instance(), shader);
                    //modelBatch.render(renderCall.instances, DevEnvironment.instance(), shader); //TODO pass environment
                }

            }
        }
        modelBatch.end();
        rendererQueue.clear();
    }
    /**
     * Renders one frame all at once
     * only capable of rendering objects that have been Queued
     */
    public void render(boolean clearqueue){
        modelBatch.begin(camera.getCamera());

        for(RenderCall renderCall : rendererQueue){
            Shader shader = QLDShaderProvider.getShader(renderCall.ShaderID);
            if(!(renderCall.instances.indexOf(null) > -1)) {
                if (shader != null) {

                    modelBatch.render(renderCall.instances, DevEnvironment.instance(),shader); //TODO pass environment
                }

            }
        }
        modelBatch.end();
        if(clearqueue)
            rendererQueue.clear();
    }


    /**
     *  Queues all instances to be rendered
     * @param shaderID
     * @param instances
     */
    public void enqueue(int shaderID, ModelInstance... instances){
        for(RenderCall call : rendererQueue){
            if(call.ShaderID == shaderID){
                call.instances.addAll(Arrays.asList(instances));
                return;
            }
        }
        RenderCall newCall = new RenderCall();
        newCall.ShaderID = shaderID;
        newCall.instances.addAll(Arrays.asList(instances));
        rendererQueue.add(newCall);
    }



    private class RenderCall{
        List<ModelInstance> instances = new ArrayList<>();
        int ShaderID = 0;
    };

}
