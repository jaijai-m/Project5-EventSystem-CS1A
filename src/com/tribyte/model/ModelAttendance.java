package com.tribyte.model;

public class ModelAttendance {

    private String fullName;
    private String email;
    private String registeredAt;
    private String status;

    public ModelAttendance(String fullName, String email, String registeredAt, String status) {
        this.fullName = fullName;
        this.email = email;
        this.registeredAt = registeredAt;
        this.status = status;
    }

    public String getName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }

    public String getStatus() {
        return status;
    }
}
