package com.marshall.benjy.qld.core.engine.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.marshall.benjy.qld.core.engine.render.shaders.DefaultShader;
import com.marshall.benjy.qld.core.engine.render.shaders.QLDShaderProvider;
import org.apache.logging.log4j.core.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ModelRenderer {

    public static ModelRenderer Static_Renderer = new ModelRenderer();

    private List<RenderCall> rendererQueue = new ArrayList<>();

    private ModelBatch modelBatch;
    public ModelRenderer(){
        modelBatch = new ModelBatch();
    }


    /**
     *  Instantly Renders instances to screen
     * @param instances
     * @param shaderID
     */
    public void Render(List<ModelInstance> instances, int shaderID){
        modelBatch.render(instances, DevEnvironment.instance(), QLDShaderProvider.getShader(shaderID)); //TODO pass environment
    }

    /**
     * Renders one frame all at once
     * only capable of rendering objects that have been Queued
     */
    public void Render(){
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        modelBatch.begin(new DevCamera().getCamera());
        Shader shader = new DefaultShader();
        shader.init();
        for(RenderCall renderCall : rendererQueue){
            //Shader shader = QLDShaderProvider.getShader(renderCall.ShaderID);
            if(!(renderCall.instances.indexOf(null) > -1)) {
                modelBatch.render(renderCall.instances, DevEnvironment.instance(), shader); //TODO pass environment

            }
        }
        modelBatch.end();
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
