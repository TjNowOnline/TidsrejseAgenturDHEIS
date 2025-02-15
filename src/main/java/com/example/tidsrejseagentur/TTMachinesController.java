package com.example.tidsrejseagentur;

import com.example.tidsrejseagentur.Database.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TTMachinesController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TableView<Timemachine> timeMachineTable;
    @FXML
    private TableColumn<Timemachine, String> nameColumn;
    @FXML
    private TableColumn<Timemachine, Integer> capacityColumn;
    @FXML
    private TableColumn<Timemachine, String> statusColumn;
    @FXML
    private TextField ttText;
    @FXML
    private TextField capacityText;
    @FXML
    private TextField statusText;

    private ObservableList<Timemachine> timeMachineList = FXCollections.observableArrayList();

    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        capacityColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCapacity()).asObject());
        statusColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatus()));

        loadTimeMachines();
    }

    private void loadTimeMachines() {
        try {
            List<Timemachine> timeMachines = Repository.getAllTimeMachines();  // Fetch time machines
            timeMachineList.clear();
            timeMachineList.addAll(timeMachines);
            timeMachineTable.setItems(timeMachineList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addMachine() {
        String name = ttText.getText();
        String status = statusText.getText();
        int capacity;
        try {
            capacity = Integer.parseInt(capacityText.getText());
        } catch (NumberFormatException e) {
            return; // Handle invalid input for capacity
        }

        if (name.isEmpty() || status.isEmpty()) {
            return;
        }

        Timemachine timeMachine = new Timemachine(0, name, capacity, status);
        Repository.createTimeMachine(timeMachine);
        loadTimeMachines();
    }

    @FXML
    public void deleteMachine() {
        Timemachine selectedMachine = timeMachineTable.getSelectionModel().getSelectedItem();
        if (selectedMachine == null) {
            return;
        }

        Repository.deleteTimeMachine(selectedMachine.getId());
        loadTimeMachines();
    }

    @FXML
    public void editMachine() {
        Timemachine selectedMachine = timeMachineTable.getSelectionModel().getSelectedItem();
        if (selectedMachine == null) {
            return;
        }

        String newName = ttText.getText();
        String newStatus = statusText.getText();
        int newCapacity;
        try {
            newCapacity = Integer.parseInt(capacityText.getText());
        } catch (NumberFormatException e) {
            return; // Handle invalid input for capacity
        }

        if (newName.isEmpty() || newStatus.isEmpty()) {
            return;
        }

        selectedMachine.setName(newName);
        selectedMachine.setCapacity(newCapacity);
        selectedMachine.setStatus(newStatus);

        Repository.updateTimeMachine(selectedMachine);
        loadTimeMachines();
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
