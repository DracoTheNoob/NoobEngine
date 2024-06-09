package fr.noobengine.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventManager {
    private final HashMap<Class<? extends Event>, List<EventHandler>> handlers;

    public EventManager() {
        this.handlers = new HashMap<>();
    }

    public void addEventHandler(EventHandler handler) {
        Class<? extends Event> eventType = handler.getEventType();

        if(!handlers.containsKey(eventType)) {
            handlers.put(eventType, new ArrayList<>());
        }

        this.handlers.get(eventType).add(handler);
    }

    public void proceed(Event event) {
        Class<? extends Event> eventType = event.getClass();

        for(EventHandler handler : this.handlers.getOrDefault(eventType, new ArrayList<>())) {
            if(handler.getEventType() == eventType) {
                handler.proceed(event);
            }
        }
    }
}
