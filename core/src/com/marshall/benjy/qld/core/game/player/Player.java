package com.marshall.benjy.qld.core.game.player;


import com.marshall.benjy.qld.core.datatype.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Player {

    private static final Logger logger = LogManager.getLogger(Player.class);
    private List<Consumer<Player>> playerMovedListeners = new ArrayList<>();
    private Position position;

    public Player() {
        this.position = new Position(0,0);
    }

    public void addMovementListener(Consumer<Player> eventConsumer) {
        this.playerMovedListeners.add(eventConsumer);
    }
    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        logger.info("Updating player's position: " + position);
        playerMovedListeners.forEach(listener -> {
            listener.accept(this);
        });
    }
}
