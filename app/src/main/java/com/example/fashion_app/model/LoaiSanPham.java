package com.example.fashion_app.model;

import androidx.annotation.NonNull;

public class LoaiSanPham {
    private String tenLoai;
    private String maLoai;


    public LoaiSanPham(String tenLoai) {
        this.tenLoai = tenLoai;
    }


    public LoaiSanPham() {
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }


    @NonNull
    @Override
    public String toString() {
        return getTenLoai();
    }
}
