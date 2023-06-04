package com.marshall.benjy.qld.core.engine.render.shaders;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.utils.ShaderProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QLDShaderProvider {

    public static Map<Integer,QLDShader> COMPILED_SHADERS = new HashMap<>();

    public static QLDShader getShader(int id) {
        if(COMPILED_SHADERS.containsKey(id)) {
            return COMPILED_SHADERS.get(id);
        }
        return null;
    }
}
