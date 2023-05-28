package com.marshall.benjy.qld.core.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.marshall.benjy.qld.core.game.state.GameState;
import com.marshall.benjy.qld.core.game.state.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KeyboardInputHandler implements InputProcessor {
    private static final Logger logger = LogManager.getLogger(KeyboardInputHandler.class);
    private GameState state;
    private MovementController movementController;

    public KeyboardInputHandler(GameState state) {
        this.state = state;
        movementController = new MovementController(state);
    }

    @Override
    public boolean keyDown(int keycode) {
        logger.info("Key down event received: " + keycode);
        Player player = state.getPlayer();
        switch (keycode) {
            case Input.Keys.W:
                movementController.movePlayer(0, -1);
                break;
            case Input.Keys.A:
                movementController.movePlayer(-1, 0);
                break;
            case Input.Keys.S:
                movementController.movePlayer(0, 1);
                break;
            case Input.Keys.D:
                movementController.movePlayer(1, 0);
                break;
            case Input.Keys.ESCAPE:
                Gdx.app.exit();
           default:
                return false;
        }
        return true;
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
