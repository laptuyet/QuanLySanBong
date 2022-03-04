/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanbong.model;

/**
 *
 * @author tranh
 */
public class KhachHang {

    private String makh;
    private String ho;
    private String ten;
    private String sdt;
    private String cmnd;
    private int solan_thuesan;
    private String taikhoan;
    private String loaiTk;

    public KhachHang() {
    }

    public KhachHang(String makh, String ho, String ten, String sdt, String cmnd, int solan_thuesan, String taikhoan, String loaiTk) {
        this.makh = makh;
        this.ho = ho;
        this.ten = ten;
        this.sdt = sdt;
        this.cmnd = cmnd;
        this.solan_thuesan = solan_thuesan;
        this.taikhoan = taikhoan;
        this.loaiTk = loaiTk;
    }

    public String getLoaiTk() {
        return loaiTk;
    }

    public void setLoaiTk(String loaiTk) {
        this.loaiTk = loaiTk;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
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

    public int getSolan_thuesan() {
        return solan_thuesan;
    }

    public void setSolan_thuesan(int solan_thuesan) {
        this.solan_thuesan = solan_thuesan;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "makh=" + makh + ", ho=" + ho + ", ten=" + ten + ", sdt=" + sdt + ", cmnd=" + cmnd + ", solan_thuesan=" + solan_thuesan + ", taikhoan=" + taikhoan + ", loaiTk=" + loaiTk + '}';
    }

}
