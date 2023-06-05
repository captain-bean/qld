package com.marshall.benjy.qld.core.game.state;


import com.marshall.benjy.qld.core.engine.state.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Player {

    private Position position;

    public Player() {
        this(new Position(0,0));
    }

    public Player(Position position) {
        this.position = position;
    }


    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
