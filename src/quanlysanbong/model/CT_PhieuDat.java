/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.model;

/**
 *
 * @author tranh
 */
public class CT_PhieuDat {
    private String mapd;
    private String masan;
    private String tg_bd;
    private String tg_kt;
    private double deposit;

    public CT_PhieuDat() {
    }

    public CT_PhieuDat(String mapd, String masan, String tg_bd, String tg_kt, double deposit) {
        this.mapd = mapd;
        this.masan = masan;
        this.tg_bd = tg_bd;
        this.tg_kt = tg_kt;
        this.deposit = deposit;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public String getMapd() {
        return mapd;
    }

    public void setMapd(String mapd) {
        this.mapd = mapd;
    }

    public String getMasan() {
        return masan;
    }

    public void setMasan(String masan) {
        this.masan = masan;
    }

    public String getTg_bd() {
        return tg_bd;
    }

    public void setTg_bd(String tg_bd) {
        this.tg_bd = tg_bd;
    }

    public String getTg_kt() {
        return tg_kt;
    }

    public void setTg_kt(String tg_kt) {
        this.tg_kt = tg_kt;
    }
    
    
}
