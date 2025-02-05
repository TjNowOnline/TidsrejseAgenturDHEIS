package com.example.tidsrejseagentur.Database;

import com.example.tidsrejseagentur.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    public static List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";

        try (Connection conn = Database.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Customer customer = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
                customers.add(customer);
            }
        }
        return customers;
    }

    // Create i CRUD. ny kunde kan oprettes
 public static void cereateCostumer (Customer costumer){
    String sql = "INSERT INTO costumer (name, email) VALUES (?,?)";

    try (
            Connection conn = Database.getConnection();
            PreparedStatement ppst = conn.prepareStatement(sql);
            ) {
            ppst.setString(1, costumer.getName());
            ppst.setString(2, costumer.getEmail());
            int rowsINSERTED = ppst.executeUpdate();
            if (rowsINSERTED > 0) {
            System.out.println("A new costumer has been created");
            }
        }catch (SQLException e){
        e.printStackTrace();
    }
 } // update costumer
 public static void updateCostumer(Customer costumer){
     String sql = "UPDATE costumer SET name = ?, email = ? WHERE id = ?";

     try (
          Connection conn = Database.getConnection();
          PreparedStatement ppst = conn.prepareStatement(sql);
          ) {
         ppst.setString(1, costumer.getName());
         ppst.setString(2, costumer.getEmail());
         ppst.setInt(3, costumer.getId());
        int rowsUPDATED = ppst.executeUpdate();
        if (rowsUPDATED > 0) {
            System.out.println("A new costumer has been updated");
        }

     } catch (SQLException e){
         e.printStackTrace();
     }
 }
    public static void deleteCostumer(int id){
     String sql = "DELETE FROM costumer WHERE id = ?";

        try (
        Connection conn = Database.getConnection();
        PreparedStatement ppst = conn.prepareStatement(sql);
        ){


        }


}



}
