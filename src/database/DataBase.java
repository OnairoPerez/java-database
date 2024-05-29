/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.Class.*;
import database.objects.*;

/**
 *
 * @author onairo
 */

public class DataBase {
    private final String user = "SQL_Admin";
    private final String password = "zjKgjWK/xRyQcJyu+bU4";
    private final String url = "jdbc:mysql://localhost:3306/Tienda_Online";
    
    protected final Connection conexion;
    
    public DataBase() throws SQLException {
        conexion = DriverManager.getConnection(url, user, password);
    }
    
    public Connection getConnection() {
        return this.conexion;
    }
    
    public static void main(String[] args) {
        try {
            DataBase db = new DataBase();
            Person person = new Person(db.getConnection());
            
            //Agregar información de usuario
            person.insertInfo("Juan", "García", "123456789", "1234567890", "Calle 123", "Bogotá");
            person.insertInfo("María", "López", "987654321", "0987654321", "Avenida Principal", "Medellín");
            
            //Obtener datos
            PersonData data = person.getInfo("123456789");
            System.out.println(data.getPhone());
            
            //Actualizar información
            Json[] jsonArray = {
                new Json("nombre", "Juan"),
                new Json("apellido", "García"),
                new Json("cedula", "123456789"),
                new Json("telefono", "7874567890"),
                new Json("direccion", "Calle 124"),
                new Json("ciudad", "Bogotá"),
            };
            person.updateInfo("123456789", jsonArray);

            //Eliminar datos
            person.deleteInfo("123456789");
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
