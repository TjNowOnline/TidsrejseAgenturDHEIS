package com.example.tidsrejseagentur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class TimePeriodsController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private ComboBox<String> comboboxTP;
    @FXML
    private Label timePeriodLabel;

    @FXML
    public void initialize() {
        comboboxTP.getItems().addAll("Dinosaur era", "Medieval times", "Present", "Future");
        comboboxTP.setOnAction(event -> updateTimePeriodLabel());
    }

    private void updateTimePeriodLabel() {
        String selectedTimePeriod = comboboxTP.getValue();
        if (selectedTimePeriod != null) {
            switch (selectedTimePeriod) {
                case "Dinosaur era":
                    timePeriodLabel.setText("The Dinosaur Era, also known as the Mesozoic Era (about 252 to 66 million years ago), was dominated by massive reptiles like the T-Rex and Triceratops. Lush forests and warm climates covered the Earth, supporting a vast array of prehistoric life. This era was divided into the Triassic, Jurassic, and Cretaceous periods. It ended with a mass extinction, likely caused by an asteroid impact.");
                    break;
                case "Medieval times":
                    timePeriodLabel.setText("The Medieval Era (5th to 15th century) was marked by feudalism, knights, castles, and legendary figures like King Arthur. It saw the rise of powerful kingdoms, religious influence from the Catholic Church, and significant conflicts like the Crusades. Art and architecture flourished, leading to the construction of grand cathedrals. The Black Death in the 14th century reshaped European society.");
                    break;
                case "Present":
                    timePeriodLabel.setText("The present era is defined by rapid technological advancements, globalization, and interconnected societies. The internet, artificial intelligence, and space exploration continue to transform daily life. Environmental concerns and climate change remain pressing global issues. Humanity stands at a crossroads between innovation and sustainability.");
                    break;
                case "Future":
                    timePeriodLabel.setText("The future holds endless possibilities, from space colonization to AI-driven societies. Advancements in medicine may extend human lifespan, while renewable energy could revolutionize industries. Challenges like climate change and ethical AI development will shape civilization’s progress. Science fiction dreams, such as flying cars and interstellar travel, may one day become reality.");
                    break;
                default:
                    timePeriodLabel.setText("Unknown time period");
            }
        }
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
