import fr.noobengine.Engine;
import fr.noobengine.core.Scene;
import fr.noobengine.core.Sprite;
import fr.noobengine.math.Collision;
import fr.noobengine.math.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PolygonSprite extends Sprite {
    private static final List<UUID> ids = new ArrayList<>();
    private static final Color[] colors = {
            Color.red,
            Color.blue,
            Color.green
    };

    public PolygonSprite(Engine engine, Scene scene, Collision collision) {
        super(engine, scene, Vector.NULL, Vector.NORMAL, null, collision);
        ids.add(this.getId());
    }

    @Override
    public void render(Graphics2D g, BufferedImage texture, Dimension screen) {
        Vector[] points = this.collision.getPoints();

        int nPoints = points.length;
        int[] xPoints = new int[nPoints];
        int[] yPoints = new int[nPoints];

        for(int i = 0; i < nPoints; i++) {
            xPoints[i] = (int)((points[i].getX() + this.location.getX()) * 20 + screen.width/2);
            yPoints[i] = (int)(-(points[i].getY() + this.location.getY()) * 20 + screen.width/2);
        }

        Polygon polygon = new Polygon(xPoints, yPoints, nPoints);
        g.setColor(colors[ids.indexOf(this.getId())]);
        g.drawPolygon(polygon);
    }
}
