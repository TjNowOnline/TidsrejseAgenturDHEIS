package com.example.tidsrejseagentur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class BookingController {

    @FXML
    private ListView<TimePeriod> timePeriodLV;
    @FXML
    private ListView<Timemachine> machineLV;
    @FXML
    private ListView<Guide> guideLV;

    @FXML
    private TextField timePeriodTF;
    @FXML
    private TextField machineTF;
    @FXML
    private TextField guideTF;

    @FXML
    private Label bookingDetailsLabel;

    private Stage stage;
    private Connection connection;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Initialize method to load the data from the database
    public void initialize() {
        // Initialize DB connection
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tidsrejseagentur.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Populate ListViews with hardcoded data for TimePeriod and Guide, and data from DB for Timemachines
        populateListViews();

        // Add listeners to update TextFields when ListView items are selected
        timePeriodLV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                timePeriodTF.setText(newValue.getName());
            }
        });

        machineLV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                machineTF.setText(newValue.getName());
            }
        });

        guideLV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                guideTF.setText(newValue.getName());
            }
        });
    }

    // Populate the ListViews with data
    private void populateListViews() {
        timePeriodLV.getItems().clear();
        machineLV.getItems().clear();
        guideLV.getItems().clear();
        // Hardcode TimePeriod data
        ArrayList<TimePeriod> timePeriods = new ArrayList<>();
        timePeriods.add(new TimePeriod(1, "Dinosaur era"));
        timePeriods.add(new TimePeriod(2, "Medieval era"));
        timePeriods.add(new TimePeriod(3, "Present"));
        timePeriods.add(new TimePeriod(4, "Future"));
        timePeriodLV.getItems().setAll(timePeriods);

        // Hardcode Guide data
        ArrayList<Guide> guides = new ArrayList<>();
        guides.add(new Guide(1, "Tino", "History"));
        guides.add(new Guide(2, "Theis", "Science"));
        guides.add(new Guide(3, "Markus", "Art"));
        guideLV.getItems().setAll(guides);

        // Load Timemachines from database
        try {
            ArrayList<Timemachine> machines = loadTimemachines();
            machineLV.getItems().setAll(machines);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch timemachines from the database
    private ArrayList<Timemachine> loadTimemachines() throws SQLException {
        ArrayList<Timemachine> machines = new ArrayList<>();
        String query = "SELECT * FROM Timemachine";  // Replace with your actual table name
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int capacity = rs.getInt("capacity");
            String status = rs.getString("status");
            machines.add(new Timemachine(id, name, capacity, status));

            System.out.println("Timemachine " + id + ": " + name + ", Capacity: " + capacity + ", Status: " + status);
        }
        return machines;
    }

    @FXML
    public void createBooking() {
        // Get the selected items
        TimePeriod selectedTimePeriod = timePeriodLV.getSelectionModel().getSelectedItem();
        Timemachine selectedMachine = machineLV.getSelectionModel().getSelectedItem();
        Guide selectedGuide = guideLV.getSelectionModel().getSelectedItem();

        if (selectedTimePeriod != null && selectedMachine != null && selectedGuide != null) {
            // Create the booking object
            Customer customer = new Customer(1, "John Doe", "johndoe@example.com"); // Replace with actual customer object
            Booking newBooking = new Booking(
                    generateBookingId(),
                    selectedMachine,
                    customer,
                    selectedTimePeriod,
                    selectedGuide
            );

            // Here you would save the new booking to the database or print it
            bookingDetailsLabel.setText(newBooking.toString()); // Print the booking for verification

        } else {
            // Show an alert if something is missing
            System.out.println("Please select all fields");
        }
    }

    private int generateBookingId() {
        // Generate a unique booking ID (you might want to use auto-increment in the database)
        return (int) (Math.random() * 10000);
    }

    @FXML
    public void backToMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TimeTravelApplication.class.getResource("timetravel.fxml"));
        Parent root = fxmlLoader.load();

        TTController ttController = fxmlLoader.getController();
        ttController.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
