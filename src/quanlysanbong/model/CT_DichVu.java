/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.model;

/**
 *
 * @author tranh
 */
public class CT_DichVu {
    private String mapt;
    private String madv;
    private int soluong;

    public CT_DichVu() {
    }

    public CT_DichVu(String mapt, String madv, int soluong) {
        this.mapt = mapt;
        this.madv = madv;
        this.soluong = soluong;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getMapt() {
        return mapt;
    }

    public void setMapt(String mapt) {
        this.mapt = mapt;
    }

    public String getMadv() {
        return madv;
    }

    public void setMadv(String madv) {
        this.madv = madv;
    }
    
    
}
