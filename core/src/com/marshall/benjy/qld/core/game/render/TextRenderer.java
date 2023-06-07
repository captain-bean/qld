package com.marshall.benjy.qld.core.game.render;

import com.marshall.benjy.qld.core.engine.logic.command.MoveCameraCommand;
import com.marshall.benjy.qld.core.engine.state.Position;
import com.marshall.benjy.qld.core.game.state.Level;
import com.marshall.benjy.qld.core.game.state.QLDGameState;
import com.marshall.benjy.qld.core.game.state.tile.Tile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextRenderer implements QLDRenderer{

    private static final Logger logger = LogManager.getLogger(TextRenderer.class);

    private QLDGameState state;
    private char[][] textRepresentation;

    private boolean dirty;

    public TextRenderer(QLDGameState state) {
        this.state = state;
        refresh();
    }

    @Override
    public void render() {
        if(dirty) {
            for(char[] textLine : textRepresentation) {
                logger.info(new String(textLine));
            }
            dirty = false;
        }
    }

    public void refresh() {
        Tile[][] level = state.getLevel().getTiles();
        textRepresentation = new char[level.length][level[0].length];

        for(int i = 0; i < level.length; i++) {
            Tile[] line = level[i];
            char[] textLine = new char[line.length];
            for(int j = 0; j < line.length; j++) {
                textLine[j] = tileToChar(line[j]);
            }
            textRepresentation[i] = textLine;
        }

        Position playerPosition = state.getPlayer().getPosition();
        textRepresentation[playerPosition.getX()][playerPosition.getZ()] = 'P';

        dirty = true;
    }

    private char tileToChar(Tile tile) {
        switch(tile.getType()) {
            case PAVEMENT: return 'O';
            case WOOD: return 'W';
            case BLOWED_UP: return 'X';
        }
        return '?';
    }

    @Override
    public void updateLevelInstances() {
        refresh();
    }

    @Override
    public void updatePlayerInstance() {
        refresh();
    }

    @Override
    public void moveCamera(MoveCameraCommand command) {
        //No camera, don't do anything
    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int width, int height) {

    }
}
