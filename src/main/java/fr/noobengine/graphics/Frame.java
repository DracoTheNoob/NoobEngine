package fr.noobengine.graphics;

import fr.javason.Json;
import fr.noobengine.Engine;
import fr.noobengine.core.Cycle;
import fr.noobengine.graphics.listeners.FrameListener;
import fr.noobengine.input.InputManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Frame implements Cycle {
    private static final String DEFAULT_TITLE = String.format("NoobEngine Application V-%s", Frame.class.getPackage().getImplementationVersion());

    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension DEFAULT_INITIAL_SIZE = new Dimension((int)(SCREEN_SIZE.getWidth() / 2), (int)(SCREEN_SIZE.getHeight() / 2));
    private static final Dimension DEFAULT_MINIMUM_SIZE = new Dimension((int)(SCREEN_SIZE.getWidth() / 3), (int)(SCREEN_SIZE.getHeight() / 3));

    private static final boolean DEFAULT_IS_VISIBLE = true;
    private static final boolean DEFAULT_IS_RESIZABLE = true;

    private final Engine engine;
    private final Json configuration;

    private final JFrame frame;
    private final Panel panel;

    private boolean fullscreen;
    private final GraphicsDevice device;

    public Frame(Engine engine, Json configuration) {
        this.engine = engine;
        this.configuration = configuration;
        this.frame = new JFrame();
        this.panel = new Panel(engine, configuration.getJson("panel"), SCREEN_SIZE);
        this.device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    }

    @Override
    public void initialize() {
        this.frame.setTitle(configuration.getString("title", DEFAULT_TITLE));

        this.frame.setSize(configuration.getDimension("size.initial", DEFAULT_INITIAL_SIZE));
        this.frame.setMinimumSize(configuration.getDimension("size.minimum", DEFAULT_MINIMUM_SIZE));

        this.frame.setLocationRelativeTo(null);

        this.frame.setResizable(configuration.getBoolean("is_resizable", DEFAULT_IS_RESIZABLE));
        this.frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        this.frame.addWindowListener(new FrameListener(this));

        this.panel.initialize();
        this.frame.setContentPane(panel);

        InputManager input = this.engine.getInputManager();
        this.frame.addKeyListener(input);
        this.frame.addMouseListener(input);
        this.frame.addMouseMotionListener(input);
        this.frame.addMouseWheelListener(input);

        this.setFullScreen(configuration.getBoolean("is_fullscreen", false));
        this.frame.setVisible(configuration.getBoolean("is_visible", DEFAULT_IS_VISIBLE));
    }

    @Override
    public void update() {
        this.panel.update();
    }

    @Override
    public void close() {
        this.engine.setRunning(false);
        this.frame.dispose();
    }

    public void setFullScreen(boolean fullScreen) {
        if(fullScreen) {
            device.setFullScreenWindow(this.frame);
        } else {
            device.setFullScreenWindow(null);
        }

        this.fullscreen = fullScreen;
    }

    public boolean isFullscreen() { return fullscreen; }
}
