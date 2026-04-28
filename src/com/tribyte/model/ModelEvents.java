package com.tribyte.model;

import com.tribyte.swing.table.EventAction;
import com.tribyte.swing.table.ModelAction;

public class ModelEvents {

    public int getFilledSlots() {
        return filledSlots;
    }

    public void setFilledSlots(int filledSlots) {
        this.filledSlots = filledSlots;
    }

    public int getMaxSlots() {
        return maxSlots;
    }

    public void setMaxSlots(int maxSlots) {
        this.maxSlots = maxSlots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    

    public ModelEvents(String name, String date, String venue, int filledSlots, int maxSlots) {
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.filledSlots = filledSlots;
        this.maxSlots = maxSlots;
    }

    public ModelEvents() {
    }
    
    private String name;
    private String date;
    private String venue;
    private int filledSlots;
    private int maxSlots;
    
    public Object[]toRowTable(EventAction event) {
        String slotDisplay = getFilledSlots() + " / " + getMaxSlots();
        return new Object[]{name, date, venue, slotDisplay, new ModelAction(this, event)};
    }
}
