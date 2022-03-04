/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.model;

import java.util.Date;

/**
 *
 * @author tranh
 */
public class NhanVien {
    private String manv;
    private String ho;
    private String ten;
    private String sdt;
    private String cmnd;
    boolean trangthainghi;
    String taikhoan;

    public NhanVien() {
    }

    public NhanVien(String manv, String ho, String ten, String sdt, String cmnd, boolean trangthainghi, String taikhoan) {
        this.manv = manv;
        this.ho = ho;
        this.ten = ten;
        this.sdt = sdt;
        this.cmnd = cmnd;
        this.trangthainghi = trangthainghi;
        this.taikhoan = taikhoan;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public boolean isTrangthainghi() {
        return trangthainghi;
    }

    public void setTrangthainghi(boolean trangthainghi) {
        this.trangthainghi = trangthainghi;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "manv=" + manv + ", ho=" + ho + ", ten=" + ten + ", sdt=" + sdt + ", cmnd=" + cmnd + ", trangthainghi=" + trangthainghi + ", taikhoan=" + taikhoan + '}';
    }

}
