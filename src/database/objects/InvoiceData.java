/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.objects;

/**
 *
 * @author onairo
 */
public class InvoiceData {
    private String invoiceID;
    private String personID;
    private String date;
    private String time;
    private float subtotal;
    private float spareChange;
    private float payment;
    private int method;
    
    public InvoiceData() {}
    public InvoiceData(String invoiceID, String personID, String date, String time, float subtotal, float spareChange, float payment, int method) {
        this.invoiceID = invoiceID;
        this.personID = personID;
        this.date = date;
        this.time = time;
        this.subtotal = subtotal;
        this.spareChange = spareChange;
        this.payment = payment;
        this.method = method;
    }
    
    //Getters
    public String getInvoiceID() {
        return this.invoiceID;
    }
    public String getPersonID() {
        return this.personID;
    }
    public String getDate() {
        return this.date;
    }
    public String getTime() {
        return this.time;
    }
    public float getSubtotal() {
        return this.subtotal;
    }
    public float getSpareChange() {
        return this.spareChange;
    }
    public float getPayment() {
        return this.payment;
    }
    public float getMethod() {
        return this.method;
    }
}
