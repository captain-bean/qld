package com.marshall.benjy.qld;

import com.badlogic.gdx.ApplicationAdapter;
import com.marshall.benjy.qld.core.game.ControlManager;
import com.marshall.benjy.qld.core.game.GameState;
import com.marshall.benjy.qld.core.game.RenderManager;

public class Driver extends ApplicationAdapter {

	GameState state;
	RenderManager renderManager;

	ControlManager controlManager;
	@Override
	public void create () {
		state = new GameState();
		renderManager = new RenderManager();
		controlManager = new ControlManager();
	}

	@Override
	public void render () {
		state = controlManager.acceptInputs(state);
		renderManager.render(state);
	}

	@Override
	public void dispose () {
		renderManager.dispose();
	}
}
