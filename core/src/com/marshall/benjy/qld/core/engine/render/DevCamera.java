package com.marshall.benjy.qld.core.engine.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.marshall.benjy.qld.core.engine.datatype.Constants;

public class DevCamera {

    private Viewport viewport;
    private Camera camera;

    public DevCamera() {
        camera = new PerspectiveCamera(70, 600, 480);
        viewport = new ScreenViewport(camera);

        camera.position.set(Constants.SCALE * 6.25f, Constants.SCALE * 5, Constants.SCALE * 6.25f);
        camera.lookAt(Constants.SCALE * 3, Constants.SCALE * 0, Constants.SCALE * 3);
        camera.near = Constants.SCALE * .001f;
        camera.far = Constants.SCALE * 50f;
        camera.update();
    }

    public void resize(int width, int height) {
        //viewport.update(width, height, true);
        //viewport.getCamera().update();
    }

    public Camera getCamera() {
        return viewport.getCamera();
    }

    public Viewport getViewport() {
        return viewport;
    }

}
