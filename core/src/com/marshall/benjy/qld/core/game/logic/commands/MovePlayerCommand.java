package com.marshall.benjy.qld.core.game.logic.commands;

import com.marshall.benjy.qld.core.engine.logic.command.Command;
import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.Player;
import com.marshall.benjy.qld.core.game.state.QLDGameState;

public class MovePlayerCommand extends Command {

    private QLDGameState state;
    private int deltaX;
    private int deltaY;

    public MovePlayerCommand(QLDGameState state, int deltaX, int deltaY){
        this.state = state;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public void execute() {
        Player player = state.getPlayer();
        Level level = state.getLevel();

        Position oldPosition = player.getPosition();
        Position newPosition = new Position(oldPosition.getX() + deltaX, oldPosition.getZ() + deltaY);

        if(level.validPlayerPosition(newPosition)) {
            player.setPosition(newPosition);
            level.blowUp(newPosition);
        }
    }
}
