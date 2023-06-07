package com.marshall.benjy.qld.core.game.logic.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.marshall.benjy.qld.core.engine.logic.command.CommandExecutor;
import com.marshall.benjy.qld.core.engine.logic.command.ExitAppCommand;
import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.logic.input.KeyboardInputReceiver;
import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.logic.commands.MovePlayerCommand;
import com.marshall.benjy.qld.core.game.render.QLDRenderState;
import com.marshall.benjy.qld.core.game.render.QLDRenderer;
import com.marshall.benjy.qld.core.game.state.QLDGameState;

public class QLDController {

    private QLDGameState state;
    private QLDRenderer renderer; //Todo Rename

    private PlayerController playerController;
    private LevelController levelController;

    public QLDController(QLDGameState state, QLDRenderer renderer) {
        this.state = state;
        this.renderer = renderer;

        this.levelController = new LevelController(state.getLevel());
        this.levelController.addTileDestroyedListener((tile) -> renderer.updateLevelInstances());

        this.playerController = new PlayerController(state.getPlayer());
        this.playerController.addMovementListener((p) -> {
            renderer.updatePlayerInstance();
        });

        CommandExecutor commandExecutor = new CommandExecutor();

        commandExecutor.addCommandTypeHandler(ExitAppCommand.TYPE, (command) -> Gdx.app.exit());
        commandExecutor.addCommandTypeHandler(MoveCameraCommand.TYPE, (command) -> executeMoveCamera((MoveCameraCommand) command));
        commandExecutor.addCommandTypeHandler(MovePlayerCommand.TYPE, (command) -> executeMovePlayer((MovePlayerCommand) command));

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

        Gdx.input.setInputProcessor(keyboardReceiver);
    }

    public void executeMovePlayer(MovePlayerCommand command) {
        Position oldPosition = state.getPlayer().getPosition();
        Position newPosition = new Position(oldPosition.getX() + command.getDeltaX(),
                oldPosition.getZ() + command.getDeltaZ());

        if(levelController.validPlayerPosition(newPosition)) {
            playerController.movePlayer(newPosition);
            levelController.blowUp(newPosition);
        }
    }

    public void executeMoveCamera(MoveCameraCommand command) {


    }
}
