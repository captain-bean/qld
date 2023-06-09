package com.marshall.benjy.qld.core.engine.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.state.Constants;
import com.marshall.benjy.qld.core.game.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DevCamera {

    private static final Logger logger = LogManager.getLogger(DevCamera.class);

    private Viewport viewport;
    private Camera camera;

    public DevCamera() {
        camera = new PerspectiveCamera(70, 600, 480);
        viewport = new ScreenViewport(camera);

        camera = viewport.getCamera();

        camera.position.set(Constants.SCALE * 25, Constants.SCALE * 15, Constants.SCALE * 25);
        camera.lookAt(Constants.SCALE * 0, Constants.SCALE * 0, Constants.SCALE * 0);
        camera.near = Constants.SCALE * .1f;
        camera.far = Constants.SCALE * 500f;
        camera.update();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, false);
        viewport.getCamera().update();
    }

    public void moveCamera(MoveCameraCommand command) {
        camera.position.add(command.getDelta());
        camera.update();
        logger.info("Camera position: " + camera.position);
    }

    public Camera getCamera() {
        return viewport.getCamera();
    }

    public Viewport getViewport() {
        return viewport;
    }

}
