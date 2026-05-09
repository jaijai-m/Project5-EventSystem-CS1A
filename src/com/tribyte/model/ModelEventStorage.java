package com.tribyte.model;

import com.tribyte.utilities.UserSession;
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

        String sql = "SELECT * FROM events";

        try (java.sql.Connection conn = database.connection.DatabaseConnection.getConnection();
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Get the numbers first
                int filled = rs.getInt("filled_slots");
                int max = rs.getInt("max_slots");
                String professor = UserSession.getInstance().getUserName();

                // Calculate the status locally
                // If filled is equal or more than max, the status is "Closed"
                String status = (filled >= max) ? "Closed" : "Open";
                
                // Create the Model object for each row in the database
                ModelEvents event = new ModelEvents(
                    rs.getInt("event_id"),
                    rs.getInt("created_by"),
                    rs.getString("event_name"),
                    rs.getString("event_date"),
                    rs.getString("venue"),
                    filled,
                    max,
                    status,
                    "---", "---", // Placeholder for joined/left times
                    professor,     
                    "Public",     // Placeholder for accessibility
                    rs.getString("event_code")
                );

                // Add it to the Local List
                ModelEventStorage.eventList.add(event);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
}
