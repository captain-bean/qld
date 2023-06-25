package com.marshall.benjy.qld.core.game.director;

import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.messaging.Publisher;
import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.input.commands.MovePlayerCommand;
import com.marshall.benjy.qld.core.game.messaging.Topics;
import com.marshall.benjy.qld.core.game.state.api.QLDGameState;
import com.marshall.benjy.qld.core.game.state.api.StateManager;
import com.marshall.benjy.qld.core.game.state.datatype.Player;
import com.marshall.benjy.qld.core.game.state.datatype.tile.Tile;
import com.marshall.benjy.qld.core.game.state.datatype.tile.TileTypes;
import com.marshall.benjy.qld.core.game.state.generator.LegacyLevelGenerator;
import org.apache.camel.CamelContext;

import javax.swing.plaf.nimbus.State;

public class Director {

    private StateManager stateManager;
    private Publisher<Position> movePlayerPublisher;
    private Publisher<MoveCameraCommand> moveCameraPublisher;
    public Director(CamelContext context, StateManager stateManager) {
        this.stateManager = stateManager;

        this.movePlayerPublisher = new Publisher<>(context);
        this.moveCameraPublisher = new Publisher<>(context);

        DirectorBridge bridge = new DirectorBridge(context, this);

        // Listen for events and trigger sequences

        // When player moved, ask for tile destruction

        // When tile destroyed, check for level completion

        // When level completed, change level
    }

    public void handleMovePlayerIntent(MovePlayerCommand command) {
        QLDGameState state = stateManager.getState();

        Position oldPosition = state.getPlayer().getPosition();
        Position newPosition = new Position(oldPosition.getX() + command.getDeltaX(),
                oldPosition.getZ() + command.getDeltaZ());

        if(!PlayerMovementValidator.validMove(state, newPosition)) {
            return;
        }

        movePlayerPublisher.sendMessage(Topics.MOVE_PLAYER, newPosition);

        stateManager.changeTile(newPosition, TileTypes.BLOWED_UP);

        if(LevelFinishedChecker.isFinished(state.getLevel())) {
            stateManager.changeLevel(LegacyLevelGenerator.generateLegacyLevel(15, 15));
            movePlayerPublisher.sendMessage(Topics.MOVE_PLAYER, state.getLevel().getStartPosition());
        }
    }



    public void handleMoveCameraIntent(MoveCameraCommand command) {
        moveCameraPublisher.sendMessage(Topics.MOVE_CAMERA, command);
    }

}
