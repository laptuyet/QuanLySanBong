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
import quanlysanbong.model.CT_PhieuThue;

/**
 *
 * @author tranh
 */
public class CT_PhieuThueDAO {

    private Connection conn;
    CalendarHelper cal = new CalendarHelper();
    
    public CT_PhieuThueDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public ArrayList<CT_PhieuThue> getOrderDetailWithPreOrder(String mapd, String mapt){
        ArrayList<CT_PhieuThue> ctptList = new ArrayList<>();
        HeSoDAO hsDao = new HeSoDAO();
        String makhunggio = "";

        String sql = "SELECT CT.*, LS.giatien_motgio \n"
                + "FROM CHITIET_PHIEUDAT CT INNER JOIN SANBONG S ON CT.masan = S.masan \n"
                + "INNER JOIN LOAISAN LS ON S.maloaisan = LS.maloaisan \n"
                + "WHERE CT.mapd = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mapd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                
                makhunggio = hsDao.getMaKhungGio(rs.getString(3));
                
                // tinh thanhtien, chua nhan (X) he so khung gio
                String sDateTime = rs.getString(3), eDateTime = rs.getString(4);
                float totalTime = cal.totalTime(sDateTime, eDateTime);
                
                //
                
                CT_PhieuThue ctpt = new CT_PhieuThue(mapt, rs.getString(2),
                        cal.formatDateTime(sDateTime), cal.formatDateTime(eDateTime),
                        "", rs.getDouble(6) * totalTime, makhunggio);
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

                ps.setDouble(6, ctpt.getThanhtien());
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
                       cal.formatDateTime(rs.getString(3)), cal.formatDateTime(rs.getString(4)),
                        cal.formatDateTime(rs.getString(5)), rs.getDouble(6), rs.getString(7));
                ctptList.add(ctpt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ctptList;
    }
    
    public boolean updateLeaveTimeOfStadium(CT_PhieuThue item) throws ParseException {
        String sql = "UPDATE CHITIET_PHIEUTHUE SET giotra=? WHERE mapt=? AND masan=?";
        SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            long ms = spf.parse(item.getGiotra()).getTime();
            ps.setTimestamp(1, new Timestamp(ms));
            ps.setString(2, item.getMapt());
            ps.setString(3, item.getMasan());
            
            return ps.executeUpdate() > 0;
            
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<CT_PhieuThue> getOrderDetailWithBillId(String mahd, String mapt){
        ArrayList<CT_PhieuThue> ctptList = new ArrayList<>();
        HeSoDAO hsDao = new HeSoDAO();
        String makhunggio = "";

        String sql = "SELECT CTPT.*\n" +
                    "FROM HOADON HD INNER JOIN CHITIET_PHIEUTHUE CTPT ON HD.mapt = CTPT.mapt\n" +
                    "	AND mahd=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mahd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { 
                String sDateTime = rs.getString(3),
                        esDateTime = rs.getString(4),
                        eDateTime = rs.getString(5);
                
                CT_PhieuThue ctpt = new CT_PhieuThue(mapt, rs.getString(2),
                        cal.formatDateTime(sDateTime), cal.formatDateTime(esDateTime),
                        cal.formatDateTime(eDateTime), rs.getDouble(6), rs.getString(7));
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
