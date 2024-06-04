/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.objects;

/**
 *
 * @author onairo
 */
public class BillingData {
    private int quantity;
    private float totalAmount;
    private String invoiceID;
    private String productID;
    
    //Builder
    public BillingData() {}
    public BillingData(int quantity, float totalAmount, String invoiceID, String productID) {
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.invoiceID = invoiceID;
        this.productID = productID;
    }
    
    //Getters
    public int getQuantity() {
        return this.quantity;
    }
    public float getTotalAmount() {
        return this.totalAmount;
    }
    public String getInvoiceid() {
        return this.invoiceID;
    }
    public String getProductid() {
        return this.productID;
    }
}
