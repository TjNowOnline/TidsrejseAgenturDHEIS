package com.example.tidsrejseagentur;

import com.example.tidsrejseagentur.Database.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class CustomerAdminController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        loadCustomers();
    }
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, String> emailColumn;
    @FXML
    private TextField customerName;
    @FXML
    private TextField customerEmail;
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    private void loadCustomers() {
        try {
            List<Customer> customers = Repository.getAllCustomers();
            customerList.clear();
            customerList.addAll(customers);
            customerTable.setItems(customerList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addCustomer() {
        String name = customerName.getText();
        String email = customerEmail.getText();
        if (name.isEmpty() || email.isEmpty()) {
            return;
        }
        Customer customer = new Customer(0, name, email);
        Repository.cereateCostumer(customer);
        loadCustomers();
    }

    @FXML
    public void deleteCustomer() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            return;
        }
        Repository.deleteCostumer(selectedCustomer.getId());
        loadCustomers();
    }

    @FXML
    public void editCustomer() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            return;
        }
        String newName = customerName.getText();
        String newEmail = customerEmail.getText();
        if (newName.isEmpty() || newEmail.isEmpty()) {
            return;
        }
        Repository.updateCostumer(selectedCustomer);
        loadCustomers();
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
