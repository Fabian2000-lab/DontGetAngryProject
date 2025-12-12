package zgkprojekt.model;

import zgkprojekt.enums.EventType;

public class Event {
    private EventType event;

    // This is the number to signify the amount of player turns this event should run
    // It is based on the number of players in the game
    // When cycle is 0 a new event should start
    private int cycle;

    public Event(EventType event, int cycle) {
        this.event = event;
        this.cycle = cycle;
    }

    public EventType getEventType() {
        return this.event;
    }

    public void setCycle(int count) {
        this.cycle = count;
    }

    public int getCycle() {
        return this.cycle;
    }
}
