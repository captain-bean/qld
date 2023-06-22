package com.marshall.benjy.qld.core.game.logic.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.marshall.benjy.qld.core.engine.logic.command.CommandExecutor;
import com.marshall.benjy.qld.core.engine.logic.command.ExitAppCommand;
import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.logic.input.KeyboardInputReceiver;
import com.marshall.benjy.qld.core.game.logic.commands.MovePlayerCommand;

public class QLDInputReceiver {

    public QLDInputReceiver(CommandExecutor commandExecutor) {
        KeyboardInputReceiver keyboardReceiver = new KeyboardInputReceiver(commandExecutor);

        keyboardReceiver.addKeyCommand(Input.Keys.ESCAPE, new ExitAppCommand());

        keyboardReceiver.addKeyCommand(Input.Keys.W, new MovePlayerCommand(0, -1));
        keyboardReceiver.addKeyCommand(Input.Keys.A, new MovePlayerCommand(-1, 0));
        keyboardReceiver.addKeyCommand(Input.Keys.S, new MovePlayerCommand(0, 1));
        keyboardReceiver.addKeyCommand(Input.Keys.D, new MovePlayerCommand(1, 0));

        keyboardReceiver.addKeyCommand(Input.Keys.I, new MoveCameraCommand(new Vector3(0, 10, 0)));
        keyboardReceiver.addKeyCommand(Input.Keys.K, new MoveCameraCommand(new Vector3(0, -10, 0)));
        keyboardReceiver.addKeyCommand(Input.Keys.J, new MoveCameraCommand(new Vector3(-10, 0, 0)));
        keyboardReceiver.addKeyCommand(Input.Keys.L, new MoveCameraCommand(new Vector3(10, 0, 0)));
        keyboardReceiver.addKeyCommand(Input.Keys.U, new MoveCameraCommand(new Vector3(0, 0, -10)));
        keyboardReceiver.addKeyCommand(Input.Keys.O, new MoveCameraCommand(new Vector3(0, 0, 10)));

        if (Gdx.input != null) {
            Gdx.input.setInputProcessor(keyboardReceiver);
        }
    }
}


