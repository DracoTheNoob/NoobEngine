package fr.noobengine.math;

import java.util.Arrays;

public class Polygon {
    private final Vector[] points;
    private final Segment[] segments;

    public Polygon(Vector... points) {
        if(points == null || points.length < 3) {
            throw new IllegalArgumentException("A polygon is made of at least 3 points");
        }

        this.points = points;
        this.segments = new Segment[points.length];

        for(int i = 1; i < points.length; i++) {
            this.segments[i] = new Segment(points[i-1], points[i]);
        }

        this.segments[0] = new Segment(points[points.length-1], points[0]);
    }

    public boolean contains(Vector point) {
        System.out.println(point + " not in " + Arrays.toString(this.points));
        int counter = 0;

        for(Segment segment : this.segments) {
            Segment rayCast = new Segment(point, point.fromTranslation(1000, 0));
            System.out.print(rayCast + " " + segment);

            if(segment.intersects(rayCast)) {
                counter++;
            } else {
                System.out.println(" not crossing");
            }
        }

        System.out.println(counter);

        return counter % 2 == 1;
    }

    public boolean intersects(Polygon other) {
        for(Segment s1 : this.segments) {
            for(Segment s2 : other.segments) {
                if(s1.intersects(s2)) {
                    return true;
                }
            }
        }

        return this.contains(other.points[0]) || other.contains(this.points[0]);
    }

    public Vector[] getPoints() { return points; }
    public Segment[] getSegments() { return segments; }
}
