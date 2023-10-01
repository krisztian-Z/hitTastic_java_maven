package com.solent.shop.dao;

import com.solent.shop.models.User;
import com.solent.shop.utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {

    private String result;

    protected Connection conn = null;

    protected PreparedStatement ps = null;

    protected ResultSet rs = null;

    protected User user;

    public int count = 0;

    public double credit_amount = 0.00;

    public String credit_amt = null;

    public UserDao() {
    }

    public User LoginUser(String email, String password) {
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            ps = this.conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            Formatter fm = new Formatter();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getInt("user_role"), rs.getInt("user_status"));
                System.out.println(rs.getDouble("balance"));
                this.credit_amount = rs.getDouble("balance");
                credit_amt = fm.format("%.2f", this.credit_amount).toString();
                System.out.printf(credit_amt);
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    public String RegisterUser(String name, String email, String password, String cpass) {
        if (!(password.equals(cpass))) {
            return "Error: Password and confirm password doesnot matched.";
        }
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "INSERT INTO users (name, email, password)VALUES(?, ?, ?)";
            ps = this.conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.execute();
            result = "Success: User creattion success.";
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

    public List<User> getAllUsers() {
        List userlist = null;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM users where user_role !=1";
            ps = this.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            userlist = new ArrayList();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setUser_role(rs.getInt("user_role"));
                user.setUser_status(rs.getInt("user_status"));
                userlist.add(user);
            }
            this.count = userlist.size();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return userlist;
    }

    public User getUserByID(int id) {
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getInt("user_role"), rs.getInt("user_status"));
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    public List<User> getUserAsListByID(int id) {
        List userlist = null;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            userlist = new ArrayList();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setUser_role(rs.getInt("user_role"));
                user.setUser_status(rs.getInt("user_status"));
                userlist.add(user);
            }
            return userlist;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return userlist;
    }

    public int updateUser(String name, String email, int id) {
        int result = 0;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "Update users set name=? , email=? WHERE id=?";
            ps = this.conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, id);
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

    public int deleteUser(int id) {
        ResultSet result;
        int row = 0;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "Delete from users WHERE id=?";
            ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return row;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getErrorCode());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return row;
    }

    public double updateUserPurchaseBalance(int userID, double amount) {
        double final_balance = 0.00;
        this.conn = new DbConnection().getConnection();
        try {
            String sql = "SELECT balance FROM users WHERE id = ?";
            ps = this.conn.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                double current_balance = rs.getDouble("balance");
                final_balance = current_balance - amount;
                String update = "Update users set balance=? WHERE id=?";
                ps = this.conn.prepareStatement(update);
                ps.setDouble(1, final_balance);
                ps.setInt(2, userID);
                ps.executeUpdate();
            }
            return final_balance;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return final_balance;
    }
}
