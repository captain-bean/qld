package com.marshall.benjy.qld.core.datatype;

public class Position {

    private int x;
    private int z;

    public Position(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public int getX() {
        return x;
    }
    public int getZ() {
        return z;
    }

    public String toString() {
        return x + ", " + z;
    }
}
