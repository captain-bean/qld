package com.marshall.benjy.qld.core.game.render;

import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;

public interface QLDRenderer {

    void updateLevelInstances();

    void updatePlayerInstance();

    void render();

    void moveCamera(MoveCameraCommand command);

    void dispose();

    void resize(int width, int height);
}
