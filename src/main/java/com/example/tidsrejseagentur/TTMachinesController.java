package com.example.tidsrejseagentur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TTMachinesController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void addMachine() {

    }
    @FXML
    public void deleteMachine() {

    }
    @FXML
    public void editMachine() {

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
