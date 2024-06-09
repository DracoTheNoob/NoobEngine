package fr.noobengine.math;

public class Vector {
    private double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public void translate(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void translate(Vector vector) {
        this.x += vector.x;
        this.y += vector.y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
}
