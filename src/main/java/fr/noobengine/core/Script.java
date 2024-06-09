package fr.noobengine.core;

public abstract class Script implements Cycle {
    protected final Sprite sprite;

    public Script(Sprite sprite) {
        this.sprite = sprite;
    }

    public abstract void onSpriteAdded();
    public abstract void onSpriteRemoved();

    public abstract void initialize();
    public abstract void update();
    public abstract void close();
}
