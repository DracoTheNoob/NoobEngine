import fr.noobengine.Engine;
import fr.noobengine.core.Scene;
import fr.noobengine.core.Sprite;
import fr.noobengine.core.SpriteBuilder;
import fr.noobengine.math.Collision;
import fr.noobengine.math.Polygon;
import fr.noobengine.math.Vector;

public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine();
        Scene scene = new Scene(engine);

        Vector A = new Vector(-5, -5);
        Vector B = new Vector(5, -5);
        Vector C = new Vector(5, 5);
        Vector D = new Vector(-5, 5);

        Vector E = A.multiply(.2);
        Vector F = B.multiply(.2);
        Vector G = C.multiply(.2);
        Vector H = D.multiply(.2);

        Collision c1 = new Collision(null, A, B, C, D);
        Collision c2 = new Collision(null, E, F, G, H);

        PolygonSprite s1 = new PolygonSprite(engine, scene, c1);
        PolygonSprite s2 = new PolygonSprite(engine, scene, c2);

        scene.addSprites(s1, s2);

        System.out.println(s1.collides(s2));

        engine.setScene(scene);
        engine.run();
    }
}
