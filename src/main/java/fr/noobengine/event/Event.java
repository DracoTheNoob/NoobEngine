package fr.noobengine.event;

public abstract sealed class Event permits KeyEvent, ClickEvent {
    private final long when;

    public Event() {
        this.when = System.currentTimeMillis();
    }

    public final long getWhen() { return when; }
}
