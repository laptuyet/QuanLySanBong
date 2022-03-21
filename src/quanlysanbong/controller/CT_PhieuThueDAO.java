/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.controller;

import DBConnection.DBConnection;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import quanlysanbong.model.CT_PhieuThue;

/**
 *
 * @author tranh
 */
public class CT_PhieuThueDAO {

    private Connection conn;

    public CT_PhieuThueDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public ArrayList<CT_PhieuThue> getOrderDetailWithPreOrder(String mapd, String mapt, String makhunggio) {
        ArrayList<CT_PhieuThue> ctptList = new ArrayList<>();

        String sql = "SELECT CT.* \n"
                + "FROM CHITIET_PHIEUDAT CT \n"
                + "WHERE CT.mapd = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mapd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CT_PhieuThue ctpt = new CT_PhieuThue(mapt, rs.getString(2),
                        rs.getString(3), rs.getString(4), "", 0.0, makhunggio);
                ctptList.add(ctpt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ctptList;
    }

    public boolean addOrderDetailList(ArrayList<CT_PhieuThue> ctptList) throws ParseException {

        String sql = "INSERT INTO CHITIET_PHIEUTHUE(mapt, masan, gioden,"
                + " gio_dukientra, giotra, thanhtien, makhunggio) VALUES(?, ?, ?, ?, ?, ?, ?)";

        SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            for (CT_PhieuThue ctpt : ctptList) {

                ps.setString(1, ctpt.getMapt());
                ps.setString(2, ctpt.getMasan());

                long ms = spf.parse(ctpt.getGioden()).getTime();
                ps.setTimestamp(3, new Timestamp(ms));

                ms = spf.parse(ctpt.getGio_dukientra()).getTime();
                ps.setTimestamp(4, new Timestamp(ms));

                ps.setTime(5, null);

                ps.setDouble(6, 0.0);
                ps.setString(7, ctpt.getMakhunggio());

                ps.addBatch();

            }

            return ps.executeBatch() != null;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<CT_PhieuThue> getOrderDetail(String mapt) {
        ArrayList<CT_PhieuThue> ctptList = new ArrayList<>();

        String sql = "SELECT CT.* \n"
                + "FROM CHITIET_PHIEUTHUE CT \n"
                + "WHERE CT.mapt=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mapt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CT_PhieuThue ctpt = new CT_PhieuThue(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7));
                ctptList.add(ctpt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ctptList;
    }
//
//    public static void main(String[] args) {
//        ArrayList<CT_PhieuThue> ctpt = new CT_PhieuThueDAO().getOrderDetail("pt01");
//        System.out.println(ctpt.get(0).getMakhunggio());
//    }
}
