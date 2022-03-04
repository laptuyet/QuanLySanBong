/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.controller;

import DBConnection.DBConnection;
import java.sql.*;

/**
 *
 * @author tranh
 */
public class AccountDAO {

    private Connection conn;

    public AccountDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public boolean addAccount(String username, String role) {
        String sql = "INSERT INTO TAIKHOAN(taikhoan, vaitro) VALUES(?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, role);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean checkUsername(String username) {

        String sql = "SELECT * FROM TAIKHOAN WHERE taikhoan = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
