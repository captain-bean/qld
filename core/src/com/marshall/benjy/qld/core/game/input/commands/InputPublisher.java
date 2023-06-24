package com.marshall.benjy.qld.core.game.input.commands;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.logic.input.KeyboardInputReceiver;
import com.marshall.benjy.qld.core.game.messaging.QLDPublisher;
import com.marshall.benjy.qld.core.game.messaging.Topics;
import org.apache.camel.CamelContext;

public class InputPublisher {

    public InputPublisher(CamelContext context) {
        KeyboardInputReceiver keyboardReceiver = new KeyboardInputReceiver();

        keyboardReceiver.putKeyConsumer(Input.Keys.ESCAPE, (key) -> Gdx.app.exit());

        QLDPublisher<MovePlayerCommand> movePlayerCommandQLDPublisher
                = new QLDPublisher<>(context);

        keyboardReceiver.putKeyConsumer(Input.Keys.W, (key) -> {
            movePlayerCommandQLDPublisher.sendMessage(Topics.MOVE_PLAYER, new MovePlayerCommand(0, -1));
        });
        keyboardReceiver.putKeyConsumer(Input.Keys.A, (key) -> {
            movePlayerCommandQLDPublisher.sendMessage(Topics.MOVE_PLAYER, new MovePlayerCommand(-1, 0));
        });
        keyboardReceiver.putKeyConsumer(Input.Keys.S, (key) -> {
            movePlayerCommandQLDPublisher.sendMessage(Topics.MOVE_PLAYER, new MovePlayerCommand(0, 1));
        });
        keyboardReceiver.putKeyConsumer(Input.Keys.D, (key) -> {
            movePlayerCommandQLDPublisher.sendMessage(Topics.MOVE_PLAYER, new MovePlayerCommand(1, 0));
        });


        QLDPublisher<MoveCameraCommand> moveCameraCommandQLDPublisher
                = new QLDPublisher<>(context);

        keyboardReceiver.putKeyConsumer(Input.Keys.I, (key) -> {
            moveCameraCommandQLDPublisher.sendMessage(Topics.MOVE_CAMERA,
                    new MoveCameraCommand(new Vector3(0, 10, 0)));
        });
        keyboardReceiver.putKeyConsumer(Input.Keys.K, (key) -> {
            moveCameraCommandQLDPublisher.sendMessage(Topics.MOVE_CAMERA,
                    new MoveCameraCommand(new Vector3(0, -10, 0)));
        });
        keyboardReceiver.putKeyConsumer(Input.Keys.J, (key) -> {
            moveCameraCommandQLDPublisher.sendMessage(Topics.MOVE_CAMERA,
                    new MoveCameraCommand(new Vector3(-10, 0, 0)));
        });
        keyboardReceiver.putKeyConsumer(Input.Keys.L,(key) -> {
            moveCameraCommandQLDPublisher.sendMessage(Topics.MOVE_CAMERA,
                    new MoveCameraCommand(new Vector3(10, 0, 0)));
        });
        keyboardReceiver.putKeyConsumer(Input.Keys.U, (key) -> {
            moveCameraCommandQLDPublisher.sendMessage(Topics.MOVE_CAMERA,
                    new MoveCameraCommand(new Vector3(0, 0, -10)));
        });
        keyboardReceiver.putKeyConsumer(Input.Keys.O, (key) -> {
            moveCameraCommandQLDPublisher.sendMessage(Topics.MOVE_CAMERA,
                    new MoveCameraCommand(new Vector3(0, 0, 10)));
        });

        if (Gdx.input != null) {
            Gdx.input.setInputProcessor(keyboardReceiver);
        }
    }
}
