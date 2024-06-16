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
import java.util.Objects;

public class Panel extends JPanel implements Cycle {
    private static final Color DEFAULT_BACKGROUND_COLOR = new Color(32, 32, 32);
    private static final Color DEFAULT_OFFSET_COLOR = new Color(10, 10, 10);

    private final Engine engine;
    private final Json configuration;
    private final HashMap<String, BufferedImage> textures;
    private Dimension resolution;
    private double baseRatio;

    private Color background;
    private Color offsetColor;

    public Panel(Engine engine, Json configuration, Dimension defaultResolution) {
        this.engine = engine;
        this.configuration = configuration;
        this.textures = new HashMap<>();
        this.setResolution(configuration.getDimension("resolution", defaultResolution));
    }

    @Override
    public void initialize() {
        this.background = configuration.getColor("background", DEFAULT_BACKGROUND_COLOR);
        this.offsetColor = configuration.getColor("offset", DEFAULT_OFFSET_COLOR);

        this.loadTextures(engine.getFiles().get("src/textures/"));
    }

    @Override
    public void update() {
        this.repaint();
    }

    @Override
    public void close() {

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        int width = this.resolution.width;
        int height = this.resolution.height;

        BufferedImage render = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) render.getGraphics();

        g.setColor(background);
        g.fillRect(0, 0, width, height);

        for(Sprite sprite : this.engine.getCurrentScene().getSprites()) {
            sprite.render(g, textures.get(sprite.getTexture()), resolution);
        }

        width = getWidth();
        height = getHeight();

        if((double)width/height == baseRatio) {
            graphics.drawImage(render, 0, 0, width, height, null);
        } else if((double)width / height > baseRatio) {
            int offset = (int)(width - height * (16f/9));

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

    public void setResolution(Dimension resolution) {
        this.resolution = resolution;
        this.baseRatio = (double)resolution.width / resolution.height;
    }
}
