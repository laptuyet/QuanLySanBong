/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBConnection;

import java.sql.*;

/**
 *
 * @author tranh
 */
public class DBConnection {

    private Connection conn;

    public Connection getDBConnection() {
        String sql = "jdbc:sqlserver://localhost:1433;databaseName=QUANLYSANBONG;"
                + "user=sa;password=123";
        try {
            conn = DriverManager.getConnection(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
    public static void main(String[] args) {
        System.out.println(new DBConnection().getDBConnection());
    }
}
