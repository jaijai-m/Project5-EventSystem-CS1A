package com.tribyte.connection;

public class UserSession {

    private static UserSession instance;
    private int userId;
    private String firstName;
    private String lastName;
    private String role; 

    private UserSession(int userId, String firstName, String lastName, String role) { // 2. Update constructor
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public static void init(int userId, String firstName, String lastName, String role) {
        instance = new UserSession(userId, firstName, lastName, role);
    }

    public static UserSession getInstance() {
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
