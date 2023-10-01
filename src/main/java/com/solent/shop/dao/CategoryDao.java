package com.solent.shop.dao;

import com.solent.shop.models.Category;
import com.solent.shop.models.Product;
import com.solent.shop.utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDao {

    protected Connection conn = null;

    protected PreparedStatement ps = null;

    protected ResultSet rs = null;

    protected String response = "";

    protected Category category;

    public int count = 0;

    public CategoryDao() {
        this.getAllCategories();
    }

    public String addCategory(String name) {
        if (name == null) {
            return "Please fill all fields";
        }
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "INSERT INTO category (cat_name)VALUES(?)";
            ps = this.conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.execute();
            response = "Success: " + name + " Added successfully.";
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
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

    public int updateCategory(int id, String name) {
        int result = 0;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "UPDATE category SET cat_name=? WHERE cat_id=?";
            ps = this.conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, id);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public String DeleteCategory(int cat_id) {
        String result = null;
        try {
            this.conn = new DbConnection().getConnection();
            String sql = "Delete from category WHERE cat_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cat_id);
            ps.execute();
            result = "Category deleted successfully.";
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

    public List<Category> getAllCategories() {
        List categorylist = null;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM category ORDER BY cat_name";
            ps = this.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            categorylist = new ArrayList();
            while (rs.next()) {
                Category cat = new Category();
                cat.setCat_id(rs.getInt("cat_id"));
                cat.setCat_name(rs.getString("cat_name"));
                categorylist.add(cat);
            }
            this.count = categorylist.size();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return categorylist;
    }

    public List<Category> getCategoryByID(int id) {
        List categorytlist = null;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM category WHERE cat_id=?";
            ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            categorytlist = new ArrayList();
            while (rs.next()) {
                Category cat = new Category();
                cat.setCat_id(rs.getInt("cat_id"));
                cat.setCat_name(rs.getString("cat_name"));
                categorytlist.add(cat);
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
        return categorytlist;
    }
}
