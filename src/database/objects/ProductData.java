/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.objects;

/**
 *
 * @author onairo
 */
public class ProductData {
    private String code;
    private String name;
    private float purchaseValue; //valor compra
    private float saleValue; //valor venta
    private int stock;
    private int categoryID;
    private int brandID;
    
    //Builder
    public ProductData() {}
    public ProductData(String code, String name, float purchaseValue, float saleValue, int stock, int categoryID, int brandID) {
        this.code = code;
        this.name = name;
        this.purchaseValue = purchaseValue;
        this.saleValue = saleValue;
        this.stock = stock;
        this.categoryID = categoryID;
        this.brandID = brandID;
    }
    
    //Getters
    public String getCode() {
    return this.code;
    }
    public String getName() {
        return this.name;
    }
    public float getPurchaseValue() {
        return this.purchaseValue;
    }
    public float getSaleValue() {
        return this.saleValue;
    }
    public int getStock() {
        return this.stock;
    }
    public int getCategoryID() {
        return this.categoryID;
    }
    public int getBrandID() {
        return this.brandID;
    }
}
