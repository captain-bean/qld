package com.marshall.benjy.qld.core.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.marshall.benjy.qld.core.game.Constants;

public class World {

	private PointLight light;
	private Environment environment;
	private Camera cam;

	public World() {
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

		cam = new PerspectiveCamera(75, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		cam.position.set(Constants.SCALE * 3.25f, Constants.SCALE * 3, Constants.SCALE * 8);
		cam.lookAt(Constants.SCALE * 3, Constants.SCALE * -2, Constants.SCALE * 3);
		cam.near = Constants.SCALE * .001f;
		cam.far = Constants.SCALE * 50f;
		cam.update();
	}

	public Environment getEnvironment() {
		return environment;
	}

	public Camera getCamera() {
		return cam;
	}
}
