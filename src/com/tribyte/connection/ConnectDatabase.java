package com.tribyte.connection;

import com.tribyte.model.ModelAttendance;
import java.sql.*;
import com.tribyte.model.ModelEvents; 
import java.util.ArrayList;
import java.util.List;

public class ConnectDatabase {

    public static Connection conn() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_event_management2";
            String user = "root";
            String password = "";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection Error: " + e.getMessage());
            return null;
        }
    }

    public void registerUser(String fName, String lName, String email, String phone, String pass) {
        String sql = "INSERT INTO users (first_name, last_name, email, contact_number, password, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = conn(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, fName);
            pstmt.setString(2, lName);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, pass);
            pstmt.setString(6, "Registrant");
            pstmt.executeUpdate();
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void saveEvent(ModelEvents ev) {
        String sql = "INSERT INTO events (event_name, event_date, venue, max_slots, status, accessibility, event_code, created_by, professor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conn(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, ev.getName());
            pst.setString(2, ev.getDate());
            pst.setString(3, ev.getVenue());
            pst.setInt(4, ev.getMaxSlots());
            pst.setString(5, ev.getStatus());
            pst.setString(6, ev.getAccessibility());
            pst.setString(7, ev.getEventCode());
            pst.setInt(8, ev.getOwnerID());   // ID from UserSession
            pst.setString(9, ev.getProfessor()); // Name from UserSession

            pst.executeUpdate();
            System.out.println("Event saved to Database successfully!");
        } catch (SQLException e) {
            System.out.println("SQL Save Event Error: " + e.getMessage());
        }
    }
    
    public boolean updateEvent(ModelEvents ev) {
        String sql = "UPDATE events SET event_name=?, event_date=?, venue=?, max_slots=?, status=?, accessibility=?, event_code=? WHERE event_id=?";

        try (Connection con = conn(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, ev.getName());
            pst.setString(2, ev.getDate());
            pst.setString(3, ev.getVenue());
            pst.setInt(4, ev.getMaxSlots());
            pst.setString(5, ev.getStatus());
            pst.setString(6, ev.getAccessibility());
            pst.setString(7, ev.getEventCode());
            pst.setInt(8, ev.getEventID()); // ID to find the correct row

            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("SQL Update Event Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean registerForEvent(int userId, int eventId) {
        String insertSql = "INSERT INTO registrations (user_id, event_id) VALUES (?, ?)";
        String updateSql = "UPDATE events SET filled_slots = filled_slots + 1 WHERE event_id = ?";
        // Add here the auto-lock logic when filled_slots meets max slots
        String autoLockSql = "UPDATE events SET status = 'Lock' WHERE event_id = ? AND filled_slots >= max_slots";

        try (Connection con = conn()) {
            con.setAutoCommit(false); 

            try (PreparedStatement psInsert = con.prepareStatement(insertSql)) {
                psInsert.setInt(1, userId);
                psInsert.setInt(2, eventId);
                psInsert.executeUpdate();
            }

            try (PreparedStatement psUpdate = con.prepareStatement(updateSql)) {
                psUpdate.setInt(1, eventId);
                psUpdate.executeUpdate();
            }
            
            try (PreparedStatement psLock= con.prepareStatement(autoLockSql)) {
                psLock.setInt(1,eventId);
                psLock.executeUpdate();
            }

            con.commit(); 
            return true;

        } catch (SQLException e) {
            System.out.println("Registration Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteEvent(int eventId) {
        String sql = "DELETE FROM events WHERE event_id = ?";
        try (Connection con = conn(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, eventId);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL Delete Error: " + e.getMessage());
            return false;
        }
    }
    
    public List<ModelAttendance> getAttendanceList(int eventId) {
        List<ModelAttendance> list = new ArrayList<>();
        String sql = "SELECT u.first_name, u.last_name, u.email, r.registered_at, r.attendance_status "
                + "FROM registrations r "
                + "JOIN users u ON r.user_id = u.user_id "
                + "WHERE r.event_id = ?";

        try (Connection con = conn(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, eventId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                    list.add(new ModelAttendance(
                            fullName,
                            rs.getString("email"),
                            rs.getString("registered_at"),
                            rs.getString("attendance_status")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Attendance Load Error: " + e.getMessage());
        }
        return list;
    }
    
    public int getCount(String tableName, String condition) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM " + tableName + (condition.isEmpty() ? "" : " WHERE " + condition);

        try (Connection con = conn(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error getting count for " + tableName + ": " + e.getMessage());
        }
        return count;
    }
    
    public boolean unregisterStudent(String email, int eventId) {
        String sql = "DELETE FROM registrations WHERE event_id = ? AND user_id = (SELECT user_id FROM users WHERE email = ?)";

        try (Connection con = conn(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, eventId);
            pst.setString(2, email);

            String updateSql = "UPDATE events SET filled_slots = filled_slots - 1 WHERE event_id = ?";
            try (PreparedStatement psUpdate = con.prepareStatement(updateSql)) {
                psUpdate.setInt(1, eventId);
                psUpdate.executeUpdate();
            }

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Unregister Error: " + e.getMessage());
            return false;
        }
    }
}