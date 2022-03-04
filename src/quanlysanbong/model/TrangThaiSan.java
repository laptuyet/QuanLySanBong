/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.model;

/**
 *
 * @author tranh
 */
public class TrangThaiSan {
    private String maSan;
    private String tenSan;
    private String tenLoaiSan;
    private double pricePerHour;
    private String tenTrangThai;

    public TrangThaiSan() {
    }

    public TrangThaiSan(String maSan, String tenSan, String tenLoaiSan, double pricePerHour, String tenTrangThai) {
        this.maSan = maSan;
        this.tenSan = tenSan;
        this.tenLoaiSan = tenLoaiSan;
        this.pricePerHour = pricePerHour;
        this.tenTrangThai = tenTrangThai;
    }

    public String getTenTrangThai() {
        return tenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
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

    public String getTenLoaiSan() {
        return tenLoaiSan;
    }

    public void setTenLoaiSan(String tenLoaiSan) {
        this.tenLoaiSan = tenLoaiSan;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
    
    
}
