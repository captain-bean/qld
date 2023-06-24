package com.marshall.benjy.qld.core.engine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.marshall.benjy.qld.core.game.Application;
import com.marshall.benjy.qld.core.game.QLDConfig;

public class Driver extends ApplicationAdapter {
	private Application game;
	@Override
	public void create () {
		System.setProperty("log4j.configurationFactory", LogConfigurator.class.getName());

		Gdx.graphics.setUndecorated(true);
		Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
		//Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);
		Gdx.graphics.setWindowedMode(480, 600);

		game = new Application(new QLDConfig(false));
	}

	@Override
	public void render () {
		game.render();
	}

	@Override
	public void dispose () {
		game.dispose();
	}

	@Override
	public void resize (int width, int height) {
		game.resize(width, height);
	}

}
