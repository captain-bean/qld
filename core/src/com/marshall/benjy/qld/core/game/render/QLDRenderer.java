package com.marshall.benjy.qld.core.game.render;

import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.state.Position;

public interface QLDRenderer {

    void onTileUpdated(Position position);

    void updatePlayerInstance();

    void onLevelChanged();

    void render();

    void moveCamera(MoveCameraCommand command);

    void dispose();

    void resize(int width, int height);
}
