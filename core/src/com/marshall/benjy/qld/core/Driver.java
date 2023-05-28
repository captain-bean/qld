package com.marshall.benjy.qld.core;

import com.badlogic.gdx.ApplicationAdapter;

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
