package com.marshall.benjy.qld.core.game.state.api;

import com.marshall.benjy.qld.core.game.input.commands.MovePlayerCommand;
import com.marshall.benjy.qld.core.game.messaging.QLDConsumer;
import com.marshall.benjy.qld.core.game.messaging.Topics;
import org.apache.camel.CamelContext;

public class StateBridge {

    public StateBridge(CamelContext context, StateManager stateManager) {

        QLDConsumer<MovePlayerCommand> movePlayerCommandConsumer
                = new QLDConsumer<>(context, Topics.MOVE_PLAYER, (command) -> {
                    stateManager.movePlayer(command.getDeltaX(), command.getDeltaZ());
        });
    }


}
