package com.tribyte.model;

import com.tribyte.connection.ConnectDatabase;
import com.tribyte.connection.UserSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelEventStorage {

    public static List<ModelEvents> eventList = new ArrayList<>();

    public static void loadFromDatabase() {
        eventList.clear();
        int currentUserID = UserSession.getInstance().getUserId();

        String sql = "SELECT e.*, (SELECT 1 FROM registrations r WHERE r.event_id = e.event_id AND r.user_id = ?) as is_joined FROM events e";

        try (Connection con = ConnectDatabase.conn(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, currentUserID);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    ModelEvents ev = new ModelEvents(
                            rs.getInt("event_id"),
                            rs.getInt("created_by"),
                            rs.getString("event_name"),
                            rs.getString("event_date"),
                            rs.getString("venue"),
                            rs.getInt("filled_slots"),
                            rs.getInt("max_slots"),
                            rs.getString("status"),
                            "---", "---",
                            rs.getString("professor"),
                            rs.getString("accessibility"),
                            rs.getString("event_code")
                    );

                    ev.setJoined(rs.getInt("is_joined") == 1);

                    eventList.add(ev);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading events with join status: " + e.getMessage());
        }
    }
    
    // Helper method to check attendance
    private static boolean checkIfUserJoined(Connection con, int eventId, int userId) {
        String sql = "SELECT 1 FROM event_attendance WHERE event_id = ? AND user_id = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, eventId);
            pst.setInt(2, userId);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next(); 
            }
        } catch (SQLException e) {
            return false;
        }
    }
}