/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.controller;

import DBConnection.DBConnection;
import Utils.CalendarHelper;
import java.util.ArrayList;
import quanlysanbong.model.PhieuDat;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author tranh
 */
public class PhieuDatDAO {

    private Connection conn;

    public PhieuDatDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public ArrayList<PhieuDat> getPreOrderList() {
        ArrayList<PhieuDat> preList = new ArrayList<>();
        CalendarHelper cal = new CalendarHelper();

        String sql = "SELECT * FROM PHIEUDAT";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuDat pd = new PhieuDat(rs.getString(1), cal.formatDateTime(rs.getString(2)),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));

                preList.add(pd);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return preList;
    }

    public boolean addPreOrder(PhieuDat pd) throws ParseException {
        String sql = "INSERT INTO PHIEUDAT(mapd, ngaydat, ghichu, makh, manv, sdt) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pd.getMapd());
            SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            long ms = spf.parse(pd.getNgayDat()).getTime();
            Timestamp ts = new Timestamp(ms);
            ps.setTimestamp(2, ts);
            ps.setString(3, pd.getGhiChu());
            ps.setString(4, pd.getMakh());
            ps.setString(5, pd.getManv());
            ps.setString(6, pd.getSdt());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public PhieuDat getPreOrder(String mapd, ArrayList<PhieuDat> pdList) {
        PhieuDat pd = null;
        for(PhieuDat item : pdList) {
            if(item.getMapd().equals(mapd)) {
                pd = item;
            }
        }
        return pd;
    }

}
