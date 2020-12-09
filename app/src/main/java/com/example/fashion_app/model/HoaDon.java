package com.example.fashion_app.model;

public class HoaDon {
    private String maHoadon;
    private String ngay;
    private String trangThai;
    private String maKhachhang;
    private String tongTien;
    public HoaDon() {
    }

    public HoaDon(String ngay, String trangThai, String maKhachhang, String tongTien) {
        this.ngay = ngay;
        this.trangThai = trangThai;
        this.maKhachhang = maKhachhang;
        this.tongTien = tongTien;
    }

    public String getMaHoadon() {
        return maHoadon;
    }

    public void setMaHoadon(String maHoadon) {
        this.maHoadon = maHoadon;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
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

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
}
