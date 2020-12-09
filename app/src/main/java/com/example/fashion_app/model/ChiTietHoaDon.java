package com.example.fashion_app.model;

public class ChiTietHoaDon {
    private String maChitiethoadon;
    private String soLuong;
    private String size;
    private String maHoadon;
    private String maSanpham;
    private String tenSanPham;
    private String giaSanPham;
    private String hinh;


    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String soLuong, String size, String maHoadon, String maSanpham, String tenSanPham, String giaSanPham, String hinh) {
        this.soLuong = soLuong;
        this.size = size;
        this.maHoadon = maHoadon;
        this.maSanpham = maSanpham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.hinh = hinh;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(String giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
}
