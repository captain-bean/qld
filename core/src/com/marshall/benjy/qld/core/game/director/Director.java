package com.marshall.benjy.qld.core.game.director;

import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.messaging.Publisher;
import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.input.commands.MovePlayerCommand;
import com.marshall.benjy.qld.core.game.messaging.Topics;
import com.marshall.benjy.qld.core.game.state.api.QLDGameState;
import com.marshall.benjy.qld.core.game.state.api.StateManager;
import com.marshall.benjy.qld.core.game.state.datatype.tile.Tile;
import com.marshall.benjy.qld.core.game.state.datatype.tile.TileTypes;
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

        if(!validPlayerPosition(newPosition)) {
            return;
        }

        movePlayerPublisher.sendMessage(Topics.MOVE_PLAYER, newPosition);

        stateManager.changeTile(newPosition, TileTypes.BLOWED_UP);

        // Change level if finished
    }

    private boolean validPlayerPosition(Position position) {
        QLDGameState state = stateManager.getState();

        Tile[][] tiles = state.getLevel().getTiles();
        if(position.getX() < 0 || position.getZ() < 0 || position.getX() >= tiles.length || position.getZ() >= tiles[0].length) {
            return false;
        }
        TileTypes tileType = tiles[position.getX()][position.getZ()].getType();
        if(tileType == TileTypes.BLOWED_UP || tileType == TileTypes.WOOD) {
            return false;
        }
        return true;
    }

    public void handleMoveCameraIntent(MoveCameraCommand command) {
        moveCameraPublisher.sendMessage(Topics.MOVE_CAMERA, command);
    }




}
