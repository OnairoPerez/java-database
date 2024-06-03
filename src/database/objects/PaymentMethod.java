/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.objects;

/**
 *
 * @author onairo
 */
public class PaymentMethod {
    private int id;
    private String method;
    
    public PaymentMethod() {}
    public PaymentMethod(String method, int id) {
        this.method = method;
        this.id = id;
    }
    
    //Getters
    public String getMethod() {
        return this.method;
    }
    public int getID() {
        return this.id;
    }
    
    //Setters
    public void setMethod(String method) {
        this.method = method;
    }
    public void setID(int id){
        this.id = id;
    }
}
