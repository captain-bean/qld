package com.marshall.benjy.qld.core.engine.input;

import com.badlogic.gdx.InputProcessor;
import com.marshall.benjy.qld.core.engine.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class KeyboardInputReceiver implements InputProcessor {
    private static final Logger logger = LogManager.getLogger(KeyboardInputReceiver.class);

    private Map<Integer, Command> keyCommands = new HashMap<>();

    public void addKeyCommand(Integer key, Command command) {
        keyCommands.put(key, command);
    }

    @Override
    public boolean keyDown(int keycode) {
        Command command = keyCommands.get(keycode);
        if(command != null) {
            command.execute();
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}