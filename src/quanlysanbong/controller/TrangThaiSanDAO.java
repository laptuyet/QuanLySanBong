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

/**
 *
 * @author tranh
 */
public class TrangThaiSanDAO {

    private Connection conn;

    public TrangThaiSanDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public ArrayList<TrangThaiSan> getTrangThaiSanList(String sDateTime, String eEndDateTime) {
        ArrayList<TrangThaiSan> stateList = new ArrayList<>();

        String sql = "SELECT SAN.*, tentt \n"
                + "FROM (SELECT masan, tensan, tenloaisan, giatien_motgio \n"
                + "      FROM SANBONG, LOAISAN\n"
                + "      WHERE SANBONG.maloaisan = LOAISAN.maloaisan\n"
                + "     ) SAN LEFT JOIN CHITIET_TRANGTHAISAN CT ON CT.masan = SAN.masan LEFT JOIN TRANGTHAISAN TT\n"
                + "	 ON TT.matt = CT.matt AND (ngaygio_bd BETWEEN ? AND ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sDateTime);
            ps.setString(2, eEndDateTime);
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
}
