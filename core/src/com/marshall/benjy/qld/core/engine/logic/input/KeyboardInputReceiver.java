package com.marshall.benjy.qld.core.engine.logic.input;

import com.badlogic.gdx.InputProcessor;
import com.marshall.benjy.qld.core.engine.logic.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class KeyboardInputReceiver implements InputProcessor {

    private Map<Integer, Consumer<Integer>> keyCommands = new HashMap<>();

    public KeyboardInputReceiver() {
    }

    public void putKeyConsumer(Integer key, Consumer<Integer> consumer) {
        keyCommands.put(key, consumer);
    }

    @Override
    public boolean keyDown(int keycode) {
        Consumer<Integer> commandConsumer = keyCommands.get(keycode);
        if(commandConsumer != null) {
            commandConsumer.accept(keycode);
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
