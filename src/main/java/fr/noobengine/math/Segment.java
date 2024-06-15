package fr.noobengine.math;

public class Segment {
    private final Vector start, end;

    public Segment(Vector start, Vector end) {
        this.start = start;
        this.end = end;
    }

    public Vector getCollidingPoint(Segment segment) {
        Right self = new Right(this);
        Right other = new Right(segment);

        Vector colliding = self.getCollidingPoint(other);

        if(colliding == null) {
            return null;
        }

        double startX = start.getX();
        double endX = end.getX();
        double collidingX = colliding.getX();

        if(startX < endX) {
            if(startX <= collidingX && collidingX <= endX) {
                return colliding;
            }
        } else if(startX > endX) {
            if(startX >= collidingX && collidingX >= endX) {
                return colliding;
            }
        } else {
            if(startX == collidingX) {
                return colliding;
            }
        }

        return null;
    }

    public boolean intersects(Segment other) {
        return this.getCollidingPoint(other) != null;
    }

    public Vector getStart() { return start; }
    public Vector getEnd() { return end; }

    @Override
    public String toString() {
        return "(" + start + " to " + end + ")";
    }
}
