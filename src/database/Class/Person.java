/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.Class;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.objects.PersonData;
import database.objects.Json;

/**
 *
 * @author onairo
 */
public class Person {    
    private Connection connection;
    
    public Person(Connection Connection) {
        this.connection = Connection;
    }

    public PersonData getInfo(String cedula) {
        String sql = "SELECT * FROM Persona WHERE cedula = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cedula);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new PersonData(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("ciudad")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void insertInfo(String nombre, String apellido, String cedula, String telefono, String direccion, String ciudad) {
        String status = "failed";
        String sql = "INSERT INTO Persona (nombre, apellido, cedula, telefono, direccion, ciudad) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, cedula);
            ps.setString(4, telefono);
            ps.setString(5, direccion);
            ps.setString(6, ciudad);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Insert information: " + status);
    }
    
    public void updateInfo(String cedula, Json[] newData) {
        Json json = new Json();
        
        String operation = "UPDATE Persona SET ";
        String parameter = json.queryString(newData);
        String condition = "WHERE cedula = ?";
        String sql = operation + parameter + condition;
        
        String status = "failed";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cedula);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Updating information: " + status);
    }
    
    public void deleteInfo(String cedula) {
        String status = "failed";
        String sql = "DELETE FROM Persona WHERE cedula = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cedula);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Deleting information: " + status);
    }
}
