/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.controller;

import DBConnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import quanlysanbong.model.DichVu;

/**
 *
 * @author tranh
 */
public class DichVuDAO {

    private Connection conn;

    public DichVuDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public ArrayList<DichVu> getDichVuList() {
        ArrayList<DichVu> list = new ArrayList<>();
        String sql = "SELECT * FROM DICHVU";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DichVu dv = new DichVu(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4), rs.getString(5));
                list.add(dv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean addDichVu(DichVu dv) {
        String sql = "INSERT INTO DICHVU(madv, tendv, dvt, dongia, hinhanh) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, dv.getMadv());
            ps.setString(2, dv.getTendv());
            ps.setString(3, dv.getDvt());
            ps.setDouble(4, dv.getDongia());
            ps.setString(5, dv.getHinhanh());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateDichVu(DichVu dv) {
        String sql = "UPDATE DICHVU SET tendv=?, dvt=?, dongia=?, hinhanh=? WHERE madv=?";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, dv.getTendv());
            ps.setString(2, dv.getDvt());
            ps.setDouble(3, dv.getDongia());
            ps.setString(4, dv.getHinhanh());
            ps.setString(5, dv.getMadv());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteDichVu(String madv) {
        String sql = "DELETE FROM DICHVU WHERE madv=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, madv);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean checkFoodId(String foodId) {

        String sql = "SELECT * FROM DICHVU WHERE madv=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, foodId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
