/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.controller;

import DBConnection.DBConnection;
import Utils.CalendarHelper;
import java.sql.*;
import java.util.ArrayList;
import quanlysanbong.model.Bill;

/**
 *
 * @author tranh
 */
public class BillDAO {

    private Connection conn;
    private CalendarHelper cal = new CalendarHelper();

    public BillDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public ArrayList<Bill> getBillList() {
        ArrayList<Bill> billList = new ArrayList<>();

        String sql = "SELECT * FROM HOADON";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            String date;
            while (rs.next()) {
                date = cal.formatDateTime(rs.getString(2));
                Bill bill = new Bill(rs.getString(1), date, rs.getDouble(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
                billList.add(bill);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return billList;
    }
    
    public boolean addBill(Bill bill) {
        String sql = "INSERT INTO HOADON(mahd, ngaylap, tongtien, mapt, manv, makh)"
                + "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bill.getMahd());
            
            long ms = cal.getMiliSecondTime(bill.getNgaylap());
            ps.setTimestamp(2, new Timestamp(ms));
            
            ps.setDouble(3, bill.getTongtien());
            ps.setString(4, bill.getMapt());
            ps.setString(5, bill.getManv());
            ps.setString(6, bill.getMakh());
            
            return ps.executeUpdate() > 0;
            
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean isOrderHasBill(String mapt) {
        String sql = "SELECT * FROM HOADON WHERE mapt=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mapt);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

//    public static void main(String[] args) {
//        boolean billList = new BillDAO().isOrderHasBill("pt02");
//        System.out.println(billList);
//    }
}
