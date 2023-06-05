package com.marshall.benjy.qld.core.game.logic.commands;

import com.marshall.benjy.qld.core.engine.logic.command.Command;
import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.logic.control.LevelController;
import com.marshall.benjy.qld.core.game.logic.control.PlayerController;
import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.Player;
import com.marshall.benjy.qld.core.game.state.QLDGameState;

public class MovePlayerCommand extends Command {

    public static final String TYPE = "move-player";
    private int deltaX;
    private int deltaZ;

    public MovePlayerCommand(int deltaX, int deltaZ){
        super(TYPE);
        this.deltaX = deltaX;
        this.deltaZ = deltaZ;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaZ() {
        return deltaZ;
    }


}
