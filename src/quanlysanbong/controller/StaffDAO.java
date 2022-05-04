/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.controller;

import DBConnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import quanlysanbong.model.NhanVien;

/**
 *
 * @author tranh
 */
public class StaffDAO {

    private Connection conn;

    public StaffDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public ArrayList<NhanVien> getStaffList() {
        ArrayList<NhanVien> staffList = new ArrayList<>();

        String sql = "SELECT * FROM NHANVIEN";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getBoolean(6), rs.getString(7));

                staffList.add(nv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return staffList;
    }

//    public boolean updateStaff(NhanVien nv) {
//        String sql = "UPDATE NHANVIEN SET ho=?, ten=?, sdt=?, cmnd=?, trangthainghi=? WHERE manv=?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, nv.getHo());
//            ps.setString(2, nv.getTen());
//            ps.setString(3, nv.getSdt());
//            ps.setString(4, nv.getCmnd());
//            ps.setBoolean(5, nv.isTrangthainghi());
//            ps.setString(6, nv.getManv());
//
//            return ps.executeUpdate() > 0;
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return false;
//    }
    
    public boolean addStaff(NhanVien nv){
        String sql = "INSERT INTO NHANVIEN(manv, ho, ten, sdt, cmnd, trangthainghi, taikhoan)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nv.getManv());
            ps.setString(2, nv.getHo());
            ps.setString(3, nv.getTen());
            ps.setString(4, nv.getSdt());
            ps.setString(5, nv.getCmnd());
            ps.setBoolean(6, nv.isTrangthainghi());
            ps.setString(7, nv.getTaikhoan());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    public NhanVien getStaff(String username) {
        NhanVien nv = null;
        String sql = "SELECT * FROM NHANVIEN WHERE taikhoan=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getString(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nv;
    }

    public boolean updateStaffInfo(NhanVien nv) {
        String sql = "UPDATE NHANVIEN SET ho=?, ten=?, sdt=?, cmnd=?, trangthainghi=? WHERE manv=?";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, nv.getHo());
            ps.setString(2, nv.getTen());
            ps.setString(3, nv.getSdt());
            ps.setString(4, nv.getCmnd());
            ps.setBoolean(5, nv.isTrangthainghi());
            ps.setString(6, nv.getManv());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean checkStaffWorkId(String workId) {

        String sql = "SELECT * FROM NHANVIEN WHERE manv=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, workId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    public boolean isQuit(String username) {

        String sql = "SELECT trangthainghi FROM NHANVIEN WHERE taikhoan=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getBoolean(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
