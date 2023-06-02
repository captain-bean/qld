package com.marshall.benjy.qld.core.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.marshall.benjy.qld.core.engine.command.ExitAppCommand;
import com.marshall.benjy.qld.core.engine.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.input.KeyboardInputReceiver;
import com.marshall.benjy.qld.core.game.commands.MovePlayerCommand;
import com.marshall.benjy.qld.core.game.render.QLDRenderer;
import com.marshall.benjy.qld.core.game.state.QLDGameState;

public class QLDController {

    public QLDController(QLDGameState state, QLDRenderer renderer) {
        KeyboardInputReceiver keyboardReceiver = new KeyboardInputReceiver();
        keyboardReceiver.addKeyCommand(Input.Keys.ESCAPE, new ExitAppCommand());

        keyboardReceiver.addKeyCommand(Input.Keys.W, new MovePlayerCommand(state, 0, -1));
        keyboardReceiver.addKeyCommand(Input.Keys.A, new MovePlayerCommand(state, -1, 0));
        keyboardReceiver.addKeyCommand(Input.Keys.S, new MovePlayerCommand(state, 0, 1));
        keyboardReceiver.addKeyCommand(Input.Keys.D, new MovePlayerCommand(state, 1, 0));

        keyboardReceiver.addKeyCommand(Input.Keys.I, new MoveCameraCommand(renderer.getCamera(), new Vector3(0, 10, 0)));
        keyboardReceiver.addKeyCommand(Input.Keys.K, new MoveCameraCommand(renderer.getCamera(), new Vector3(0, -10, 0)));
        keyboardReceiver.addKeyCommand(Input.Keys.J, new MoveCameraCommand(renderer.getCamera(), new Vector3(-10, 0, 0)));
        keyboardReceiver.addKeyCommand(Input.Keys.L, new MoveCameraCommand(renderer.getCamera(), new Vector3(10, 0, 0)));
        keyboardReceiver.addKeyCommand(Input.Keys.U, new MoveCameraCommand(renderer.getCamera(), new Vector3(0, 0, -10)));
        keyboardReceiver.addKeyCommand(Input.Keys.O, new MoveCameraCommand(renderer.getCamera(), new Vector3(0, 0, 10)));

        Gdx.input.setInputProcessor(keyboardReceiver);
    }
}
