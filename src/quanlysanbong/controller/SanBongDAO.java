/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.controller;

import DBConnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import quanlysanbong.model.SanBong;

/**
 *
 * @author tranh
 */
public class SanBongDAO {

    private Connection conn;

    public SanBongDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public ArrayList<SanBong> getStadiumList() {
        ArrayList<SanBong> staList = new ArrayList<>();

        String sql = "SELECT * FROM SANBONG";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanBong sta = new SanBong(rs.getString(1), rs.getString(2), rs.getString(3));
                staList.add(sta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return staList;
    }

    public boolean addStadium(SanBong sta) {

        String sql = "INSERT INTO SANBONG(masan, tensan, maloaisan) VALUES(?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sta.getMaSan());
            ps.setString(2, sta.getTenSan());
            ps.setString(3, sta.getMaLoaiSan());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean updateStadium(SanBong sta) {

        String sql = "UPDATE SANBONG SET tensan = ?, maloaisan = ? WHERE masan = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(2, sta.getMaLoaiSan());
            ps.setString(1, sta.getTenSan());
            ps.setString(3, sta.getMaSan());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean deleteStadium(String masan) {
        String sql = "DELETE FROM SANBONG WHERE masan = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, masan);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean checkStaId(String id) {

        String sql = "SELECT * FROM SANBONG WHERE masan=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
}
