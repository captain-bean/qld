package com.marshall.benjy.qld.core.game.logic;

import com.marshall.benjy.qld.core.game.logic.commands.QLDCommandExecutor;
import com.marshall.benjy.qld.core.game.logic.control.QLDInputReceiver;
import com.marshall.benjy.qld.core.game.logic.control.QLDController;
import com.marshall.benjy.qld.core.game.render.QLDRenderer;
import com.marshall.benjy.qld.core.game.state.QLDGameState;

public class QLDLogic {

    private QLDGameState state;
    private QLDRenderer renderer;
    private QLDController qldController;
    private QLDInputReceiver inputReceiver;

    public QLDLogic(QLDGameState state, QLDRenderer renderer) {
        this.state = state;
        this.renderer = renderer;

        this.qldController = new QLDController(state);

        qldController.getLevelController().addTileDestroyedListener((tile) -> renderer.onTileUpdated(tile));
        qldController.getPlayerController().addMovementListener((p) -> {
            renderer.updatePlayerInstance();
        });

        QLDCommandExecutor commandExecutor = new QLDCommandExecutor(state, renderer, qldController);
        inputReceiver = new QLDInputReceiver(commandExecutor.getExecutor());
    }
}
