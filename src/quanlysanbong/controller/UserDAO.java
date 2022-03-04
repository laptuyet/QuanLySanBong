/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.controller;

import DBConnection.DBConnection;
import java.sql.*;
import quanlysanbong.model.Account;

/**
 *
 * @author tranh
 */
public class UserDAO {

    private Connection conn;

    public UserDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public Account getAccount(String username) {
        Account acc = new Account();
        try {
            String sql = "SELECT * FROM TAIKHOAN WHERE taikhoan=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                acc.setTaikhoan(rs.getString(1));
                acc.setMatkhau(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acc;
    }

    public String getRoleOfAccount(String username, String password) {
        String role = "";
        try {
            String sql = "SELECT vaitro FROM TAIKHOAN WHERE taikhoan=? AND matkhau=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                role = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public boolean changPassword(String user, String newpass) {
        String sql = "UPDATE TAIKHOAN SET matkhau=? WHERE taikhoan=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newpass);
            ps.setString(2, user);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
