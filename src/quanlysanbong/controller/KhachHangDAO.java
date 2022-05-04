/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.controller;

import DBConnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import quanlysanbong.model.KhachHang;

/**
 *
 * @author tranh
 */
public class KhachHangDAO {

    private Connection conn;

    public KhachHangDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public ArrayList<KhachHang> getCustomerList() {
        ArrayList<KhachHang> cusList = new ArrayList<>();
        String sql = "SELECT * FROM KHACHHANG";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getInt(6), rs.getString(7), rs.getString(8));
                cusList.add(kh);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cusList;
    }

    public KhachHang getCustomer(String username) {
        KhachHang kh = null;
        String sql = "SELECT * FROM KHACHHANG WHERE taikhoan=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return kh;
    }

    public String getCusPhone(String cusID) {
        String cusPhone = "";
        String sql = "SELECT sdt FROM KHACHHANG WHERE makh=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cusID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cusPhone;
    }

    public boolean addCus(KhachHang kh) {
        String sql = "INSERT INTO KHACHHANG(makh, ho, ten, sdt, cmnd, solan_thuesan, taikhoan, maloaikhach)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getMakh());
            ps.setString(2, kh.getHo());
            ps.setString(3, kh.getTen());
            ps.setString(4, kh.getSdt());
            ps.setString(5, kh.getCmnd());
            ps.setInt(6, kh.getSolan_thuesan());
            ps.setString(7, kh.getTaikhoan());
            ps.setString(8, kh.getLoaiTk());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean updateCusInfo(KhachHang kh) {
        String sql = "UPDATE KHACHHANG SET ho=?, ten=?, sdt=?, cmnd=? WHERE makh=?";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, kh.getHo());
            ps.setString(2, kh.getTen());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getCmnd());
            ps.setString(5, kh.getMakh());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean checkCusId(String id) {

        String sql = "SELECT * FROM KHACHHANG WHERE makh=?";
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

    public float getMemRateDiscount(String makh) {
        String sql = "SELECT tile_giamgia\n"
                + "FROM KHACHHANG KH INNER JOIN LOAIKHACH LK ON KH.maloaikhach = LK.maloaikhach\n"
                + "	AND KH.makh=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, makh);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getFloat(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public String getMembership(String makh) {
        String sql = "SELECT maloaikhach FROM KHACHHANG WHERE makh=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, makh);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public void updateRentTimes(String cusId) {
        String sql = "DECLARE @cusID varchar(10) = ? \n"
                + "UPDATE KHACHHANG set solan_thuesan += 1 WHERE makh = @cusID\n"
                + "\n"
                + "DECLARE @renttimes int;\n"
                + "SELECT @renttimes = solan_thuesan\n"
                + "FROM KHACHHANG WHERE makh = @cusID\n"
                + "IF (@cusID <> 'khvanglai')\n"
                + "	BEGIN\n"
                + "		IF (@renttimes > 40)\n"
                + "			UPDATE KHACHHANG set maloaikhach = 'diamond' WHERE makh = @cusID\n"
                + "		ELSE IF (@renttimes BETWEEN 16 AND 40)\n"
                + "			UPDATE KHACHHANG set maloaikhach = 'gold' WHERE makh = @cusID\n"
                + "		ELSE IF (@renttimes BETWEEN 5 AND 15)\n"
                + "			UPDATE KHACHHANG set maloaikhach = 'silver' WHERE makh = @cusID\n"
                + "	END";
        try  {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cusId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
