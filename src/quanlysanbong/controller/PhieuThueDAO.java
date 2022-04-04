/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.controller;

import DBConnection.DBConnection;
import Utils.CalendarHelper;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import quanlysanbong.model.PhieuThue;

/**
 *
 * @author tranh
 */
public class PhieuThueDAO {

    private Connection conn;

    public PhieuThueDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public ArrayList<PhieuThue> getOrderList() {
        ArrayList<PhieuThue> orderList = new ArrayList<>();
        CalendarHelper cal = new CalendarHelper();
        
        String sql = "SELECT * FROM PHIEUTHUE";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuThue pt = new PhieuThue(rs.getString(1), cal.formatDateTime(rs.getString(2)),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));

                orderList.add(pt);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orderList;
    }

    public boolean addOrder(PhieuThue pt) throws ParseException {
        String sql = "INSERT INTO PHIEUTHUE(mapt, ngaythue, ghichu, makh, manv, mapd, sdt) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pt.getMapt());
            SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            long ms = spf.parse(pt.getNgayThue()).getTime();
            Timestamp ts = new Timestamp(ms);
            ps.setTimestamp(2, ts);
            ps.setString(3, pt.getGhiChu());
            ps.setString(4, pt.getMakh());
            ps.setString(5, pt.getManv());
            ps.setString(6, pt.getMapd());
            ps.setString(7, pt.getSdt());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
