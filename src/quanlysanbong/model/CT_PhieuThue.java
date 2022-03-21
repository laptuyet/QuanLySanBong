/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.model;

/**
 *
 * @author tranh
 */
public class CT_PhieuThue {
    private String mapt;
    private String masan;
    private String gioden;
    private String gio_dukientra;
    private String giotra;
    private double thanhtien;
    private String makhunggio;

    public CT_PhieuThue() {
    }

    public CT_PhieuThue(String mapt, String masan, String gioden,
            String gio_dukientra, String giotra, double thanhtien, String makhunggio) {
        this.mapt = mapt;
        this.masan = masan;
        this.gioden = gioden;
        this.gio_dukientra = gio_dukientra;
        this.giotra = giotra;
        this.thanhtien = thanhtien;
        this.makhunggio = makhunggio;
    }

    public String getMakhunggio() {
        return makhunggio;
    }

    public void setMakhunggio(String makhunggio) {
        this.makhunggio = makhunggio;
    }

    public String getMapt() {
        return mapt;
    }

    public void setMapt(String mapt) {
        this.mapt = mapt;
    }

    public String getMasan() {
        return masan;
    }

    public void setMasan(String masan) {
        this.masan = masan;
    }

    public String getGioden() {
        return gioden;
    }

    public void setGioden(String gioden) {
        this.gioden = gioden;
    }

    public String getGio_dukientra() {
        return gio_dukientra;
    }

    public void setGio_dukientra(String gio_dukientra) {
        this.gio_dukientra = gio_dukientra;
    }

    public String getGiotra() {
        return giotra;
    }

    public void setGiotra(String giotra) {
        this.giotra = giotra;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }
    
    
}
