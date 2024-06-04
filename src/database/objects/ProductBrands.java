/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.objects;

/**
 *
 * @author onairo
 */
public class ProductBrands {
    private int brandID;
    private String name;
    
    //builder
    public ProductBrands() {}
    public ProductBrands(int id, String name) {
        this.brandID = id;
        this.name = name;
    }
    
    //Getter
    public int getBrandID() {
        return this.brandID;
    }
    public String getName() {
        return this.name;
    }
}
