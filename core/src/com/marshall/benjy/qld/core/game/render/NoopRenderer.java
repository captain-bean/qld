package com.marshall.benjy.qld.core.game.render;

import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.state.Position;

public class NoopRenderer implements QLDRenderer {
    @Override
    public void onTileUpdated(Position position) {

    }

    @Override
    public void updatePlayerInstance() {

    }

    @Override
    public void render() {

    }

    @Override
    public void moveCamera(MoveCameraCommand command) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int width, int height) {

    }
}
