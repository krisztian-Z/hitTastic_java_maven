package com.solent.shop.dao;

import com.solent.shop.models.Category;
import com.solent.shop.models.Order;
import com.solent.shop.models.OrdersDetail;
import com.solent.shop.utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDao {

    protected Connection conn = null;

    protected PreparedStatement ps = null;

    protected ResultSet rs = null;

    protected String response = "";

    protected Category category;

    public int count = 0;

    public int pending_count = 0;

    public OrderDao() {
        this.getOrders();
    }

    public int saveOrder(String order_id, double order_amount, String email, String order_status) {
        int id = -1;
        try {
            this.conn = new DbConnection().getConnection();
            String sql = "INSERT INTO orders (order_id, order_amount, email,  order_status)VALUES(?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, order_id);
            ps.setDouble(2, order_amount);
            ps.setString(3, email);
            ps.setString(4, order_status);
            int executeQuery = ps.executeUpdate();
            id = executeQuery;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    public String SaveOrderDetails(String order_id, int product_id, String user_name, String email, String shipping_address, String billing_address, String product_name, String product_image, double product_price, int quantity) {
        try {
            this.conn = new DbConnection().getConnection();
            String sql = "INSERT INTO orders_detail (order_id, product_id, username, email, shipping_address,billing_address,  product_name, product_image, product_price, quantity)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, order_id);
            ps.setInt(2, product_id);
            ps.setString(3, user_name);
            ps.setString(4, email);
            ps.setString(5, shipping_address);
            ps.setString(6, billing_address);
            ps.setString(7, product_name);
            ps.setString(8, product_image);
            ps.setDouble(9, product_price);
            ps.setInt(10, quantity);
            System.out.println(order_id);
            int executeQuery = ps.executeUpdate();
            if (executeQuery != 0) {
                response = "Success: Added successfully.";
            } else {
                response = "Error: Faile to add data into databse.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return response;
    }

    public List<Order> getOrders() {
        List ordlist = null;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM Orders ORDER BY order_date DESC";
            ps = this.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ordlist = new ArrayList();
            while (rs.next()) {
                Order o = new Order();
                o.setOrder_id(rs.getString("order_id"));
                o.setOrder_status(rs.getString("order_status"));
                o.setOrder_amount(rs.getDouble("order_amount"));
                ordlist.add(o);
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
        return ordlist;
    }

    public List<Order> getOrdersByEmail(String email) {
        List ordlist = null;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM Orders WHERE email=? ORDER BY id DESC";
            ps = this.conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            ordlist = new ArrayList();
            while (rs.next()) {
                Order o = new Order();
                o.setOrder_id(rs.getString("order_id"));
                o.setOrder_status(rs.getString("order_status"));
                o.setOrder_amount(rs.getDouble("order_amount"));
                ordlist.add(o);
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
        return ordlist;
    }

    public List<OrdersDetail> getOrdersDetail() {
        List orderlist = null;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM orders_detail";
            ps = this.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            orderlist = new ArrayList();
            while (rs.next()) {
                OrdersDetail od = new OrdersDetail();
                od.setOrder_id(rs.getString("order_id"));
                od.setUser_name(rs.getString("username"));
                od.setProduct_name(rs.getString("product_name"));
                od.setProduct_image(rs.getString("product_image"));
                od.setProduct_price(rs.getDouble("product_price"));
                od.setProduct_quantity(rs.getInt("quantity"));
                orderlist.add(od);
            }
            this.count = orderlist.size();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(orderlist);
        return orderlist;
    }

    public List<OrdersDetail> getOrdersDetailByUser(String email) {
        List orderlist = null;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM orders_detail WHERE email=?";
            ps = this.conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            orderlist = new ArrayList();
            while (rs.next()) {
                OrdersDetail od = new OrdersDetail();
                od.setOrder_id(rs.getString("order_id"));
                od.setProduct_name(rs.getString("product_name"));
                od.setProduct_image(rs.getString("product_image"));
                od.setProduct_price(rs.getDouble("product_price"));
                od.setProduct_quantity(rs.getInt("quantity"));
                orderlist.add(od);
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
        System.out.println(orderlist);
        return orderlist;
    }
}
