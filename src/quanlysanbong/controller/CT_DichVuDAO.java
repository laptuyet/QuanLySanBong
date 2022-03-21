/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.controller;

import DBConnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import quanlysanbong.model.CT_DichVu;

/**
 *
 * @author tranh
 */
public class CT_DichVuDAO {

    private Connection conn;

    public CT_DichVuDAO() {
        conn = new DBConnection().getDBConnection();
    }

    public boolean addServiceDetailOrder(ArrayList<CT_DichVu> ctdvList) {
        if (!ctdvList.isEmpty()) {
            String sql = "INSERT INTO CHITIET_DICHVU(mapt, madv, soluong) VALUES(?, ?, ?)";

            try {

                PreparedStatement ps = conn.prepareStatement(sql);
                for (CT_DichVu ctdv : ctdvList) {
                    ps.setString(1, ctdv.getMapt());
                    ps.setString(2, ctdv.getMadv());
                    ps.setInt(3, ctdv.getSoluong());

                    ps.addBatch();
                }

                return ps.executeBatch() != null;

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return true;
    }
    
    public ArrayList<CT_DichVu> getOrderServices(String mapt){
        ArrayList<CT_DichVu> ctdvList = new ArrayList<>();
        
        String sql = "SELECT * FROM CHITIET_DICHVU WHERE mapt=?";
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mapt);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ctdvList.add(new CT_DichVu(rs.getString(1), rs.getString(2), rs.getInt(3)));
            }
            
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return ctdvList;
    }
}
