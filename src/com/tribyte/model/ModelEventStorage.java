package com.tribyte.model;

import database.connection.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;

public class ModelEventStorage {
    public static final List<ModelEvents> eventList = new ArrayList<>();

    //Temporary storage for FormEvents
    
    static {
        // Event owned by someone else (ID 101)
        eventList.add(new ModelEvents(1, 101, "CCS Week: Day 1", "April 26, 2026", "Main Lobby", 15, 100, "Open", "9:52 AM", "3:37 PM", "Dr. Smith", "Public", "NONE"));

        // Event owned by YOU (ID 102)
        eventList.add(new ModelEvents(2, 102, "CCS Week: Day 2", "April 27, 2026", "Gymnasium", 100, 100, "Open", "---", "---", "Kenneth Bautista", "Private", "CCS123"));
    }
    
    public static void loadEventsFromDatabase() {
        // Clear the local list first so no duplicates
        com.tribyte.model.ModelEventStorage.eventList.clear();

        String sql = "SELECT e.*, CONCAT(u.first_name, ' ', IFNULL(u.middle_name, ' '), ' ', u.last_name) AS full_name " + 
                     "FROM events e " +
                     "JOIN users u ON e.created_by = u.user_id";

        try (java.sql.Connection conn = DatabaseConnection.getConnection();
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String professor = rs.getString("full_name");

                // Create the Model object for each row in the database
                ModelEvents event = new ModelEvents(
                    rs.getInt("event_id"),
                    rs.getInt("created_by"),
                    rs.getString("event_name"),
                    rs.getString("event_date"),
                    rs.getString("venue"),
                    rs.getInt("filled_slots"),
                    rs.getInt("max_slots"),
                    rs.getString("status"),
                    "---", "---", // Placeholder for joined/left times
                    professor,     
                    rs.getString("accessibility"),
                    rs.getString("event_code")
                );

                // Add it to the Local List
                eventList.add(event);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
}
