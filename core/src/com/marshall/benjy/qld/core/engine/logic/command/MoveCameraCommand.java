package com.marshall.benjy.qld.core.engine.logic.command;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class MoveCameraCommand extends Command {

    public static final String TYPE = "move-camera";
    private Vector3 delta;

    public MoveCameraCommand(Vector3 delta){
        super(TYPE);
        this.delta = delta;
    }

    public Vector3 getDelta() {
        return delta;
    }

}
