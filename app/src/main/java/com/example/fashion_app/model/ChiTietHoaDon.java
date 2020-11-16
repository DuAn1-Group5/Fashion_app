package com.example.fashion_app.model;

public class ChiTietHoaDon {
    private String maChitiethoadon;
    private String soLuong;
    private String maHoadon;
    private String maSanpham;


    public ChiTietHoaDon(String maChitiethoadon, String soLuong, String maHoadon, String maSanpham) {
        this.maChitiethoadon = maChitiethoadon;
        this.soLuong = soLuong;
        this.maHoadon = maHoadon;
        this.maSanpham = maSanpham;
    }

    public String getMaChitiethoadon() {
        return maChitiethoadon;
    }

    public void setMaChitiethoadon(String maChitiethoadon) {
        this.maChitiethoadon = maChitiethoadon;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaHoadon() {
        return maHoadon;
    }

    public void setMaHoadon(String maHoadon) {
        this.maHoadon = maHoadon;
    }

    public String getMaSanpham() {
        return maSanpham;
    }

    public void setMaSanpham(String maSanpham) {
        this.maSanpham = maSanpham;
    }
}
