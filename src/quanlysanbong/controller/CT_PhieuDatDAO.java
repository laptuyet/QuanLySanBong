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
import quanlysanbong.model.CT_PhieuDat;

/**
 *
 * @author tranh
 */
public class CT_PhieuDatDAO {

    private Connection conn;

    public CT_PhieuDatDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public boolean addPreOrderDetail(ArrayList<CT_PhieuDat> ctpdList) throws ParseException {
        String sql = "INSERT INTO CHITIET_PHIEUDAT(mapd, masan, tg_dukienden, tg_dukiendi, tiendatcoc)"
                + "VALUES(?, ?, ?, ?, ?)";
        SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (CT_PhieuDat ctpd : ctpdList) {
                ps.setString(1, ctpd.getMapd());
                ps.setString(2, ctpd.getMasan());

                long ms = spf.parse(ctpd.getTg_bd()).getTime();
                ps.setTimestamp(3, new Timestamp(ms));
                ms = spf.parse(ctpd.getTg_kt()).getTime();
                ps.setTimestamp(4, new Timestamp(ms));

                ps.setDouble(5, ctpd.getDeposit());

                ps.addBatch();
            }

            return ps.executeBatch() != null;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<CT_PhieuDat> getPreOrderDetail(String mapd) {
        ArrayList<CT_PhieuDat> ctpdList = new ArrayList<>();

        String sql = "SELECT CT.* \n"
                + "FROM CHITIET_PHIEUDAT CT, PHIEUDAT PD\n"
                + "WHERE CT.mapd = PD.mapd AND PD.mapd=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mapd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CT_PhieuDat ctpd = new CT_PhieuDat(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getDouble(5));
                ctpdList.add(ctpd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ctpdList;
    }
}
