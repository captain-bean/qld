package com.marshall.benjy.qld.core.game.control;

import com.marshall.benjy.qld.Game;
import com.marshall.benjy.qld.core.datatype.Position;
import com.marshall.benjy.qld.core.game.GameState;
import com.marshall.benjy.qld.core.game.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerMovementController {

    private static final Logger logger = LogManager.getLogger(PlayerMovementController.class);

    public void movePlayer(Player player, int deltaX, int deltaY) {
        Position oldPosition = player.getPosition();
        Position newPosition = new Position(oldPosition.getX() + deltaX, oldPosition.getY() + deltaY);
        player.setPosition(newPosition);
    }
}
