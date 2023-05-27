package com.marshall.benjy.qld;

import com.badlogic.gdx.ApplicationAdapter;
import com.marshall.benjy.qld.core.game.ControlManager;
import com.marshall.benjy.qld.core.game.GameState;
import com.marshall.benjy.qld.core.game.RenderManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver extends ApplicationAdapter {
	private Game game;
	@Override
	public void create () {
		System.setProperty("log4j.configurationFactory", LogConfigurator.class.getName());
		game = new Game();
	}

	@Override
	public void render () {
		game.render();
	}

	@Override
	public void dispose () {
		game.dispose();
	}
}
