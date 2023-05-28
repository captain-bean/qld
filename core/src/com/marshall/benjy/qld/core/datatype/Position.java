package com.marshall.benjy.qld.core.datatype;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public String toString() {
        return x + ", " + y;
    }
}
