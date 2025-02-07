package com.example.tidsrejseagentur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TimeTravelApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TimeTravelApplication.class.getResource("timetravel.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        TTController ttController = fxmlLoader.getController();
        ttController.setStage(stage);


        stage.setTitle("Tidsrejse");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        String url = "jdbc:sqlite:tidsrejseagentur.db";

        // SQL to create the 'customer' table
        String createCustomerTableSQL = "CREATE TABLE IF NOT EXISTS customer ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "email TEXT NOT NULL);";

        // SQL to create the 'timemachine' table
        String createTimemachineTableSQL = "CREATE TABLE IF NOT EXISTS timemachine ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "capacity INTEGER NOT NULL, "
                + "status TEXT NOT NULL);";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Create 'customer' table
            stmt.execute(createCustomerTableSQL);
            System.out.println("Table 'customer' created successfully or already exists.");

            // Create 'timemachine' table
            stmt.execute(createTimemachineTableSQL);
            System.out.println("Table 'timemachine' created successfully or already exists.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        launch();
    }
}
