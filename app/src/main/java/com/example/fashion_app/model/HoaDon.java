package com.example.fashion_app.model;

public class HoaDon {
    private String maHoadon;
    private String Ngay;
    private String maSanpham;
    private String trangThai;
    private String maKhachhang;


    public HoaDon(String maHoadon, String ngay, String maSanpham, String trangThai, String maKhachhang) {
        this.maHoadon = maHoadon;
        Ngay = ngay;
        this.maSanpham = maSanpham;
        this.trangThai = trangThai;
        this.maKhachhang = maKhachhang;
    }

    public String getMaHoadon() {
        return maHoadon;
    }

    public void setMaHoadon(String maHoadon) {
        this.maHoadon = maHoadon;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public String getMaSanpham() {
        return maSanpham;
    }

    public void setMaSanpham(String maSanpham) {
        this.maSanpham = maSanpham;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaKhachhang() {
        return maKhachhang;
    }

    public void setMaKhachhang(String maKhachhang) {
        this.maKhachhang = maKhachhang;
    }
}
