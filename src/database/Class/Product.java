/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.Class;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.objects.Json;
import database.objects.ProductData;
import database.objects.ProductCategory;
import database.objects.ProductBrands;

/**
 *
 * @author onairo
 */
public class Product {
    private Connection connection;
    
    //Builder
    public Product(Connection connection) {
        this.connection = connection;
    }
    
    //Table Product
    public void insertInfo(String code, String name, float purchaseValue, float saleValue, int stock, int categoryID, int brandID) {
        String status = "failed";
        String sql = "INSERT INTO Productos (codigo, nombre, valor_compra, valor_venta, existencias, Categorias_id_categoria, Marca_id_Marca) Values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.setString(2, name);
            ps.setFloat(3, purchaseValue);
            ps.setFloat(4, saleValue);
            ps.setInt(5, stock);
            ps.setInt(6, categoryID);
            ps.setInt(7, brandID);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Inserting product information: " + status);
    }
    public ProductData getInfo(String code) {
        String sql = "SELECT * FROM Productos WHERE codigo = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, code);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new ProductData(
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getFloat("valor_compra"),
                    rs.getFloat("valor_venta"),
                    rs.getInt("existencias"),
                    rs.getInt("Categorias_id_categoria"),
                    rs.getInt("Marca_id_Marca")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ProductData();
    }
    public void updateInfo(String code, Json[] newData) {
        Json json = new Json();
        
        String operation = "UPDATE Productos SET ";
        String parameter = json.queryString(newData);
        String condition = "WHERE codigo = ?";
        String sql = operation + parameter + condition;
        
        String status = "failed";
        
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Product information update: " + status);
    }
    public void deleteInfo(String code) {
        String status = "failed";
        String sql = "DELETE FROM Productos WHERE codigo = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Table Category
    public void newCategory(String name) {
        String status = "failed";
        String sql = "INSERT INTO Categorias (nombre) VALUES (?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Adding category: " + status);
    }
    public void deleteCategory(int code) {
        String status = "failed";
        String sql = "DELETE FROM Categorias WHERE id_categoria = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, code);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Category deletion: " + status);
    }
    public void updateCategory(int code, Json[] newData) {
        Json json = new Json();
        String status = "failed";
        
        String operation = "UPDATE Categorias SET ";
        String parameter = json.queryString(newData);
        String condition = "WHERE id_categoria = ?";
        String sql = operation + parameter + condition;
        
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, code);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ProductCategory[] getAllCategory() {
        String sql = "SELECT * FROM Categorias";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ProductCategory[] products = {};
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ProductCategory[] arrayProducts = new ProductCategory[products.length +1];
                System.arraycopy(products, 0, arrayProducts, 0, products.length);
                arrayProducts[products.length] = new ProductCategory(rs.getInt("id_categoria"), rs.getString("nombre"));
                
                products = arrayProducts;
            }
            
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ProductCategory[0];
    }
    
    //Table Brands
    public void newBrand(String name) {
        String status = "failed";
        String sql = "INSERT INTO Marca (nombre) VALUES (?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, name);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Adding new brand: " + status);
    }
    public void deleteBrand(int code) {
        String status = "failed";
        String sql = "DELETE FROM Marca WHERE id_Marca = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, code);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Removing brand: " + status);
    }
    public void updateBrand(int code, Json[] newData) {
        Json json = new Json();
        String status = "failed";
        
        String operation = "UPDATE Marca SET ";
        String parameter = json.queryString(newData);
        String condition = "WHERE id_Marca = ?";
        String sql = operation + parameter + condition;
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, code);
            ps.executeUpdate();
            
            status = "done";
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Updating brand: " + status);
    }
    public ProductBrands[] getAllBrand() {
        String sql = "SELECT * FROM Marca";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ProductBrands[] brands = {};
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                ProductBrands[] arrayBrands = new ProductBrands[brands.length +1];
                System.arraycopy(brands, 0, arrayBrands, 0, brands.length);
                arrayBrands[brands.length] = new ProductBrands(rs.getInt("id_Marca"), rs.getString("nombre"));
                
                brands = arrayBrands;
            }
            
            return brands;
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ProductBrands[0];
    }
}
