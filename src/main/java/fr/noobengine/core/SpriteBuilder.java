package fr.noobengine.core;

import fr.noobengine.Engine;
import fr.noobengine.math.Collision;
import fr.noobengine.math.Vector;

import java.util.ArrayList;
import java.util.List;

public class SpriteBuilder {
    protected final Engine engine;
    protected final Scene scene;

    protected Vector location = Vector.NULL;
    protected Vector scale = Vector.NORMAL;
    protected String texture;
    protected Collision collision;
    protected final List<Script> scripts;

    public SpriteBuilder(Engine engine, Scene scene) {
        this.engine = engine;
        this.scene = scene;
        this.scripts = new ArrayList<>();
    }

    public SpriteBuilder self(Runnable action) {
        action.run();
        return this;
    }

    public SpriteBuilder setLocation(Vector location) {
        return self(() -> this.location = location);
    }

    public SpriteBuilder setScale(Vector scale) {
        return self(() -> this.scale = scale);
    }

    public SpriteBuilder setTexture(String texture) {
        return self(() -> this.texture = texture);
    }

    public SpriteBuilder setCollision(Collision collision) {
        return self(() -> this.collision = collision);
    }

    public SpriteBuilder addScript(Script script) {
        return self(() -> this.scripts.add(script));
    }

    public SpriteBuilder addScripts(Script... scripts) {
        return self(() -> this.scripts.addAll(List.of(scripts)));
    }

    public Sprite build() {
        Sprite sprite = new Sprite(this.engine, this.scene, this.location, this.scale, this.texture, this.collision);
        sprite.addScripts(this.scripts);

        return sprite;
    }
}
