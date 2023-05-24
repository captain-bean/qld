package com.marshall.benjy.qld.core.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class TestShader implements Shader {
	ShaderProgram program;
	Camera camera;
	RenderContext context;
	int u_projTrans;
	int u_worldTrans;
	int u_color;
	
	@Override
	public void init () {
		String vert = Gdx.files.internal("Shaders/test.vert").readString();
		String frag = Gdx.files.internal("Shaders/test.frag").readString();
		program = new ShaderProgram(vert, frag);
		if (!program.isCompiled())
			throw new GdxRuntimeException(program.getLog());
		u_projTrans = program.getUniformLocation("u_projTrans");
		u_worldTrans = program.getUniformLocation("u_worldTrans");
		u_color = program.getUniformLocation("u_color");
	}
	
	@Override
	public void dispose () {
		program.dispose();
	}
	
	@Override
	public void begin(Camera camera, RenderContext context) {
		this.camera = camera;
		this.context = context;
		program.bind();
		program.setUniformMatrix(u_projTrans, camera.combined);
		context.setDepthTest(GL20.GL_LEQUAL);
		context.setCullFace(GL20.GL_BACK);
	}
	
	@Override
	public void render(Renderable renderable) {
	    program.setUniformMatrix(u_worldTrans, renderable.worldTransform);
	    
	    Material material = renderable.material;
	    ColorAttribute colorAttribute = (ColorAttribute)material.get(ColorAttribute.Diffuse);
	    
	    if (colorAttribute != null) {
	        program.setUniformf(u_color, colorAttribute.color.r, colorAttribute.color.g, colorAttribute.color.b);
	        
	    }
	    renderable.meshPart.render(program);
	    
	}
	
	
	
	@Override
	public void end () {
	
	}
	
	@Override
	public int compareTo (Shader other) {
		return 0;
	}
	@Override
	public boolean canRender (Renderable instance) {
		return true;
	}
}
