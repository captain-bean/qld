package com.marshall.benjy.qld.core.game.player;

import com.marshall.benjy.qld.core.datatype.Position;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerMovementController {

    private static final Logger logger = LogManager.getLogger(PlayerMovementController.class);

    private Player player;
    public PlayerMovementController (Player player) {
        this.player = player;
    }

    public void movePlayer(int deltaX, int deltaY) {
        Position oldPosition = player.getPosition();
        Position newPosition = new Position(oldPosition.getX() + deltaX, oldPosition.getY() + deltaY);
        player.setPosition(newPosition);

    }
}