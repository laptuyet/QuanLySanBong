/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.model;

/**
 *
 * @author tranh
 */
public class PhieuThue {
    private String mapt;
    private String ngayThue;
    private String ghiChu;
    private String makh;
    private String manv;
    private String mapd;
    private String sdt;

    public PhieuThue() {
    }

    public PhieuThue(String mapt, String ngayThue, String ghiChu, String makh, String manv, String mapd, String sdt) {
        this.mapt = mapt;
        this.ngayThue = ngayThue;
        this.ghiChu = ghiChu;
        this.makh = makh;
        this.manv = manv;
        this.mapd = mapd;
        this.sdt = sdt;
    }

    public String getMapd() {
        return mapd;
    }

    public void setMapd(String mapd) {
        this.mapd = mapd;
    }

    public String getMapt() {
        return mapt;
    }

    public void setMapt(String mapt) {
        this.mapt = mapt;
    }

    public String getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
    
}
