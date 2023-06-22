package com.marshall.benjy.qld.core.game.logic.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.marshall.benjy.qld.core.engine.logic.command.CommandExecutor;
import com.marshall.benjy.qld.core.engine.logic.command.ExitAppCommand;
import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.logic.input.KeyboardInputReceiver;
import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.logic.commands.MovePlayerCommand;
import com.marshall.benjy.qld.core.game.logic.commands.QLDCommandExecutor;
import com.marshall.benjy.qld.core.game.logic.generator.LegacyLevelGenerator;
import com.marshall.benjy.qld.core.game.render.QLDRenderer;
import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.QLDGameState;

public class QLDController {

    private QLDGameState state;
    private PlayerController playerController;
    private LevelController levelController;

    public QLDController(QLDGameState state) {
        this.state = state;

        this.levelController = new LevelController(state);
        this.playerController = new PlayerController(state.getPlayer());
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public LevelController getLevelController() {
        return levelController;
    }

}
