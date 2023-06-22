package com.marshall.benjy.qld.core.game.logic;

import com.marshall.benjy.qld.core.game.logic.commands.QLDCommandExecutor;
import com.marshall.benjy.qld.core.game.logic.control.InputController;
import com.marshall.benjy.qld.core.game.logic.control.LevelController;
import com.marshall.benjy.qld.core.game.logic.control.PlayerController;
import com.marshall.benjy.qld.core.game.logic.control.QLDController;
import com.marshall.benjy.qld.core.game.render.QLDRenderer;
import com.marshall.benjy.qld.core.game.state.QLDGameState;

public class QLDLogic {

    private QLDGameState state;
    private QLDRenderer renderer;
    private QLDController qldController;
    private InputController inputController;

    public QLDLogic(QLDGameState state, QLDRenderer renderer) {
        this.state = state;
        this.renderer = renderer;

        this.qldController = new QLDController(state, renderer);

        QLDCommandExecutor commandExecutor = new QLDCommandExecutor(state, renderer, qldController);
        inputController = new InputController(commandExecutor.getExecutor());
    }
}
