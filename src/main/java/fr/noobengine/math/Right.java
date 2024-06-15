package fr.noobengine.math;

public class Right {
    private final double a, b, c;

    public Right(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Right(Segment segment) {
        Vector director = new Vector(segment.getStart(), segment.getEnd());
        this.a = director.getY();
        this.b = -director.getX();
        this.c = -a * segment.getStart().getX() - b*segment.getStart().getY();
    }

    public Vector getCollidingPoint(Right other) {
        double a1 = this.a;
        double b1 = this.b;
        double c1 = this.c;

        double a2 = other.a;
        double b2 = other.b;
        double c2 = other.c;

        if(a1 == a2 && b1 == b2) {
            return null;
        }

        double y = (-(a1*c2)/a2 + c1) / (-b1 + (a1*b2)/a2);
        double x = (-b1 * y - c1) / a1;

        return new Vector(x, y);
    }

    @Override
    public String toString() {
        return String.format("%f*x + %f*y + %f = 0", a, b, c);
    }

    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }
}
