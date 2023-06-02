package com.marshall.benjy.qld.core.game.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.marshall.benjy.qld.core.game.Constants;

public class DevCamera {

    private static Camera camera;
    private static void createCam() {
        camera = new PerspectiveCamera(75, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.position.set(Constants.SCALE * 6.25f, Constants.SCALE * 5, Constants.SCALE * 6.25f);
        camera.lookAt(Constants.SCALE * 3, Constants.SCALE * 0, Constants.SCALE * 3);
        camera.near = Constants.SCALE * .001f;
        camera.far = Constants.SCALE * 50f;
        camera.update();

    }

    public static Camera instance() {
        if (camera == null) {
            createCam();
        }
        return camera;
    }
}
