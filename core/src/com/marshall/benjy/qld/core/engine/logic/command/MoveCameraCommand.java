package com.marshall.benjy.qld.core.engine.logic.command;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class MoveCameraCommand extends Command {
    private Camera camera;
    private Vector3 delta;

    public MoveCameraCommand(Camera camera, Vector3 delta){
        this.camera = camera;
        this.delta = delta;
    }

    public void execute() {
        camera.position.add(delta);
        camera.update();
    }
}
