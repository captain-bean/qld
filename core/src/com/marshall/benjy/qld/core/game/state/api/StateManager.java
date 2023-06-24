package com.marshall.benjy.qld.core.game.state.api;

import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.messaging.QLDPublisher;
import com.marshall.benjy.qld.core.game.messaging.Topics;
import com.marshall.benjy.qld.core.game.state.datatype.Level;
import com.marshall.benjy.qld.core.game.state.datatype.tile.Tile;
import com.marshall.benjy.qld.core.game.state.datatype.tile.TileTypes;
import com.marshall.benjy.qld.core.game.state.generator.QLDGameStateFactory;
import org.apache.camel.CamelContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateManager {

    private static final Logger logger = LogManager.getLogger(StateManager.class);

    private QLDGameState state;
    private QLDPublisher<Position> playerMovedPublisher;
    private QLDPublisher<Position> tileChangedPublisher;
    private QLDPublisher<String> levelChangedPublisher;
    private StateBridge stateBridge;

    public StateManager(CamelContext context) {
        state = QLDGameStateFactory.development();
        playerMovedPublisher = new QLDPublisher<>(context);
        tileChangedPublisher = new QLDPublisher<>(context);
        levelChangedPublisher = new QLDPublisher<>(context);
        stateBridge = new StateBridge(context, this);

        movePlayer(0, 0);
    }

    public QLDGameState getState() {
        return state;
    }

    // TODO how to move this logic out into more of a game flow director?
    public void movePlayer(int deltaX, int deltaZ) {
        Position oldPosition = state.getPlayer().getPosition();
        Position newPosition = new Position(oldPosition.getX() + deltaX, oldPosition.getZ() + deltaZ);

        if(!validPlayerPosition(newPosition)) {
            return;
        }
        logger.info("Updating player's position: " + newPosition);
        state.getPlayer().setPosition(newPosition);
        playerMovedPublisher.sendMessage(Topics.PLAYER_MOVED, newPosition);

        changeTile(newPosition, TileTypes.BLOWED_UP);

        // Change level if finished
    }

    protected void changeTile(Position position, TileTypes tileType) {
        state.getLevel().setTile(position, new Tile(tileType));
        tileChangedPublisher.sendMessage(Topics.TILE_UPDATED, position);
    }

    protected void changeLevel(Level level) {
        state.setLevel(level);
        state.getPlayer().setPosition(state.getLevel().getStartPosition());
    }

    private boolean validPlayerPosition(Position position) {
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

}
