package com.marshall.benjy.qld.core.engine.state;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + z;
        return result;
    }

    // Equals method (assuming you also override equals for comparison)
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        return x == other.x && z == other.z;
    }


//    3D position versions
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + x;
//        result = prime * result + y;
//        result = prime * result + z;
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null || getClass() != obj.getClass())
//            return false;
//        Position other = (Position) obj;
//        return x == other.x && y == other.y && z == other.z;
//    }
}
