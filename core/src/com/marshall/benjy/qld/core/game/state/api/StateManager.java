package com.marshall.benjy.qld.core.game.state.api;

import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.engine.messaging.Publisher;
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
    private Publisher<Position> playerMovedPublisher;
    private Publisher<Position> tileChangedPublisher;
    private Publisher<String> levelChangedPublisher;
    private StateBridge stateBridge;

    public StateManager(CamelContext context) {
        state = QLDGameStateFactory.development();
        playerMovedPublisher = new Publisher<>(context);
        tileChangedPublisher = new Publisher<>(context);
        levelChangedPublisher = new Publisher<>(context);
        stateBridge = new StateBridge(context, this);
    }

    public QLDGameState getState() {
        return state;
    }

    // TODO how to move this logic out into more of a game flow director?
    protected void movePlayer(Position newPosition) {
        logger.info("Updating player's position: " + newPosition);
        state.getPlayer().setPosition(newPosition);
        playerMovedPublisher.sendMessage(Topics.PLAYER_MOVED, newPosition);
    }

    public void changeTile(Position position, TileTypes tileType) {
        state.getLevel().setTile(position, new Tile(tileType));
        tileChangedPublisher.sendMessage(Topics.TILE_UPDATED, position);
    }

    protected void changeLevel(Level level) {
        state.setLevel(level);
        state.getPlayer().setPosition(state.getLevel().getStartPosition());
    }



}
