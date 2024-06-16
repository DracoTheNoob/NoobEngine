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

        if((a1 == 0 && a2 == 0) || (b1 == 0 && b2 == 0) || b2 == (b1 * a2) / a1) {
            return null;
        }

        double x = (b1*c2 - b2*c1) / (a1*b2 - a2*b1);
        double y = (c1*a2 - c2*a1) / (a1*b2 - a2*b1);

        return new Vector(x, y);
    }

    @Override
    public String toString() {
        return (a == 0 ? "" : a + "x" + (b > 0 ? "+" : "")) + (b == 0 ? "" : b + "y" + (c > 0 ? "+" : "")) + (c == 0 ?  "" : c) + " = 0";
    }

    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }
}
