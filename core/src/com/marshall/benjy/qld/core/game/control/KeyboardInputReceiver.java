package com.marshall.benjy.qld.core.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.marshall.benjy.qld.core.game.control.commands.Command;
import com.marshall.benjy.qld.core.game.control.commands.ExitAppCommand;
import com.marshall.benjy.qld.core.game.control.commands.MoveCameraCommand;
import com.marshall.benjy.qld.core.game.control.commands.MovePlayerCommand;
import com.marshall.benjy.qld.core.game.render.ScreenRenderer;
import com.marshall.benjy.qld.core.game.state.GameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class KeyboardInputReceiver implements InputProcessor {
    private static final Logger logger = LogManager.getLogger(KeyboardInputReceiver.class);
    private GameState state;

    private Map<Integer, Command> keyCommands = new HashMap<>();

    public KeyboardInputReceiver(GameState state, ScreenRenderer renderer) {
        this.state = state;

        keyCommands.put(Input.Keys.ESCAPE, new ExitAppCommand());

        keyCommands.put(Input.Keys.W, new MovePlayerCommand(state, 0, -1));
        keyCommands.put(Input.Keys.A, new MovePlayerCommand(state, -1, 0));
        keyCommands.put(Input.Keys.S, new MovePlayerCommand(state, 0, 1));
        keyCommands.put(Input.Keys.D, new MovePlayerCommand(state, 1, 0));

        keyCommands.put(Input.Keys.I, new MoveCameraCommand(renderer.getCamera(), new Vector3(0, 10, 0)));
        keyCommands.put(Input.Keys.K, new MoveCameraCommand(renderer.getCamera(), new Vector3(0, -10, 0)));
        keyCommands.put(Input.Keys.J, new MoveCameraCommand(renderer.getCamera(), new Vector3(-10, 0, 0)));
        keyCommands.put(Input.Keys.L, new MoveCameraCommand(renderer.getCamera(), new Vector3(10, 0, 0)));
        keyCommands.put(Input.Keys.U, new MoveCameraCommand(renderer.getCamera(), new Vector3(0, 0, -10)));
        keyCommands.put(Input.Keys.O, new MoveCameraCommand(renderer.getCamera(), new Vector3(0, 0, 10)));
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
