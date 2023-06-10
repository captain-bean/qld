package com.marshall.benjy.qld.core.engine.render;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;

public class ModelTexturer {

    public static boolean addTexture(ModelInstance instance, String texturePath, long textureAttribType){

        if((TextureAttribute.getAttributeAlias(textureAttribType) != null) ) {
            Texture texture = new Texture(texturePath);
            TextureAttribute textureAttribute = new TextureAttribute(textureAttribType, texture);
            if(instance.materials.size <= 0){
                instance.materials.add(new Material());
            }
            Material material = instance.materials.get(0);
            material.set(textureAttribute);
            material.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
            return true;
        }
        return false;
    }
}
