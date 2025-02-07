package com.example.tidsrejseagentur.Database;

import com.example.tidsrejseagentur.Customer;
import com.example.tidsrejseagentur.Timemachine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    public static List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";  // Corrected "customers"

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Customer customer = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
                customers.add(customer);
            }
        }
        return customers;
    }

    // Create a customer
    public static void createCustomer(Customer customer) {
        String sql = "INSERT INTO customer (name, email) VALUES (?, ?)";  // Corrected "costumer" to "customer"

        try (Connection conn = Database.getConnection();
             PreparedStatement ppst = conn.prepareStatement(sql)) {
            ppst.setString(1, customer.getName());
            ppst.setString(2, customer.getEmail());
            int rowsInserted = ppst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new customer has been created.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update a customer
    public static void updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET name = ?, email = ? WHERE id = ?";  // Corrected "costumer" to "customer"

        try (Connection conn = Database.getConnection();
             PreparedStatement ppst = conn.prepareStatement(sql)) {
            ppst.setString(1, customer.getName());
            ppst.setString(2, customer.getEmail());
            ppst.setInt(3, customer.getId());
            int rowsUpdated = ppst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("A customer has been updated.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a customer
    public static void deleteCustomer(int id) {
        String sql = "DELETE FROM customer WHERE id = ?";  // Corrected "costumer" to "customer"

        try (Connection conn = Database.getConnection();
             PreparedStatement ppst = conn.prepareStatement(sql)) {
            ppst.setInt(1, id);
            int rowsDeleted = ppst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A customer has been deleted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTimeMachine(Timemachine timeMachine) {
        String sql = "INSERT INTO timemachine (name, capacity, status) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ppst = conn.prepareStatement(sql)) {
            ppst.setString(1, timeMachine.getName());
            ppst.setInt(2, timeMachine.getCapacity());
            ppst.setString(3, timeMachine.getStatus());
            ppst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTimeMachine(Timemachine timeMachine) {
        String sql = "UPDATE timemachine SET name = ?, capacity = ?, status = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ppst = conn.prepareStatement(sql)) {
            ppst.setString(1, timeMachine.getName());
            ppst.setInt(2, timeMachine.getCapacity());
            ppst.setString(3, timeMachine.getStatus());
            ppst.setInt(4, timeMachine.getId());
            ppst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTimeMachine(int id) {
        String sql = "DELETE FROM timemachine WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ppst = conn.prepareStatement(sql)) {
            ppst.setInt(1, id);
            ppst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Timemachine> getAllTimeMachines() throws SQLException {
        List<Timemachine> timeMachines = new ArrayList<>();
        String query = "SELECT * FROM timemachine";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Timemachine timeMachine = new Timemachine(rs.getInt("id"), rs.getString("name"), rs.getInt("capacity"), rs.getString("status"));
                timeMachines.add(timeMachine);
            }
        }
        return timeMachines;
    }
}
