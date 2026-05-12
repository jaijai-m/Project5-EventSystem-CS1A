package com.tribyte.model;

public class ModelAttendance {

    private String fullName;
    private String email;
    private String timeIn;   
    private String timeOut;  
    private String status;

    public ModelAttendance(String fullName, String email, String timeIn, String timeOut, String status) {
        this.fullName = fullName;
        this.email = email;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.status = status;
    }

    public String getName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public String getTimeOut() { 
        return timeOut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
