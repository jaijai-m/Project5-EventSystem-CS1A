package com.tribyte.connection;

import com.tribyte.model.ModelAttendance;
import java.sql.*;
import com.tribyte.model.ModelEvents; 
import java.util.ArrayList;
import java.util.List;

public class ConnectDatabase {

    public static Connection conn() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_event_management";
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
            pst.setInt(8, ev.getOwnerID());
            pst.setString(9, ev.getProfessor());

            pst.executeUpdate();
            System.out.println("Event saved to Database successfully!");
        } catch (SQLException e) {
            System.out.println("SQL Save Event Error: " + e.getMessage());
        }
    }

    public boolean updateEvent(ModelEvents ev) {
        String sql = "UPDATE events SET event_name=?, event_date=?, venue=?, max_slots=?, status=?, accessibility=?, event_code=? WHERE event_id=?";

        try (Connection con = conn()) {
            con.setAutoCommit(false); // Start transaction

            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, ev.getName());
                pst.setString(2, ev.getDate());
                pst.setString(3, ev.getVenue());
                pst.setInt(4, ev.getMaxSlots());
                pst.setString(5, ev.getStatus());
                pst.setString(6, ev.getAccessibility());
                pst.setString(7, ev.getEventCode());
                pst.setInt(8, ev.getEventID());
                pst.executeUpdate();
            }

            if ("Lock".equalsIgnoreCase(ev.getStatus())) {
                String markAbsent = "UPDATE registrations SET attendance_status = 'Absent' WHERE event_id = ? AND attendance_status = 'Pending'";
                String markAttended = "UPDATE registrations SET attendance_status = 'Attended', time_out = NOW() WHERE event_id = ? AND attendance_status = 'Present'";

                try (PreparedStatement psAbs = con.prepareStatement(markAbsent); PreparedStatement psAtt = con.prepareStatement(markAttended)) {
                    psAbs.setInt(1, ev.getEventID());
                    psAbs.executeUpdate();
                    psAtt.setInt(1, ev.getEventID());
                    psAtt.executeUpdate();
                }
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL Update Event Error: " + e.getMessage());
            return false;
        }
    }

    public boolean registerForEvent(int userId, int eventId) {
        String insertSql = "INSERT INTO registrations (user_id, event_id, attendance_status) VALUES (?, ?, 'Pending')";
        String updateSql = "UPDATE events SET filled_slots = filled_slots + 1 WHERE event_id = ? AND filled_slots < max_slots";
        String autoLockSql = "UPDATE events SET status = 'LOCK' WHERE event_id = ? AND filled_slots >= max_slots";

        try (Connection con = conn()) {
            con.setAutoCommit(false); 
            
            int slotsUpdated = 0;
            try (PreparedStatement psUpdate = con.prepareStatement(updateSql)) {
                psUpdate.setInt(1, eventId);
                slotsUpdated = psUpdate.executeUpdate();
            }

            if (slotsUpdated == 0) {
                System.out.println("Registration rejected: Event is already full.");
                con.rollback();
                return false;
            }

            try (PreparedStatement psInsert = con.prepareStatement(insertSql)) {
                psInsert.setInt(1, userId);
                psInsert.setInt(2, eventId);
                psInsert.executeUpdate();
            }

            try (PreparedStatement psLock = con.prepareStatement(autoLockSql)) {
                psLock.setInt(1, eventId);
                int lockAffected = psLock.executeUpdate();

                if (lockAffected > 0) {
                    String markAbsent = "UPDATE registrations SET attendance_status = 'Absent' WHERE event_id = ? AND attendance_status = 'Pending'";
                    String markAttended = "UPDATE registrations SET attendance_status = 'Attended', time_out = NOW() WHERE event_id = ? AND attendance_status = 'Present'";

                    try (PreparedStatement psAbs = con.prepareStatement(markAbsent); PreparedStatement psAtt = con.prepareStatement(markAttended)) {
                        psAbs.setInt(1, eventId);
                        psAbs.executeUpdate();

                        psAtt.setInt(1, eventId);
                        psAtt.executeUpdate();
                    }
                }
            }

            con.commit(); 
            return true;

        } catch (SQLException e) {
            System.out.println("Registration Transaction Error: " + e.getMessage());
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
        String sql = "SELECT u.first_name, u.last_name, u.email, r.time_in, r.time_out, r.attendance_status "
                + "FROM registrations r "
                + "JOIN users u ON r.user_id = u.user_id "
                + "WHERE r.event_id = ?";

        try (Connection con = conn(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, eventId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String fullName = rs.getString("first_name") + " " + rs.getString("last_name");

                    String tIn = rs.getTimestamp("time_in") != null ? rs.getTimestamp("time_in").toString() : "---";
                    String tOut = rs.getTimestamp("time_out") != null ? rs.getTimestamp("time_out").toString() : "---";

                    list.add(new ModelAttendance(
                            fullName,
                            rs.getString("email"),
                            tIn,
                            tOut,
                            rs.getString("attendance_status")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Attendance Load Error: " + e.getMessage());
        }
        return list;
    }
    
    public List<ModelEvents> getEventsToManage(String role, int currentUserId) {
        List<ModelEvents> list = new ArrayList<>();
        String sql;

        // FIX: Staff bypass the filter entirely to see everything
        if ("Staff".equalsIgnoreCase(role)) {
            sql = "SELECT * FROM events";
        } else {
            sql = "SELECT * FROM events WHERE created_by = ?";
        }

        try (Connection con = conn(); PreparedStatement pst = con.prepareStatement(sql)) {
            if (!"Staff".equalsIgnoreCase(role)) {
                pst.setInt(1, currentUserId);
            }

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
                    list.add(ev);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching management list: " + e.getMessage());
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

        String updateSql = "UPDATE events SET filled_slots = filled_slots - 1 WHERE event_id = ? AND filled_slots > 0";

        String autoUnlockSql = "UPDATE events SET status = 'Open' WHERE event_id = ? AND status = 'LOCK'";


        try (Connection con = conn()) {
            con.setAutoCommit(false);

            int deletedRows = 0;
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, eventId);
                pst.setString(2, email);
                deletedRows = pst.executeUpdate();
            }

            if (deletedRows > 0) {
                try (PreparedStatement psUpdate = con.prepareStatement(updateSql)) {
                    psUpdate.setInt(1, eventId);
                    psUpdate.executeUpdate();
                }

                try (PreparedStatement psUnlock = con.prepareStatement(autoUnlockSql)) {
                    psUnlock.setInt(1, eventId);
                    psUnlock.executeUpdate();
                }

                con.commit();
                return true;
            }

            con.rollback();
            return false;
        } catch (SQLException e) {
            System.out.println("Unregister Error: " + e.getMessage());
            return false;
        }
    }

    public boolean updateAttendanceStatus(String email, int eventId, String status) {
        String query = "UPDATE registrations SET attendance_status = ? WHERE event_id = ? AND user_id = (SELECT user_id FROM users WHERE email = ?)";

        try (Connection con = conn(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, status);
            ps.setInt(2, eventId);
            ps.setString(3, email);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean recordTimeIn(int userId, int eventId) {
        String query = "UPDATE registrations SET attendance_status = 'Present', time_in = NOW() "
                + "WHERE event_id = ? AND user_id = ?";
        try (Connection con = conn(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, eventId);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean recordTimeOut(int userId, int eventId) {
        String query = "UPDATE registrations SET attendance_status = 'Attended', time_out = NOW() WHERE event_id = ? AND user_id = ?";

        try (Connection con = conn(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, eventId);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean recordTimeIn(String email, int eventId) {
        String query = "UPDATE registrations SET attendance_status = 'Present', time_in = NOW() "
                + "WHERE event_id = ? AND user_id = (SELECT user_id FROM users WHERE email = ?)";
        try (Connection con = conn(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, eventId);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean recordTimeOut(String email, int eventId) {
        String query = "UPDATE registrations SET attendance_status = 'Completed', time_out = NOW() "
                + "WHERE event_id = ? AND user_id = (SELECT user_id FROM users WHERE email = ?)";
        try (Connection con = conn(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, eventId);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean forceCloseEvent(int eventId) {
        String lockEventSql = "UPDATE events SET status = 'LOCK' WHERE event_id = ?";

        String markAbsentSql = "UPDATE registrations SET attendance_status = 'Absent' "
                + "WHERE event_id = ? AND attendance_status = 'Pending'";

        String forceTimeOutSql = "UPDATE registrations SET attendance_status = 'Attended', time_out = NOW() "
                + "WHERE event_id = ? AND attendance_status = 'Present'";

        try (Connection con = conn()) {
            con.setAutoCommit(false); // Start transaction

            try (PreparedStatement ps1 = con.prepareStatement(lockEventSql); PreparedStatement ps2 = con.prepareStatement(markAbsentSql); PreparedStatement ps3 = con.prepareStatement(forceTimeOutSql)) {

                ps1.setInt(1, eventId);
                ps1.executeUpdate();

                ps2.setInt(1, eventId);
                ps2.executeUpdate();

                ps3.setInt(1, eventId);
                ps3.executeUpdate();
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Force Close Error: " + e.getMessage());
            return false;
        }
    }
}
