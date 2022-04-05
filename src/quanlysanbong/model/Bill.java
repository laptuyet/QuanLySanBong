/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.model;

/**
 *
 * @author tranh
 */
public class Bill {
    private String mahd;
    private String ngaylap;
    private double tongtien;
    private String mapt;
    private String manv;
    private String makh;

    public Bill() {
    }

    public Bill(String mahd, String ngaylap, double tongtien, String mapt, String manv, String makh) {
        this.mahd = mahd;
        this.ngaylap = ngaylap;
        this.tongtien = tongtien;
        this.mapt = mapt;
        this.manv = manv;
        this.makh = makh;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(String ngaylap) {
        this.ngaylap = ngaylap;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public String getMapt() {
        return mapt;
    }

    public void setMapt(String mapt) {
        this.mapt = mapt;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    @Override
    public String toString() {
        return "Bill{" + "mahd=" + mahd + ", ngaylap=" + ngaylap + ", tongtien=" + tongtien + ", mapt=" + mapt + ", manv=" + manv + ", makh=" + makh + '}';
    }
    
    
}
