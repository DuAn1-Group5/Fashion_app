package com.example.fashion_app.model;

public class KhachHang {
    private String maKhachhang;
    private String tenKhachhang;
    private String Diachi;
    private String soDienthoai;
    private String eMail;


    public KhachHang(String maKhachhang, String tenKhachhang, String diachi, String soDienthoai, String eMail) {
        this.maKhachhang = maKhachhang;
        this.tenKhachhang = tenKhachhang;
        Diachi = diachi;
        this.soDienthoai = soDienthoai;
        this.eMail = eMail;
    }

    public String getMaKhachhang() {
        return maKhachhang;
    }

    public void setMaKhachhang(String maKhachhang) {
        this.maKhachhang = maKhachhang;
    }

    public String getTenKhachhang() {
        return tenKhachhang;
    }

    public void setTenKhachhang(String tenKhachhang) {
        this.tenKhachhang = tenKhachhang;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public String getSoDienthoai() {
        return soDienthoai;
    }

    public void setSoDienthoai(String soDienthoai) {
        this.soDienthoai = soDienthoai;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
