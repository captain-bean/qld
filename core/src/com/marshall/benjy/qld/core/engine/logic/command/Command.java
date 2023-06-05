package com.marshall.benjy.qld.core.engine.logic.command;

public abstract class Command {

    public String type;

    public Command(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
