/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tribyte.utilities;

/**
 *
 * @author jaijaimnglndn07
 */
public class UserSession {
    private static UserSession instance;
    private int userId;
    private String userName;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUser(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }
    
    public String getUserName() {
        return userName;
    }

    public void cleanUserSession() {
        userId = 0;
        userName = null;
    }
}
