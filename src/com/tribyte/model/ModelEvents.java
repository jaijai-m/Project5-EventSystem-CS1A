package com.tribyte.model;

import com.tribyte.swing.table.EventAction;
import com.tribyte.swing.table.ModelAction;

public class ModelEvents {

    private int eventID;
    private int ownerID;
    private String name;
    private String date;
    private String venue;
    private int filledSlots;
    private int maxSlots;
    private String status;
    private String joinedTime;
    private String leftTime;  
    private String professor;
    private String accessibility;
    private String eventCode;

    public ModelEvents(int eventID, int ownerID, String name, String date, String venue, int filledSlots, int maxSlots, String status, String joinedTime, String leftTime, String professor, String accessibility, String eventCode) {
        this.eventID = eventID;
        this.ownerID = ownerID;
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.filledSlots = filledSlots;
        this.maxSlots = maxSlots;
        this.status = status;
        this.joinedTime = joinedTime;
        this.leftTime = leftTime;
        this.professor = professor;
        this.accessibility = accessibility;
        this.eventID = eventID;
        this.eventCode = eventCode;
    }

    public ModelEvents() {
    }

    // ID Getters
    public int getEventID() {
        return eventID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    // Text Getters & Setters
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    public String getStatus() {
        return status;
    }

    public String getJoinedTime() {
        return joinedTime;
    }

    public String getLeftTime() {
        return leftTime;
    }

    // Slot Getters
    public int getFilledSlots() {
        return filledSlots;
    }

    public int getMaxSlots() {
        return maxSlots;
    }
    
    public String getProfessor() {
        return professor;
    }

    public String getAccessibility() {
        return accessibility;
    }
    
    public String getEventCode() {
        return eventCode;
    }

    //Table in FormHome
    public Object[] toRowTable(EventAction event) {
        String slotDisplay = filledSlots + " / " + maxSlots;

        return new Object[]{name, date, venue, slotDisplay, new ModelAction(this, event)
        };
    }
}
