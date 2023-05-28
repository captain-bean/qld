package com.marshall.benjy.qld.core.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;

public class World {
	private Environment environment;
	private PerspectiveCamera cam;

	public World() {
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		cam = new PerspectiveCamera(50, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		cam.position.set(50f, 50f, 50f);
		cam.lookAt(0, 0, 0);
		cam.near = .1f;
		cam.far = 1000f;
		cam.update();
	}

	public Environment getEnvironment() {
		return environment;
	}

	public PerspectiveCamera getCamera() {
		return cam;
	}
}
