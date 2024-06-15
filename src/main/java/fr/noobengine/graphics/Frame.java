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
    private static final List<Long> DEFAULT_INITIAL_SIZE = List.of((long)(SCREEN_SIZE.getWidth() / 2), (long)(SCREEN_SIZE.getHeight() / 2));
    private static final List<Long> DEFAULT_MINIMUM_SIZE = List.of((long)(SCREEN_SIZE.getWidth() / 3), (long)(SCREEN_SIZE.getHeight() / 3));

    private static final boolean DEFAULT_IS_VISIBLE = true;
    private static final boolean DEFAULT_IS_RESIZABLE = true;

    private final Engine engine;
    private final Json configuration;

    private final JFrame frame;
    private final Panel panel;

    public Frame(Engine engine, Json configuration) {
        this.engine = engine;
        this.configuration = configuration;
        this.frame = new JFrame();
        this.panel = new Panel(engine, configuration.getJson("panel"), List.of((long)SCREEN_SIZE.width, (long)SCREEN_SIZE.height));
    }

    @Override
    public void initialize() {
        this.frame.setTitle(configuration.getString("title", DEFAULT_TITLE));

        int[] initialSize = configuration.getList("size.initial", Long.class, DEFAULT_INITIAL_SIZE).stream().mapToInt(Long::intValue).toArray();
        this.frame.setSize(initialSize[0], initialSize[1]);

        int[] minimumSize = configuration.getList("size.minimum", Long.class, DEFAULT_MINIMUM_SIZE).stream().mapToInt(Long::intValue).toArray();
        this.frame.setMinimumSize(new Dimension(minimumSize[0], minimumSize[1]));

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

        if(configuration.getBoolean("fullscreen", false)) {
            this.setFullScreen(true);
        }

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
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();

        if(fullScreen) {
            device.setFullScreenWindow(this.frame);
        } else {
            device.setFullScreenWindow(null);
        }
    }
}
