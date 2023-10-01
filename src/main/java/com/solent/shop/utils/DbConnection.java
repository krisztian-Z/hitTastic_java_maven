package com.solent.shop.utils;

import java.sql.Connection;
import static com.solent.shop.utils.Constants.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(DB_HOST, DB_USER, DB_PASS);
            return conn;
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex.getCause());
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getErrorCode());
        }
        return conn;
    }
}
