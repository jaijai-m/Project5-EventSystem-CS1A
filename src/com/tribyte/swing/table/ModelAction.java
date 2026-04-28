package com.tribyte.swing.table;

import com.tribyte.model.ModelEvents;

public class ModelAction {

    public ModelEvents getEvents() {
        return events;
    }

    public void setEvents(ModelEvents events) {
        this.events = events;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

    public ModelAction(ModelEvents events, EventAction event) {
        this.events = events;
        this.event = event;
    }

    public ModelAction() {
    }
    
    private ModelEvents events;
    private EventAction event;
}
