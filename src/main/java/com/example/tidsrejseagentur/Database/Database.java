package com.example.tidsrejseagentur.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:sqlite:tidsrejseagentur.db"; // SQLite database file

    private static Database instance;

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL); // No username and password needed
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
