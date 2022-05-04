/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.controller;

import DBConnection.DBConnection;
import java.sql.Connection;
import java.util.ArrayList;
import quanlysanbong.model.TrangThaiSan;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import quanlysanbong.model.CT_PhieuDat;
import quanlysanbong.model.CT_PhieuThue;

/**
 *
 * @author tranh
 */
public class TrangThaiSanDAO {

    private Connection conn;

    public TrangThaiSanDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public ArrayList<TrangThaiSan> getTrangThaiSanList(String sDateTime, String eDateTime) {
        ArrayList<TrangThaiSan> stateList = new ArrayList<>();

        String sql = "DECLARE @START smalldatetime = ?,\n"
                + "		@END smalldatetime = ?\n"
                + "SELECT SAN.*, tentt\n"
                + "FROM (SELECT masan, tensan, tenloaisan, giatien_motgio\n"
                + "      FROM SANBONG, LOAISAN\n"
                + "      WHERE SANBONG.maloaisan = LOAISAN.maloaisan) SAN\n"
                + "             LEFT JOIN CHITIET_TRANGTHAISAN CT ON CT.masan = SAN.masan \n"
                + "			 AND ( (ngaygio_bd BETWEEN @START AND @END)\n"
                + "				OR ( ngaygio_bd <= @START AND @END <= ngaygio_kt) \n"
                + "				OR (ngaygio_kt BETWEEN @START AND @END) )\n"
                + "             LEFT JOIN TRANGTHAISAN TT ON TT.matt = CT.matt";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sDateTime);
            ps.setString(2, eDateTime);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String state = rs.getString(5) == null ? "Trống" : rs.getString(5);
                TrangThaiSan ttSan = new TrangThaiSan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), state);
                stateList.add(ttSan);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return stateList;
    }

    public boolean addTrangThaiSanPreOrder(ArrayList<CT_PhieuDat> ctpdList) throws ParseException {
        String sql = "INSERT INTO CHITIET_TRANGTHAISAN(masan, matt, ngaygio_bd, ngaygio_kt)"
                + "VALUES(?, ?, ?, ?)";
        SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (CT_PhieuDat ctpd : ctpdList) {
                ps.setString(1, ctpd.getMasan());
                ps.setString(2, "tt02"); // da dat truoc

                long ms = spf.parse(ctpd.getTg_bd()).getTime();
                ps.setTimestamp(3, new Timestamp(ms));
                ms = spf.parse(ctpd.getTg_kt()).getTime();
                ps.setTimestamp(4, new Timestamp(ms));

                ps.addBatch();
            }

            return ps.executeBatch() != null;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean addTrangThaiSanOrder(ArrayList<CT_PhieuThue> ctptList) throws ParseException {
        String sql = "INSERT INTO CHITIET_TRANGTHAISAN(masan, matt, ngaygio_bd, ngaygio_kt)"
                + "VALUES(?, ?, ?, ?)";
        SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (CT_PhieuThue ctpt : ctptList) {
                ps.setString(1, ctpt.getMasan());
                ps.setString(2, "tt04"); // dang thue

                long ms = spf.parse(ctpt.getGioden()).getTime();
                ps.setTimestamp(3, new Timestamp(ms));
                ms = spf.parse(ctpt.getGio_dukientra()).getTime();
                ps.setTimestamp(4, new Timestamp(ms));

                ps.addBatch();
            }

            return ps.executeBatch() != null;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
