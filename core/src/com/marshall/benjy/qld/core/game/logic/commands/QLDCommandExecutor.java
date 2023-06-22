package com.marshall.benjy.qld.core.game.logic.commands;

import com.badlogic.gdx.Gdx;
import com.marshall.benjy.qld.core.engine.logic.command.CommandExecutor;
import com.marshall.benjy.qld.core.engine.logic.command.ExitAppCommand;
import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.logic.control.LevelController;
import com.marshall.benjy.qld.core.game.logic.control.PlayerController;
import com.marshall.benjy.qld.core.game.logic.control.QLDController;
import com.marshall.benjy.qld.core.game.logic.generator.LegacyLevelGenerator;
import com.marshall.benjy.qld.core.game.render.QLDRenderer;
import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.QLDGameState;

public class QLDCommandExecutor {

    private CommandExecutor commandExecutor;
    private QLDGameState state;
    private QLDRenderer renderer;
    private QLDController qldController;

    public QLDCommandExecutor(QLDGameState state, QLDRenderer renderer, QLDController qldController) {
        this.state = state;
        this.renderer = renderer;
        this.qldController = qldController;

        commandExecutor = new CommandExecutor();
        commandExecutor.addCommandTypeHandler(ExitAppCommand.TYPE, (command) -> Gdx.app.exit());
        commandExecutor.addCommandTypeHandler(MoveCameraCommand.TYPE, (command) -> executeMoveCamera((MoveCameraCommand) command));
        commandExecutor.addCommandTypeHandler(MovePlayerCommand.TYPE, (command) -> executeMovePlayer((MovePlayerCommand) command));
    }

    public CommandExecutor getExecutor() {
        return commandExecutor;
    }

    public void executeMovePlayer(MovePlayerCommand command) {
        Position oldPosition = state.getPlayer().getPosition();
        Position newPosition = new Position(oldPosition.getX() + command.getDeltaX(),
                oldPosition.getZ() + command.getDeltaZ());

        LevelController levelController = qldController.getLevelController();
        PlayerController playerController = qldController.getPlayerController();

        if(levelController.validPlayerPosition(newPosition)) {
            playerController.movePlayer(newPosition);
            levelController.blowUp(newPosition);

            if(newPosition.equals(state.getLevel().getEndPosition())) {
                Level newLevel = LegacyLevelGenerator.generateLegacyLevel(15, 15);
                levelController.changeLevel(newLevel);
            }
        }
    }

    public void executeMoveCamera(MoveCameraCommand command) {
        renderer.moveCamera(command);
    }
}
