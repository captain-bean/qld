package com.marshall.benjy.qld.core.game.render.api;

import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.render.QLDRenderer;
import com.marshall.benjy.qld.core.game.messaging.QLDConsumer;
import com.marshall.benjy.qld.core.game.messaging.Topics;
import org.apache.camel.CamelContext;

/*
 Forwards necessary information from the core game to a renderer
 */
public class RenderBridge {

    private QLDRenderer renderer;
    
    public RenderBridge(QLDRenderer renderer, CamelContext context) {

        // Listening for state updates
        QLDConsumer<Position> tileUpdatedListener
                = new QLDConsumer<>(context, Topics.TILE_UPDATED, (tile) -> renderer.onTileUpdated(tile));
        QLDConsumer<Position> playerMovementListener
                = new QLDConsumer<>(context, Topics.PLAYER_MOVED, (p) -> renderer.updatePlayerInstance());
        QLDConsumer<String> levelChangedListener
                = new QLDConsumer<>(context, Topics.LEVEL_CHANGED, (string) -> renderer.onLevelChanged());

        // Listening for commands
        QLDConsumer<MoveCameraCommand> moveCameraListener
                = new QLDConsumer<>(context, Topics.MOVE_CAMERA, (c) -> renderer.moveCamera(c));

    }


}
