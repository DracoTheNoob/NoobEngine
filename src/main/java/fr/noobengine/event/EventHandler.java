package fr.noobengine.event;

public abstract class EventHandler {
    private final Class<? extends Event> eventType;

    public EventHandler(Class<? extends Event> eventType) {
        this.eventType = eventType;
    }

    protected abstract void proceed(Event e);

    public final Class<? extends Event> getEventType() { return eventType; }
}
