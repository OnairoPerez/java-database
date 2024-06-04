/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.objects;

/**
 *
 * @author onairo
 */
public class ProductCategory {
    private int categoryID;
    private String name;
    
    //Builder
    public ProductCategory() {}
    public ProductCategory(int id, String name) {
        this.categoryID = id;
        this.name = name;
    }
    
    //Getters
    public int getCategoryID() {
        return this.categoryID;
    }
    public String getName() {
        return this.name;
    }
}
