package com.example.foodie.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Getting a new connection to the database
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Create or connect to the database file
            String dbPath = "jdbc:sqlite:Foodie_Database.db";
            conn = DriverManager.getConnection(dbPath);

            System.out.println("Connected to SQLite database");
        } catch (ClassNotFoundException | SQLException e) {
            e.getMessage();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    // test for database connection
    public static void testConnection() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Foodie_Database.db")){
            if (!conn.isClosed()) {
                System.out.println("\n========== DATABASE CONNECTION TEST ==========");
                System.out.println("Connection successful!");
                System.out.println("=============================================\n");
                closeConnection(conn);
            }
        } catch (SQLException e) {
            System.err.println("Connection test failed: " + e.getMessage());
        }
    }
}

