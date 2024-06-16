import fr.noobengine.core.Script;
import fr.noobengine.core.Sprite;
import fr.noobengine.input.InputManager;
import fr.noobengine.math.Vector;

import java.awt.event.KeyEvent;

public class PlayerScript extends Script {
    private final Sprite s2;

    public PlayerScript(Sprite sprite, Sprite s2) {
        super(sprite);

        this.s2 = s2;
    }

    @Override
    public void onSpriteAdded() {

    }

    @Override
    public void onSpriteRemoved() {

    }

    @Override
    public void initialize() {

    }

    @Override
    public void update() {
        InputManager in = sprite.getEngine().getInputManager();

        Vector velocity = new Vector(0, 0);

        if(in.isKeyPressed(KeyEvent.VK_Z)) {
            velocity = velocity.translate(new Vector(0, 1));
        }
        if(in.isKeyPressed(KeyEvent.VK_S)) {
            velocity = velocity.translate(new Vector(0, -1));
        }
        if(in.isKeyPressed(KeyEvent.VK_D)) {
            velocity = velocity.translate(new Vector(1, 0));
        }
        if(in.isKeyPressed(KeyEvent.VK_Q)) {
            velocity = velocity.translate(new Vector(-1, 0));
        }

        velocity = velocity.multiply(sprite.getEngine().getDeltaTime());
        sprite.setLocation(sprite.getLocation().translate(velocity));

        System.out.println(sprite.collides(s2) + " ====> " + sprite.getEffectiveCollision() + " AND " + s2.getEffectiveCollision());
    }

    @Override
    public void close() {

    }
}
