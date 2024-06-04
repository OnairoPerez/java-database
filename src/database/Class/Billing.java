/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.Class;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.objects.Json;
import database.objects.BillingData;

/**
 *
 * @author onairo
 */
public class Billing {
    private Connection connection;
    
    //Builder
    public Billing(Connection connection) {
        this.connection = connection;   
    }
    
    //Table Product and Invoice
    public void addRecord(int quantity, float totalAmount, String invoiceID, String productID) {
        String status = "failed";
        String sql = "INSERT INTO ProductoXFactura (cantidad, monto_total, factura_id_factura, Productos_codigo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setFloat(2, totalAmount);
            ps.setString(3, invoiceID);
            ps.setString(4, productID);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Adding record: " + status);
    }
    public void deleteRecord(String invoiceID, String productID) {
        String status = "failed";
        String sql = "DELETE FROM ProductoXFactura WHERE factura_id_factura = ? AND Productos_codigo = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, invoiceID);
            ps.setString(2, productID);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Deleting record: " + status);
    }
    public void updateRecord(String invoiceID, String productID, Json[] newData){
        Json json = new Json();
        String status = "failed";
        
        String operation = "UPDATE ProductoXFactura SET ";
        String parameter = json.queryString(newData);
        String condition = "WHERE factura_id_factura = ? AND Productos_codigo = ?";
        String sql = operation + parameter + condition;
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, invoiceID);
            ps.setString(2, productID);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        System.out.println("Updating record: " + status);
    }
    public BillingData[] getRecords(String invoiceID) {
        String sql = "SELECT * FROM ProductoXFactura WHERE factura_id_factura = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, invoiceID);
            
            BillingData[] records = {};
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BillingData[] arrayRecords = new BillingData[records.length + 1];
                System.arraycopy(records, 0, arrayRecords, 0, records.length);
                arrayRecords[records.length] = new BillingData(rs.getInt("cantidad"), rs.getFloat("monto_total"), rs.getString("factura_id_factura"), rs.getString("Productos_codigo"));
                
                records = arrayRecords;
            }
            
            return records;
        } catch (SQLException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new BillingData[0];
    }
}
