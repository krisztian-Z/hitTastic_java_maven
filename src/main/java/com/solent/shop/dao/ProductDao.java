package com.solent.shop.dao;

import com.solent.shop.models.Category;
import com.solent.shop.models.Product;
import com.solent.shop.utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDao {

    protected Connection conn = null;

    protected PreparedStatement ps = null;

    protected ResultSet rs = null;

    protected String response = "";

    protected Product product;

    public int count = 0;

    public ProductDao() {
    }

    public String AddProduct(int category_id, int quantity, String name, String product_description, String image, double price) {
        if (category_id == 0 || quantity == 0 || name == null || price == 0) {
            return "Please fill all fields";
        }
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "INSERT INTO products (category_id, name, product_details, image, quantity, unit_price)VALUES(?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, category_id);
            ps.setString(2, name);
            ps.setString(3, product_description);
            if (image != null || !" ".equals(image)) {
                ps.setString(4, image);
            } else {
                ps.setString(4, "No Image");
            }
            ps.setInt(5, quantity);
            ps.setDouble(6, price);
            ps.execute();
            response = "Success: " + name + " Added successfully.";
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            System.out.println(ex.getMessage());
            return ex.getMessage();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return response;
    }

    public int UpdateProduct(int product_id, int category_id, int quantity, String name, String image, String product_description, double price) {
        int result = 0;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "Update products set category_id=? , name=?, product_details=?, image=?, quantity=?, unit_price=? WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, category_id);
            ps.setString(2, name);
            ps.setString(3, product_description);
            ps.setString(4, image);
            ps.setInt(5, quantity);
            ps.setDouble(6, price);
            ps.setInt(7, product_id);
            result = ps.executeUpdate();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getErrorCode());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public int UpdateProductWithoutImage(int product_id, int category_id, int quantity, String name, String product_description, double price) {
        int result = 0;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "Update products set category_id=? , name=?, product_details=?,  quantity=?, unit_price=? WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, category_id);
            ps.setString(2, name);
            ps.setString(3, product_description);
            ps.setInt(4, quantity);
            ps.setDouble(5, price);
            ps.setInt(6, product_id);
            result = ps.executeUpdate();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getErrorCode());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public int DeleteProduct(int product_id) {
        int result = 0;
        try {
            this.conn = new DbConnection().getConnection();
            String sql = "Delete from products WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, product_id);
            ps.execute();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getErrorCode());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public List<Product> getAllProducts() {
        List productlist = null;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM products";
            ps = this.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            productlist = new ArrayList();
            while (rs.next()) {
                Product p = new Product();
                CategoryDao cat = new CategoryDao();
                p.setProduct_id(rs.getInt("id"));
                p.setCategory_id(rs.getInt("category_id"));
                p.setName(rs.getString("name"));
                p.setProduct_description(rs.getString("product_details"));
                p.setImage(rs.getString("image"));
                p.setQuantity(rs.getInt("quantity"));
                p.setUnit_price(rs.getDouble("unit_price"));
                p.setCategory(cat.getCategoryByID(rs.getInt("category_id")));
                productlist.add(p);
            }
            this.count = productlist.size();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return productlist;
    }

    public List<Product> getProductByID(int id) {
        List productlist = null;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM products WHERE id=?";
            ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            productlist = new ArrayList();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getInt("id"));
                p.setCategory_id(rs.getInt("category_id"));
                p.setName(rs.getString("name"));
                p.setProduct_description(rs.getString("product_details"));
                p.setImage(rs.getString("image"));
                p.setQuantity(rs.getInt("quantity"));
                p.setUnit_price(rs.getDouble("unit_price"));
                productlist.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return productlist;
    }

    public List<Product> getProductByCategoryID(int id) {
        List productlist = null;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM products WHERE category_id=?";
            ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            productlist = new ArrayList();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getInt("id"));
                p.setCategory_id(rs.getInt("category_id"));
                p.setName(rs.getString("name"));
                p.setProduct_description(rs.getString("product_details"));
                p.setImage(rs.getString("image"));
                p.setQuantity(rs.getInt("quantity"));
                p.setUnit_price(rs.getDouble("unit_price"));
                productlist.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return productlist;
    }

    public List<Product> getProductByName(String name) {
        List productlist = null;
        conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM products WHERE name LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            productlist = new ArrayList();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getInt("id"));
                p.setCategory_id(rs.getInt("category_id"));
                p.setName(rs.getString("name"));
                p.setProduct_description(rs.getString("product_details"));
                p.setImage(rs.getString("image"));
                p.setQuantity(rs.getInt("quantity"));
                p.setUnit_price(rs.getDouble("unit_price"));
                productlist.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return productlist;
    }

    public List<Product> searchProduct(String query) {
        List productlist = null;
        conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM products WHERE name  LIKE ? OR product_details LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            rs = ps.executeQuery();
            productlist = new ArrayList();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getInt("id"));
                p.setCategory_id(rs.getInt("category_id"));
                p.setName(rs.getString("name"));
                p.setProduct_description(rs.getString("product_details"));
                p.setImage(rs.getString("image"));
                p.setQuantity(rs.getInt("quantity"));
                p.setUnit_price(rs.getDouble("unit_price"));
                productlist.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return productlist;
    }

    public int updateProductQuantity(int productID, int productQuantity) {
        List plist = null;
        int result = 0;
        conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT quantity FROM products WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            plist = new ArrayList();
            while (rs.next()) {
                int qty = rs.getInt("quantity");
                int remainingQuantity = qty - productQuantity;
                String update = "Update products set quantity=? WHERE id=?";
                ps = conn.prepareStatement(update);
                ps.setInt(1, remainingQuantity);
                ps.setInt(2, productID);
                result = ps.executeUpdate();
                return result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
