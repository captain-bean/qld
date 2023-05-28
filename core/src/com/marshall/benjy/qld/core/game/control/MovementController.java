package com.marshall.benjy.qld.core.game.control;

import com.marshall.benjy.qld.core.datatype.Position;
import com.marshall.benjy.qld.core.game.GameState;
import com.marshall.benjy.qld.core.game.level.Level;
import com.marshall.benjy.qld.core.game.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MovementController {
    private static final Logger logger = LogManager.getLogger(MovementController.class);
    private GameState state;
    public MovementController (GameState state) {
        this.state = state;
    }

    public void movePlayer(int deltaX, int deltaY) {
        Player player = state.getPlayer();
        Level level = state.getLevel();

        Position oldPosition = state.getPlayer().getPosition();
        Position newPosition = new Position(oldPosition.getX() + deltaX, oldPosition.getY() + deltaY);

        if(state.getLevel().validPlayerPosition(newPosition)) {
            state.getPlayer().setPosition(newPosition);
            level.blowUp(newPosition);
        }
    }


}
