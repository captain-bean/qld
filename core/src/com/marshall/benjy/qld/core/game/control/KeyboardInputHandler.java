package com.marshall.benjy.qld.core.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.marshall.benjy.qld.core.game.GameState;
import com.marshall.benjy.qld.core.game.player.Player;

public class KeyboardInputHandler implements InputProcessor {

    private GameState state;
    private PlayerMovementController playerMovementController;
    public KeyboardInputHandler() {
        this(new GameState());
    }

    public KeyboardInputHandler(GameState state) {
        this.state = state;
        playerMovementController = new PlayerMovementController();
    }

    @Override
    public boolean keyDown(int keycode) {
        Player player = state.getPlayer();
        switch (keycode) {
            case Input.Keys.W:
                playerMovementController.movePlayer(player, -1, 0);
                break;
            case Input.Keys.A:
                playerMovementController.movePlayer(player, 0, -1);
                break;
            case Input.Keys.S:
                playerMovementController.movePlayer(player, 1, 0);
                break;
            case Input.Keys.D:
                playerMovementController.movePlayer(player, 0, 1);
                break;
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
