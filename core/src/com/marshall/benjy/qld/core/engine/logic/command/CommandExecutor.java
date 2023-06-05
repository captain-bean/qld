package com.marshall.benjy.qld.core.engine.logic.command;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandExecutor {

    private Map<String, Consumer<Command>> typeHandlers = new HashMap<>();

    public void addCommandTypeHandler(String type, Consumer<Command> handler) {
        typeHandlers.put(type, handler);
    }
    public void execute(Command command) {
        Consumer<Command> handler = typeHandlers.get(command.getType());
        if(handler != null) {
            handler.accept(command);
        }

    }
}
