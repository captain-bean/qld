package com.marshall.benjy.qld.core.game.state.api;

import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.input.commands.MovePlayerCommand;
import com.marshall.benjy.qld.core.engine.messaging.Subscriber;
import com.marshall.benjy.qld.core.game.messaging.Topics;
import org.apache.camel.CamelContext;

public class StateBridge {

    protected StateBridge(CamelContext context, StateManager stateManager) {

        Subscriber<Position> movePlayerCommandConsumer
                = new Subscriber<>(context, Topics.MOVE_PLAYER, stateManager::movePlayer);
    }


}
