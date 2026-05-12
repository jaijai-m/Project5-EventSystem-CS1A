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
    private boolean joined;
    private String timeIn = "---";
    private String timeOut = "---";

    public ModelEvents(int eventID, int ownerID, String name, String date, String venue,
            int filledSlots, int maxSlots, String status,
            String timeIn, String timeOut, 
            String professor, String accessibility, String eventCode) {
        this.eventID = eventID;
        this.ownerID = ownerID;
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.filledSlots = filledSlots;
        this.maxSlots = maxSlots;
        this.status = status;

        this.timeIn = timeIn;
        this.timeOut = timeOut;

        this.professor = professor;
        this.accessibility = accessibility;
        this.eventCode = eventCode;
    }
    public ModelEvents() {
    }

    public int getEventID() {
        return eventID;
    }

    public int getOwnerID() {
        return ownerID;
    }

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
    
    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }

    public boolean isUserJoined(int userId) {
        return this.joined;
    }
    
    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }
    
    private String userAttendanceStatus = "Pending"; 

    public String getUserAttendanceStatus() {
        return userAttendanceStatus;
    }

    public void setUserAttendanceStatus(String userAttendanceStatus) {
        this.userAttendanceStatus = userAttendanceStatus;
    }
    
    //Table in FormHome
    public Object[] toRowTable(EventAction event) {
        String slotDisplay = filledSlots + " / " + maxSlots;

        return new Object[]{name, date, venue, slotDisplay, new ModelAction(this, event)
        };
    }
}
