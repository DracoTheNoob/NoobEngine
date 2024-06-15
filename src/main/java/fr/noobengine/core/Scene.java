package fr.noobengine.core;

import fr.noobengine.Engine;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Scene implements Cycle {
    protected final Engine engine;

    private final List<Sprite> sprites;
    private final List<UUID> spritesToInitialize;
    private Camera currentCamera;

    public Scene(Engine engine) {
        this.engine = engine;
        this.sprites = new ArrayList<>();
        this.spritesToInitialize = new ArrayList<>();
        this.currentCamera = new Camera();
    }

    @Override
    public void initialize() {
        spritesToInitialize.forEach(id -> getSprite(id).initialize());
        spritesToInitialize.clear();

        this.currentCamera.initialize();
    }

    @Override
    public void update() {
        if(!spritesToInitialize.isEmpty()) {
            spritesToInitialize.forEach(id -> getSprite(id).initialize());
            spritesToInitialize.clear();
        }

        this.sprites.forEach(Sprite::update);
        this.currentCamera.update();
    }

    @Override
    public void close() {
        this.sprites.forEach(Sprite::close);
        this.currentCamera.close();
    }

    public void addSprite(Sprite sprite) {
        this.sprites.add(sprite);
        sprite.onSpriteAdded();
        this.spritesToInitialize.add(sprite.getId());
    }

    public void addSprites(Sprite... sprites) {
        for(Sprite sprite : sprites) {
            this.addSprite(sprite);
        }
    }

    public void remove(UUID spriteId) {
        for(Sprite sprite : this.sprites) {
            if(sprite.getId() == spriteId) {
                sprite.close();
                this.sprites.remove(sprite);
                sprite.onSpriteRemoved();
                break;
            }
        }
    }

    public Sprite getSprite(UUID id) {
        for(Sprite sprite : sprites) {
            if(sprite.getId() == id) {
                return sprite;
            }
        }

        return null;
    }

    public Camera getCurrentCamera() { return currentCamera; }
    public Sprite[] getSprites() { return sprites.toArray(Sprite[]::new); }
}
