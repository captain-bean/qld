package com.marshall.benjy.qld.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.marshall.benjy.qld.core.game.Application;

public class Driver extends ApplicationAdapter {
	private Application game;
	@Override
	public void create () {
		System.setProperty("log4j.configurationFactory", LogConfigurator.class.getName());
		game = new Application();
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
