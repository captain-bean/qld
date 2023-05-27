package com.marshall.benjy.qld.core.game.player;


import com.marshall.benjy.qld.core.datatype.Position;

public class Player {

    private String name = "Player Name";
    private Position position;

    public Player() {
        this.position = new Position(0,0);
    }

    public String getName() {
        return name;
    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
