package fr.noobengine.time;

import fr.javason.Json;

public class TimeManager {
    private static final int DEFAULT_MAX_FPS = 100;

    private final int maxFps;
    private final long updateInterval;

    private int frames;
    private int fps;
    private int delta;

    private long lastFrameMeasure;
    private long lastUpdate;

    private long lastFrameUpdate;
    private long secondLastFrameUpdate;

    public TimeManager(Json configuration) {
        this.maxFps = configuration.getInt("max_fps", DEFAULT_MAX_FPS);
        this.updateInterval = 1000 / this.maxFps;

        long currentTime = System.currentTimeMillis();
        this.frames = 0;
        this.fps = 0;
        this.delta = 0;
        this.lastFrameMeasure = currentTime;
        this.lastUpdate = currentTime;
    }

    public boolean shouldUpdate() {
        return System.currentTimeMillis() - lastUpdate >= updateInterval;
    }

    public boolean shouldMeasureFps() {
        return System.currentTimeMillis() - lastFrameMeasure >= 1000;
    }

    public void increment() {
        this.frames++;
        this.lastUpdate += updateInterval;
    }

    public void measureFps() {
        this.lastFrameMeasure = System.currentTimeMillis();
        this.fps = frames;
        this.frames = 0;
    }

    public void measureDelta() {
        this.delta = (int)(lastFrameUpdate - secondLastFrameUpdate);
        secondLastFrameUpdate = lastFrameUpdate;
        lastFrameUpdate = System.currentTimeMillis();
    }

    public int getMaxFps() { return maxFps; }
    public int getFps() { return fps; }
    public int getDelta() { return delta; }
}
