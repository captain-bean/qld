package com.marshall.benjy.qld.core.game.logic.control;

import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.state.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PlayerController {

    private static final Logger logger = LogManager.getLogger(PlayerController.class);
    private Player player;
    private List<Consumer<Player>> playerMovedListeners = new ArrayList<>();

    public PlayerController(Player player) {
        this.player = player;
    }

    public void addMovementListener(Consumer<Player> eventConsumer) {
        this.playerMovedListeners.add(eventConsumer);
    }

    public void movePlayer(Position newPosition) {
        logger.info("Updating player's position: " + newPosition);
        player.setPosition(newPosition);

        playerMovedListeners.forEach(listener -> {
            listener.accept(player);
        });
    }
}
