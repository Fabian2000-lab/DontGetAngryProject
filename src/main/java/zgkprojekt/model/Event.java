package zgkprojekt.model;

import zgkprojekt.enums.EventType;

public class Event {
    private EventType event;
    private int cycle;

    public Event(EventType event, int cycle) {
        this.event = event;
        this.cycle = cycle;
    } 
}
