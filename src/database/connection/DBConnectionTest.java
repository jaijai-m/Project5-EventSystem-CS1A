/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.connection;

/**
 *
 * @author jaijaimnglndn07
 */
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionTest {
    public static void main(String[] args) {
        System.out.println("Checking database connection...");
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("SUCCESS: Connected to db_event_management!");
            }
        } catch (SQLException e) {
            System.err.println("ERROR: Could not connect to the database.");
            System.err.println("Message: " + e.getMessage());
            System.err.println("Check if XAMPP MySQL is turned ON.");
        }
    }
}
