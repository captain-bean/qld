package com.marshall.benjy.qld.core.game.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;

public class DevEnvironment {

	private static Environment environment;
	private static void createEnvironment() {
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.2f, 0.2f, 0.2f, 1f));

		PointLight pointLight = new PointLight().set(1f, .5f, 0f, 25f, 15f, 25f, 5000f);
		environment.add(pointLight);



		DirectionalLight directionalLight = new DirectionalLight().set(.1f,.1f,.1f,0f,-1f,-1f);
		environment.add(directionalLight);
		directionalLight = new DirectionalLight().set(.5f,.5f,.5f,-1f,-1f,-1f);
		environment.add(directionalLight);

	}

	public static Environment instance() {
		if(environment == null) {
			createEnvironment();
		}
		return environment;
	}

}
