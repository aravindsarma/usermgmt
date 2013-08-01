/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sqlit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aravind Sarma Yeluripati
 */
public class DBConnectionManager {

    private static final String db_url = "jdbc:mysql://localhost:3306/usermgmt";
    private static final String db_user = "root";
    private static final String db_password = "Justdoit12";
    private static final String db_driver = "com.mysql.jdbc.Driver";

    //loading the JDBC driver
    static {
        try {
            Class.forName(db_driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //establishing a connection
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(db_url, db_user, db_password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
