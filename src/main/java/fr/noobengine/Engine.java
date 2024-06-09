package fr.noobengine;

import fr.javason.Json;
import fr.noobengine.core.Cycle;
import fr.noobengine.core.Scene;
import fr.noobengine.event.EventManager;
import fr.noobengine.graphics.Frame;
import fr.noobengine.input.InputManager;
import fr.noobengine.io.FileManager;
import fr.noobengine.time.TimeManager;

import java.io.File;

public class Engine implements Cycle, Runnable {
    private boolean running = true;
    private boolean initialized = false;

    private final FileManager files;
    private final Json configuration;

    private final Frame frame;
    private final TimeManager timeManager;
    private final InputManager inputManager;
    private final EventManager eventManager;

    private Scene currentScene;

    public Engine() {
        this.files = new FileManager(new File(System.getProperty("user.home"), "noobengine"));
        this.configuration = new Json(files.get("configuration.json"));

        this.frame = new Frame(this, configuration.getJson("window"));
        this.timeManager = new TimeManager(configuration.getJson("time"));
        this.eventManager = new EventManager();
        this.inputManager = new InputManager(eventManager);
    }

    @Override
    public void initialize() {
        this.currentScene.initialize();
        this.frame.initialize();
        this.initialized = true;
    }

    @Override
    public void update() {
        this.timeManager.measureDelta();
        this.currentScene.update();
        this.frame.update();
    }

    @Override
    public void close() {
        this.currentScene.close();
        System.exit(0);
    }

    @Override
    public void run() {
        this.initialize();
        long start = System.currentTimeMillis();

        while(this.running) {
            while(timeManager.shouldUpdate()) {
                this.timeManager.increment();
                this.update();
            }

            if(timeManager.shouldMeasureFps()) {
                timeManager.measureFps();
            }
        }

        this.close();
    }

    public FileManager getFiles() { return files; }
    public Json getConfiguration() { return configuration; }
    public Frame getFrame() { return frame; }
    public TimeManager getTimeManager() { return timeManager; }
    public InputManager getInputManager() { return inputManager; }
    public EventManager getEventManager() { return eventManager; }
    public Scene getCurrentScene() { return currentScene; }

    public int getDeltaTime() { return timeManager.getDelta(); }

    public void setRunning(boolean running) { this.running = running; }
    public void setScene(Scene scene) {
        if(this.currentScene != null && initialized) {
            this.currentScene.close();
        }

        this.currentScene = scene;

        if(initialized) {
            this.currentScene.initialize();
        }
    }
}
