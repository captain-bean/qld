package com.marshall.benjy.qld.core.game.director;

import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.messaging.Subscriber;
import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.input.commands.MovePlayerCommand;
import com.marshall.benjy.qld.core.game.messaging.Topics;
import com.marshall.benjy.qld.core.game.state.api.StateManager;
import org.apache.camel.CamelContext;

public class DirectorBridge {

    public DirectorBridge(CamelContext context, Director director) {
        Subscriber<MovePlayerCommand> movePlayerCommandConsumer =
                new Subscriber<>(context, Topics.MOVE_PLAYER_INTENT, director::handleMovePlayerIntent);
        Subscriber<MoveCameraCommand> moveCameraCommandConsumer =
                new Subscriber<>(context, Topics.MOVE_CAMERA_INTENT, director::handleMoveCameraIntent);
    }

}
