package com.marshall.benjy.qld.core.game.render.api;

import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.render.QLDRenderer;
import com.marshall.benjy.qld.core.engine.messaging.Subscriber;
import com.marshall.benjy.qld.core.game.messaging.Topics;
import org.apache.camel.CamelContext;

/*
 Forwards necessary information from the core game to a renderer
 */
public class RenderBridge {

    private QLDRenderer renderer;
    
    public RenderBridge(QLDRenderer renderer, CamelContext context) {

        // Listening for state updates
        Subscriber<Position> tileUpdatedListener
                = new Subscriber<>(context, Topics.TILE_UPDATED, (tile) -> renderer.onTileUpdated(tile));
        Subscriber<Position> playerMovementListener
                = new Subscriber<>(context, Topics.PLAYER_MOVED, (p) -> renderer.updatePlayerInstance());
        Subscriber<String> levelChangedListener
                = new Subscriber<>(context, Topics.LEVEL_CHANGED, (string) -> renderer.onLevelChanged());

        // Listening for commands
        Subscriber<MoveCameraCommand> moveCameraListener
                = new Subscriber<>(context, Topics.MOVE_CAMERA, (c) -> renderer.moveCamera(c));

    }


}
