/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package database;

/**
 *
 * @author onairo
 */
import java.sql.*;

public class DataBase {
    private String user = "SQL_Admin";
    private String password = "zjKgjWK/xRyQcJyu+bU4";
    private String url = "jdbc:mysql://localhost:3306/Tienda_Online";
    
    private Connection conexion;
    
    public DataBase() throws SQLException {
        conexion = DriverManager.getConnection(url, user, password);
    }
    
    public void insertPersona(String nombre, String apellido, String cedula, String telefono, String direccion, String ciudad) throws SQLException {
        String sql = "INSERT INTO Persona (nombre, apellido, cedula, telefono, direccion, ciudad) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, cedula);
            ps.setString(4, telefono);
            ps.setString(5, direccion);
            ps.setString(6, ciudad);
            ps.executeUpdate();
        }
    }

    // Método para actualizar datos de una persona
    public void updatePersona(String cedula, String nuevoTelefono, String nuevaDireccion, String nuevaCiudad) throws SQLException {
        String sql = "UPDATE Persona SET telefono = ?, direccion = ?, ciudad = ? WHERE cedula = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nuevoTelefono);
            ps.setString(2, nuevaDireccion);
            ps.setString(3, nuevaCiudad);
            ps.setString(4, cedula);
            ps.executeUpdate();
        }
    }

    // Método para eliminar una persona
    public void deletePersona(String cedula) throws SQLException {
        String sql = "DELETE FROM Persona WHERE cedula = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cedula);
            ps.executeUpdate();
        }
    }
    
    public void getPersona(String id) {
        String sql = "SELECT * FROM Persona WHERE cedula = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, id);
  
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Cedula: " + rs.getString("cedula"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Apellido: " + rs.getString("apellido"));
                System.out.println("Telefono: " + rs.getString("telefono"));
                System.out.println("Dirección: " + rs.getString("direccion"));
                System.out.println("Ciudad: " + rs.getString("ciudad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        try {
            DataBase db = new DataBase();
            db.insertPersona("Ivan", "Martínez", "1081200000", "3118000000", "Consacá", "Consacá");
            db.getPersona("1081200000");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
