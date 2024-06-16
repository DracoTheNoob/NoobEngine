import com.sun.security.jgss.GSSUtil;
import fr.noobengine.Engine;
import fr.noobengine.core.Scene;
import fr.noobengine.core.Script;
import fr.noobengine.core.Sprite;
import fr.noobengine.core.SpriteBuilder;
import fr.noobengine.math.Collision;
import fr.noobengine.math.Polygon;
import fr.noobengine.math.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine();
        Scene scene = new Scene(engine);

        Vector A = new Vector(-50, -50);
        Vector B = new Vector(50, -50);
        Vector C = new Vector(50, 50);
        Vector D = new Vector(-50, 50);

        Vector E = A.multiply(.2).translate(100, 100);
        Vector F = B.multiply(.2).translate(100, 100);
        Vector G = C.multiply(.2).translate(100, 100);
        Vector H = D.multiply(.2).translate(100, 100);

        Collision c1 = new Collision(null, A, B, C, D);
        Collision c2 = new Collision(null, E, F, G, H);

        PolygonSprite s1 = new PolygonSprite(engine, scene, c1);
        PolygonSprite s2 = new PolygonSprite(engine, scene, c2);

        s1.addScript(new PlayerScript(s1, s2));

        scene.addSprites(s1, s2);

        engine.setScene(scene);
        engine.run();

        // */
    }
}
