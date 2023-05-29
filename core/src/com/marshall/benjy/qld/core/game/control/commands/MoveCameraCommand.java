package com.marshall.benjy.qld.core.game.control.commands;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.marshall.benjy.qld.core.datatype.Position;
import com.marshall.benjy.qld.core.game.state.GameState;
import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.Player;

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
