package fr.noobengine.core;

import fr.noobengine.Engine;
import fr.noobengine.math.Vector;

import java.awt.event.KeyEvent;
import java.util.UUID;

public class Sprite implements Cycle {
    protected final Engine engine;
    protected final Scene scene;
    protected final UUID id;
    protected final Vector location;
    protected final String texture;

    public Sprite(Engine engine, Scene scene, Vector location, String texture) {
        this.engine = engine;
        this.scene = scene;
        this.id = UUID.randomUUID();
        this.location = location;
        this.texture = texture;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void update() {

    }

    @Override
    public void close() {

    }

    public UUID getId() { return id; }
    public Vector getLocation() { return location; }
    public String getTexture() { return texture; }
}
