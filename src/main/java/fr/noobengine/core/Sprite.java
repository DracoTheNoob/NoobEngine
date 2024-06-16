package fr.noobengine.core;

import  fr.noobengine.Engine;
import fr.noobengine.math.Collision;
import fr.noobengine.math.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Sprite implements Cycle {
    protected final Engine engine;
    protected final Scene scene;
    protected final UUID id;
    protected Vector location;
    protected Vector scale;
    protected String texture;
    protected final List<Script> scripts;
    protected Collision baseCollision;
    protected Collision effectiveCollision;

    public Sprite(Engine engine, Scene scene, Vector location, Vector scale, String texture, Collision collision) {
        this.engine = engine;
        this.scene = scene;
        this.id = UUID.randomUUID();
        this.location = location;
        this.scale = scale;
        this.texture = texture;
        this.scripts = new ArrayList<>();
        this.baseCollision = collision;
        this.updateEffectiveCollision();
    }

    @Override
    public final void initialize() {
        this.scripts.forEach(Script::initialize);
    }

    @Override
    public final void update() {
        this.updateEffectiveCollision();
        this.scripts.forEach(Script::update);
    }

    @Override
    public final void close() {
        this.scripts.forEach(Script::close);
    }

    public final void onSpriteAdded() {
        this.scripts.forEach(Script::onSpriteAdded);
    }

    public final void onSpriteRemoved() {
        this.scripts.forEach(Script::onSpriteRemoved);
    }

    public final double distanceTo(Sprite other) {
        Vector loc1 = this.location;
        Vector loc2 = other.location;

        return Math.sqrt(Math.pow(loc1.getX() - loc2.getX(), 2) + Math.pow(loc1.getY() - loc2.getY(), 2));
    }

    public final double distanceTo(Vector loc2) {
        Vector loc1 = this.location;
        return Math.sqrt(Math.pow(loc1.getX() - loc2.getX(), 2) + Math.pow(loc1.getY() - loc2.getY(), 2));
    }

    public final boolean collides(Sprite sprite) {
        return this.effectiveCollision.isColliding(sprite.effectiveCollision);
    }

    public void render(Graphics2D g, BufferedImage texture, Dimension screen) {
        if(texture == null) {
            return;
        }

        int w = (int)(texture.getWidth() * this.getScale().getX());
        int h = (int)(texture.getHeight() * this.getScale().getY());
        int x = screen.width/2 + (int)(this.getLocation().getX() - w/2);
        int y = screen.height/2 - (int)(this.getLocation().getY() + h/2);

        g.drawImage(texture, x, y, w, h, null);
    }

    public void updateEffectiveCollision() {
        this.effectiveCollision = this.baseCollision.translate(this.location);
    }

    public final UUID getId() { return id; }
    public final Vector getLocation() { return location; }
    public final Vector getScale() { return scale; }
    public final String getTexture() { return texture; }
    public final Collision getEffectiveCollision() { return effectiveCollision; }

    public final void setLocation(Vector location) { this.location = location; }
    public final void setScale(Vector scale) { this.scale = scale; }
    public final void setTexture(String texture) { this.texture = texture; }

    public final void addScript(Script script) {
        this.scripts.add(script);
    }
    public final void addScripts(Script... scripts) {
        this.addScripts(List.of(scripts));
    }
    public final void addScripts(List<Script> scripts) {
        this.scripts.addAll(scripts);
    }

    public final Engine getEngine() { return engine; }
}
