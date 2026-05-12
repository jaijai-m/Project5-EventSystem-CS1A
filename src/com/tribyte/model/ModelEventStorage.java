package com.tribyte.model;

import com.tribyte.connection.ConnectDatabase;
import com.tribyte.connection.UserSession;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class ModelEventStorage {

    public static List<ModelEvents> eventList = new ArrayList<>();

    public static void loadFromDatabase(String role, int currentUserID) {
        if (eventList != null) {
            eventList.clear();
        } else {
            eventList = new ArrayList<>();
        }

        String sql;
        if ("Staff".equalsIgnoreCase(role)) {
            sql = "SELECT e.*, r.attendance_status, r.time_in, r.time_out, "
                    + "(CASE WHEN r.event_id IS NOT NULL THEN 1 ELSE 0 END) AS is_joined "
                    + "FROM events e "
                    + "LEFT JOIN registrations r ON e.event_id = r.event_id";
        } else {
            sql = "SELECT e.*, r.attendance_status, r.time_in, r.time_out, "
                    + "(CASE WHEN r.event_id IS NOT NULL THEN 1 ELSE 0 END) AS is_joined "
                    + "FROM events e "
                    + "LEFT JOIN registrations r ON e.event_id = r.event_id AND r.user_id = ?";
        }

        try (Connection con = ConnectDatabase.conn(); PreparedStatement pst = con.prepareStatement(sql)) {
            if (!"Staff".equalsIgnoreCase(role)) {
                pst.setInt(1, currentUserID);
            }

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String rawIn = rs.getString("time_in");
                    String rawOut = rs.getString("time_out");

                    System.out.println("DB Check - Event: " + rs.getString("event_name") + " | In: " + rawIn + " | Out: " + rawOut);

                    String formattedIn = "---";
                    String formattedOut = "---";
                    SimpleDateFormat amPmFormat = new SimpleDateFormat("hh:mm a");

                    try {
                        if (rawIn != null) {
                            formattedIn = amPmFormat.format(rs.getTimestamp("time_in"));
                        }
                        if (rawOut != null) {
                            formattedOut = amPmFormat.format(rs.getTimestamp("time_out"));
                        }
                    } catch (Exception e) {
                        formattedIn = (rawIn != null) ? rawIn : "---";
                        formattedOut = (rawOut != null) ? rawOut : "---";
                    }

                    ModelEvents ev = new ModelEvents(
                            rs.getInt("event_id"),
                            rs.getInt("created_by"),
                            rs.getString("event_name"),
                            rs.getString("event_date"),
                            rs.getString("venue"),
                            rs.getInt("filled_slots"),
                            rs.getInt("max_slots"),
                            rs.getString("status"),
                            formattedIn,
                            formattedOut,
                            rs.getString("professor"),
                            rs.getString("accessibility"),
                            rs.getString("event_code")
                    );

                    ev.setJoined(rs.getInt("is_joined") == 1);
                    ev.setUserAttendanceStatus(rs.getString("attendance_status"));

                    eventList.add(ev);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading events with join status: " + e.getMessage());
        }
    }

    private static boolean checkIfUserJoined(Connection con, int eventId, int userId) {
        String sql = "SELECT 1 FROM registrations WHERE event_id = ? AND user_id = ?";
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