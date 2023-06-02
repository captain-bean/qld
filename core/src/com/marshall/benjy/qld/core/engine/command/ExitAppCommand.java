package com.marshall.benjy.qld.core.engine.command;

import com.badlogic.gdx.Gdx;
import com.marshall.benjy.qld.core.engine.command.Command;

public class ExitAppCommand extends Command {
    @Override
    public void execute() {
        Gdx.app.exit();
    }
}
