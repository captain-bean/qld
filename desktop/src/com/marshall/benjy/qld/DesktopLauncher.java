package com.marshall.benjy.qld;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.marshall.benjy.qld.core.engine.Driver;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Quantum Loot Dungeon");
		config.setOpenGLEmulation(Lwjgl3ApplicationConfiguration.GLEmulation.GL30,4,1);
		new Lwjgl3Application(new Driver(), config);
	}
}
