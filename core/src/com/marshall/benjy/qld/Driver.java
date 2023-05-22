package com.marshall.benjy.qld;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.marshall.benjy.qld.core.GameState;
import com.marshall.benjy.qld.core.RenderManager;

public class Driver extends ApplicationAdapter {

	GameState state;
	RenderManager renderManager;
	@Override
	public void create () {
		state = new GameState();
		renderManager = new RenderManager();
	}

	@Override
	public void render () {
		renderManager.render(state);
	}

	@Override
	public void dispose () {
		renderManager.dispose();
	}
}
