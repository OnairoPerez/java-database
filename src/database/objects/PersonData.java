/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.objects;

/**
 *
 * @author onairo
 */
public class PersonData {
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String ciudad;
    
    public PersonData(String cedula, String nombre, String apellido, String telefono, String direccion, String ciudad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }
    
    public String getId() {
        return this.cedula;
    }
    public String getName() {
        return this.nombre;
    }
    public String getSurname() {
        return this.apellido;
    }
    public String getPhone() {
        return this.telefono;
    }
    public String getAddress() {
        return this.direccion;
    }
    public String getCity() {
        return this.ciudad;
    }
}
