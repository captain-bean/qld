package com.marshall.benjy.qld.core.control;

import com.marshall.benjy.qld.core.datatype.Position;
import com.marshall.benjy.qld.core.game.state.GameState;
import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.Player;

public class MovementController {
    private GameState state;
    public MovementController (GameState state) {
        this.state = state;
    }

    public void movePlayer(int deltaX, int deltaY) {
        Player player = state.getPlayer();
        Level level = state.getLevel();

        Position oldPosition = player.getPosition();
        Position newPosition = new Position(oldPosition.getX() + deltaX, oldPosition.getY() + deltaY);

        if(level.validPlayerPosition(newPosition)) {
            player.setPosition(newPosition);
            level.blowUp(newPosition);
        }
    }


}
