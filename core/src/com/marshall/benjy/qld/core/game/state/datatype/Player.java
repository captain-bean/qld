package com.marshall.benjy.qld.core.game.state.datatype;


import com.marshall.benjy.qld.core.engine.state.Position;

public class Player {

    private Position position;

    public Player() {
        this(new Position(0,0));
    }

    public Player(Position position) {
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition(){
        return position;
    }

}
