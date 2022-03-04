/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.model;

/**
 *
 * @author tranh
 */
public class PhieuDat {
    private String mapd;
    private String ngayDat;
    private String ghiChu;
    private String makh;
    private String manv;
    private String sdt;

    public PhieuDat() {
    }

    public PhieuDat(String mapd, String ngayDat, String ghiChu, String makh, String manv, String sdt) {
        this.mapd = mapd;
        this.ngayDat = ngayDat;
        this.ghiChu = ghiChu;
        this.makh = makh;
        this.manv = manv;
        this.sdt = sdt;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMapd() {
        return mapd;
    }

    public void setMapd(String mapd) {
        this.mapd = mapd;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }
    
    
}
