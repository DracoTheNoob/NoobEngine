import fr.noobengine.Engine;
import fr.noobengine.core.Scene;
import fr.noobengine.core.Sprite;
import fr.noobengine.math.Vector;

public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine();
        Scene scene = new Scene(engine);

        scene.addSprite(new Sprite(engine, scene, new Vector(0, 0), new Vector(1, 1), "debug.png"));

        engine.setScene(scene);
        engine.run();
    }
}
