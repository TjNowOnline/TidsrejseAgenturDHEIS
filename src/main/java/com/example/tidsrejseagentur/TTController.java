package com.example.tidsrejseagentur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class TTController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void cabClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TimeTravelApplication.class.getResource("customeradmin.fxml"));
        Parent root = fxmlLoader.load();

        CustomerAdminController customerAdminController = fxmlLoader.getController();
        customerAdminController.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void ttmbClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TimeTravelApplication.class.getResource("timetravelmachines.fxml"));
        Parent root = fxmlLoader.load();

        TTMachinesController ttMachinesController = fxmlLoader.getController();
        ttMachinesController.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void tpbClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TimeTravelApplication.class.getResource("timeperiods.fxml"));
        Parent root = fxmlLoader.load();

        TimePeriodsController timePeriodsController = fxmlLoader.getController();
        timePeriodsController.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void bbClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TimeTravelApplication.class.getResource("booking.fxml"));
        Parent root = fxmlLoader.load();

        BookingController bookingController = fxmlLoader.getController();
        bookingController.setStage(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}