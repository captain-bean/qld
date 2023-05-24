package com.marshall.benjy.qld.core.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;

public class World {
    private Environment environment;
    private OrthographicCamera cam;
    public World() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        cam.position.set(100f, 50f, 100f);
        cam.lookAt(0, 0, 0);
        cam.near = .00001f;
        cam.far = 100000f;
        cam.zoom = .2f;
        cam.update();
    }

    public Environment getEnvironment() {
        return environment;
    }
    public OrthographicCamera getCamera() {
        return cam;
    }
}
