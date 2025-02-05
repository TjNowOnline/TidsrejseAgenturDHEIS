package com.example.tidsrejseagentur.Database;

import com.example.tidsrejseagentur.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Repository {
// Create i CRUD. ny kunde kan oprettes
 public void cereateCostumer (Customer costumer){
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
 public void updateCostumer (Customer costumer){
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
    public void deleteCostumer (int id ){
     String sql = "DELETE FROM costumer WHERE id = ?";

        try (
        Connection conn = Database.getConnection();
        PreparedStatement ppst = conn.prepareStatement(sql);
        ){


        }


}



}
