package fr.noobengine.core;

import fr.noobengine.Engine;
import fr.noobengine.math.Vector;

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

    public Sprite(Engine engine, Scene scene, Vector location, Vector scale, String texture) {
        this.engine = engine;
        this.scene = scene;
        this.id = UUID.randomUUID();
        this.location = location;
        this.scale = scale;
        this.texture = texture;
        this.scripts = new ArrayList<>();
    }

    @Override
    public void initialize() {
        this.scripts.forEach(Script::initialize);
    }

    @Override
    public void update() {
        this.scripts.forEach(Script::update);
    }

    @Override
    public void close() {
        this.scripts.forEach(Script::close);
    }

    public void onSpriteAdded() {
        this.scripts.forEach(Script::onSpriteAdded);
    }

    public void onSpriteRemoved() {
        this.scripts.forEach(Script::onSpriteRemoved);
    }

    public UUID getId() { return id; }
    public Vector getLocation() { return location; }
    public Vector getScale() { return scale; }
    public String getTexture() { return texture; }

    public void setLocation(Vector location) { this.location = location; }
    public void setScale(Vector scale) { this.scale = scale; }
    public void setTexture(String texture) { this.texture = texture; }

    public void addScript(Script script) {
        this.scripts.add(script);
    }
}
