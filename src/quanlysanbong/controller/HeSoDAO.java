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
import quanlysanbong.model.HeSo;

/**
 *
 * @author tranh
 */
public class HeSoDAO {

    private Connection conn;

    public HeSoDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public ArrayList<HeSo> getRateList() {
        ArrayList<HeSo> rateList = new ArrayList<>();
        String sql = "SELECT * FROM HESO_THEOGIO";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HeSo rate = new HeSo(rs.getString(1), rs.getTime(2).toString(), rs.getTime(3).toString(), rs.getFloat(4));
                rateList.add(rate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rateList;
    }

    public boolean updateRate(HeSo rate) throws ParseException {
        String sql = "UPDATE HESO_THEOGIO SET tu=?, den=?, heso=? WHERE makhunggio=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            SimpleDateFormat spf = new SimpleDateFormat("HH:mm:ss"); //old: hh:MM:ss
            long begin = spf.parse(rate.getTg_bd()).getTime();
            long end = spf.parse(rate.getTg_kt()).getTime();
            Time t1 = new Time(begin);
            Time t2 = new Time(end);
            ps.setTime(1, t1);
            ps.setTime(2, t2);
            ps.setFloat(3, rate.getHeSo());
            ps.setString(4, rate.getMaKhungGio());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public HeSo getRate(String makhunggio) {
        HeSo rate;
        String sql = "SELECT * FROM HESO_THEOGIO WHERE makhunggio= ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, makhunggio);
            ResultSet rs = ps.executeQuery();
            rs.next();
            rate = new HeSo(rs.getString(1), rs.getTime(2).toString(), rs.getTime(3).toString(), rs.getFloat(4));

            return rate;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getMaKhungGio(String ngayGioBatDau) {

        String[] dateTimeArr = ngayGioBatDau.split("\\s");
        String gioBatDau = dateTimeArr[1];

        String sql = "SELECT makhunggio FROM HESO_THEOGIO WHERE ? BETWEEN tu AND den";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, gioBatDau);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

//    public static void main(String[] args) {
//        System.out.println(new HeSoDAO().getMaKhungGio("2022/03/30 18:00:00"));
//    }
}
