package com.marshall.benjy.qld.core.engine.render.shaders;

import com.badlogic.gdx.graphics.g3d.Shader;

public abstract class QLDShader implements Shader {
    public static int SHADER_ID;

    public QLDShader(){
        init();
        QLDShaderProvider.COMPILED_SHADERS.put(SHADER_ID,this);
    }

}
