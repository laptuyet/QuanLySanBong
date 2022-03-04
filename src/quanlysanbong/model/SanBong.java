/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.model;

/**
 *
 * @author tranh
 */
public class SanBong {

    private String maSan;
    private String tenSan;
    private String maLoaiSan;

    public SanBong() {
    }

    public SanBong(String maSan, String tenSan, String maLoaiSan) {
        this.maSan = maSan;
        this.tenSan = tenSan;
        this.maLoaiSan = maLoaiSan;
    }

    public String getMaSan() {
        return maSan;
    }

    public void setMaSan(String maSan) {
        this.maSan = maSan;
    }

    public String getTenSan() {
        return tenSan;
    }

    public void setTenSan(String tenSan) {
        this.tenSan = tenSan;
    }

    public String getMaLoaiSan() {
        return maLoaiSan;
    }

    public void setMaLoaiSan(String maLoaiSan) {
        this.maLoaiSan = maLoaiSan;
    }

}
