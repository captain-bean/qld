package com.marshall.benjy.qld.core.game.control.commands;

import com.badlogic.gdx.Gdx;

public class ExitAppCommand extends Command{
    @Override
    public void execute() {
        Gdx.app.exit();
    }
}
