package com.example.tidsrejseagentur.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static String URL = "jdbc:mysql://localhost:3306/";

    private static String user = "Root ";

    private static String password = "root";

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
        try{
        return DriverManager.getConnection(URL, user, password);
        }catch (SQLException e ){
            e.printStackTrace();
            return null;
        }
}
}
