package com.marshall.benjy.qld.core.game.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;

public class DevEnvironment {

	private static Environment environment;
	private static void createEnvironment() {
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.2f, 0.2f, 0.2f, 1f));

		PointLight light = new PointLight().set(1.0f, 1.0f, 0.0f, 25f, 15f, 25f, 100f);
		environment.add(light);
		light = new PointLight().set(1.0f, 0.0f, 0f, -25f, 15f, 25f, 100f);
		environment.add(light);
		light = new PointLight().set(0.0f, 1.0f, 0f, 25f, 15f, -25f, 100f);
		environment.add(light);
		light = new PointLight().set(0.0f, 0.0f, 1f, -25f, 15f, -25f, 100f);
		environment.add(light);
		light = new PointLight().set(1.0f, 1.0f, 1f, 50f, 15f, 50f, 10f);
		environment.add(light);
	}

	public static Environment instance() {
		if(environment == null) {
			createEnvironment();
		}
		return environment;
	}

}
