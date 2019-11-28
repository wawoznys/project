package project;

public class Vector2d {
    public final int x;
    public final int y;
    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String toString() {
        return new String("(" + this.x + "," + this.y + ")");
    }

    public Vector2d next() {

    }
}
