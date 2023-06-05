package com.marshall.benjy.qld.core.engine.logic.command;

import com.badlogic.gdx.Gdx;

public class ExitAppCommand extends Command {

    public static final String TYPE = "exit-app";
    public ExitAppCommand() {
        super(TYPE);
    }

}
