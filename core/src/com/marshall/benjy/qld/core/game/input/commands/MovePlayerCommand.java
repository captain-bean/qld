package com.marshall.benjy.qld.core.game.input.commands;

import com.marshall.benjy.qld.core.engine.logic.command.Command;

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
