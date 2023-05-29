package com.marshall.benjy.qld.core.game.control.commands;

import com.marshall.benjy.qld.core.datatype.Position;
import com.marshall.benjy.qld.core.game.control.commands.Command;
import com.marshall.benjy.qld.core.game.state.GameState;
import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.Player;

public class MovePlayerCommand extends Command {

    private GameState state;
    private int deltaX;
    private int deltaY;

    public MovePlayerCommand(GameState state, int deltaX, int deltaY){
        this.state = state;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public void execute() {
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
