/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.Class;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.objects.Json;
import database.generate.UUIDCode;
import database.objects.InvoiceData;
import database.objects.PaymentMethod;

/**
 *
 * @author onairo
 */
public class Invoice {
    private Connection connection;
    private String uuid;
    
    public Invoice(Connection connection) {
        this.connection = connection;
    }
    
    public void insertInfo(String personID, String date, String time, float subtotal, float spareChange, float payment, int method) {
        UUIDCode UUIDCode = new UUIDCode();
        this.uuid = UUIDCode.generate();
        
        String status = "failed";
        String sql = "INSERT INTO Factura (id_factura,  cedula, fecha, hora, subtotal, cambio, pago, metodo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, this.uuid);
            ps.setString(2, personID);
            ps.setString(3, date);
            ps.setString(4, time);
            ps.setFloat(5, subtotal);
            ps.setFloat(6, spareChange);
            ps.setFloat(7, payment);
            ps.setInt(8, method);
            ps.executeUpdate();
                    
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Invoice registration: " + status);
    }
    
    public void updateInfo(String invoiceID, Json[] newData) {
        Json json = new Json();
        String status = "failed";
        
        String operation = "UPDATE Factura SET ";
        String parameter = json.queryString(newData);
        String condition = "WHERE id_factura = ?";
        String sql = operation + parameter + condition;
        
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, invoiceID);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Invoice update: " + status);
    }
    
    public void deleteInfo(String invoiceID) {
        String status = "failed";
        String sql = "DELETE FROM Factura WHERE id_factura = ?";
        
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, invoiceID);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Invoice deletion:" + status);
    }
    
    public InvoiceData getInfo(String invoiceID) {
        String sql = "SELECT * FROM Factura WHERE id_factura = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, invoiceID);
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return new InvoiceData(
                    rs.getString("id_factura"),
                    rs.getString("cedula"),
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getFloat("subtotal"),
                    rs.getFloat("cambio"),
                    rs.getFloat("pago"),
                    rs.getInt("metodo")
                );
            } else {
                System.out.println("No se ha encontrado resultados");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new InvoiceData();
    }
    
    public void newMethod(String payment) {
        String status = "failed";
        String sql = "INSERT INTO FormaDePago (metodo) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, payment);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Add new payment method: " + status);
    }
    
    public void updateMethod(int id, String value) {
        String status = "failed";
        String sql = "UPDATE FormaDePago SET metodo = ? WHERE id_FormaDePago = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, value);
            ps.setInt(2, id);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Payment method update: " + status);
    }
    
    public void deleteMethod(int id) {
        String status = "failed";
        String sql = "DELETE FROM FormaDePago WHERE id_FormaDePago = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Payment method elimination: " + status);
    }
    
    public PaymentMethod[] getAllMethods () {
        String sql = "SELECT * FROM FormaDePago";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            PaymentMethod[] methods = {};
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PaymentMethod method = new PaymentMethod(rs.getString("metodo"), rs.getInt("id_FormaDePago"));
                
                PaymentMethod[] arrayMethods = new PaymentMethod[methods.length + 1];
                System.arraycopy(methods, 0, arrayMethods, 0, methods.length);
                arrayMethods[methods.length] = method;
                
                methods = arrayMethods;
            }
            return methods;
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new PaymentMethod[0];
    }
    
    //Getter
    public String uuidCode() {
        return this.uuid;
    }
}