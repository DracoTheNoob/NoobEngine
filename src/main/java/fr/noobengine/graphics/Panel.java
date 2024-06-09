package fr.noobengine.graphics;

import fr.javason.Json;
import fr.noobengine.Engine;
import fr.noobengine.core.Cycle;
import fr.noobengine.core.Sprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Panel extends JPanel implements Cycle {
    private static final List<Long> DEFAULT_COLOR_RGB = List.of(30L, 30L, 30L);
    private static final List<Long> DEFAULT_OFFSET_COLOR_RGB = List.of(5L, 5L, 5L);

    private final Engine engine;
    private final Json configuration;
    private final HashMap<String, BufferedImage> textures;
    private Dimension resolution;

    private Color background;
    private Color offsetColor;

    public Panel(Engine engine, Json configuration, Dimension resolution) {
        this.engine = engine;
        this.configuration = configuration;
        this.textures = new HashMap<>();
        this.resolution = resolution;
    }

    @Override
    public void initialize() {
        this.background = loadColor("background", DEFAULT_COLOR_RGB);
        this.offsetColor = loadColor("offset", DEFAULT_OFFSET_COLOR_RGB);

        this.loadTextures(engine.getFiles().get("src/textures/"));
    }

    @Override
    public void update() {
        this.repaint();
    }

    @Override
    public void close() {

    }

    float baseRatio = 16f / 9f;

    @Override
    protected void paintComponent(Graphics graphics) {
        int width = this.resolution.width;
        int height = this.resolution.height;

        BufferedImage render = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) render.getGraphics();

        g.setColor(background);
        g.fillRect(0, 0, width, height);

        for(Sprite sprite : this.engine.getCurrentScene().getSprites()) {
            g.drawImage(textures.get(sprite.getTexture()), (int) sprite.getLocation().getX(), (int) sprite.getLocation().getY(), null);
        }

        width = getWidth();
        height = getHeight();

        if((double)width/height == baseRatio) {
            graphics.drawImage(render, 0, 0, width, height, null);
        } else if((double)width / height > baseRatio) {
            int offset = width - height * 16/9;

            // TODO : can be optimized
            graphics.setColor(offsetColor);
            graphics.fillRect(0, 0, width, height);

            graphics.drawImage(render, offset/2, 0, width-offset, height, null);
        } else {
            int offset = (int)(height - width / (16f/9));

            // TODO : can be optimized
            graphics.setColor(offsetColor);
            graphics.fillRect(0, 0, width, height);

            graphics.drawImage(render, 0, offset/2, width, height-offset, null);
        }
    }

    private void loadTextures(File folder) {
        if(!folder.isDirectory()) {
            return;
        }

        for(File subFile : Objects.requireNonNull(folder.listFiles())) {
            if(subFile.isDirectory()) {
                loadTextures(subFile);
                continue;
            }

            try {
                this.textures.put(subFile.getName(), ImageIO.read(subFile));
            } catch(IOException e) {
                System.err.printf("Unable to load texture '%s'\n", subFile.getPath());
            }
        }
    }

    private Color loadColor(String key, List<Long> defaultRgb) {
        int[] backgroundRgb = configuration.getList("colors." + key, Long.class, defaultRgb).stream().mapToInt(Long::intValue).toArray();

        if(backgroundRgb.length == 4) {
            return new Color(backgroundRgb[0], backgroundRgb[1], backgroundRgb[2], backgroundRgb[3]);
        }

        return new Color(backgroundRgb[0], backgroundRgb[1], backgroundRgb[2]);
    }
}
