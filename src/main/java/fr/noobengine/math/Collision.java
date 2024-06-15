package fr.noobengine.math;

import fr.noobengine.core.Sprite;

public class Collision {
    private final Sprite owner;
    private final Polygon polygon;

    public Collision(Sprite owner, Vector... points) {
        this.owner = owner;
        this.polygon = new Polygon(points);
    }

    public boolean isColliding(Collision other) {
        return polygon.intersects(other.polygon);
    }

    public Sprite getOwner() { return owner; }
    public Vector[] getPoints() { return polygon.getPoints(); }
}
