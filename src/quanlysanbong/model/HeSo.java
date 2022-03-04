/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.model;

/**
 *
 * @author tranh
 */
public class HeSo {
    private String maKhungGio;
    private String tg_bd;
    private String tg_kt;
    private float heSo;

    public HeSo() {
    }

    public HeSo(String maKhungGio, String tg_bd, String tg_kt, float heSo) {
        this.maKhungGio = maKhungGio;
        this.tg_bd = tg_bd;
        this.tg_kt = tg_kt;
        this.heSo = heSo;
    }

    public float getHeSo() {
        return heSo;
    }

    public void setHeSo(float heSo) {
        this.heSo = heSo;
    }

    public String getMaKhungGio() {
        return maKhungGio;
    }

    public void setMaKhungGio(String maKhungGio) {
        this.maKhungGio = maKhungGio;
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
