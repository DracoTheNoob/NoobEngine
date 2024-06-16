package fr.noobengine.math;

public class Vector {
    public static final Vector NORMAL = new Vector(1, 1);
    public static final Vector NULL = new Vector(0, 0);

    private double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public Vector(Vector start, Vector end) {
        this.x = end.getX() - start.getX();
        this.y = end.getY() - start.getY();
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void add(Vector vector) {
        this.x += vector.x;
        this.y += vector.y;
    }

    public Vector translate(double x, double y) {
        return new Vector(this.x + x, this.y + y);
    }

    public Vector translate(Vector vector) {
        return new Vector(this.x + vector.x, this.y + vector.y);
    }

    public double distanceFrom(Vector other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    public Vector multiply(double coefficient) {
        return new Vector(this.x * coefficient, this.y * coefficient);
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    @Override
    public String toString() {
        return String.format("(%f, %f)", x, y);
    }
}
