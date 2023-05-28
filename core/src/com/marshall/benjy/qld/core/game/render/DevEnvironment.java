package com.marshall.benjy.qld.core.game.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;

public class DevEnvironment {

	private PointLight light;
	private Environment environment;

	public DevEnvironment() {
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.2f, 0.2f, 0.2f, 1f));

		light = new PointLight().set(1.0f, 1.0f, 0.0f, 25f, 15f, 25f, 100f);
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

	public Environment getEnvironment() {
		return environment;
	}

}
