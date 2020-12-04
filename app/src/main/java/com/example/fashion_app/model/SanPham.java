package com.example.fashion_app.model;

public class SanPham {
    private  String maSanPham;
    private  String tenSanPham;
    private  String giaTien;
    private  String moTa;
    private  String soLuong;
    private String hinhanh;
    private  String maLoai;

    public SanPham(String tenSanPham, String giaTien, String moTa, String soLuong, String hinhanh, String maLoai) {
        this.tenSanPham = tenSanPham;
        this.giaTien = giaTien;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.hinhanh = hinhanh;
        this.maLoai = maLoai;
    }

    public SanPham(String tenSanPham, String giaTien, String moTa, String soLuong, String maLoai) {
        this.tenSanPham = tenSanPham;
        this.giaTien = giaTien;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.maLoai = maLoai;
    }

    public SanPham() {
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }


    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }
}
