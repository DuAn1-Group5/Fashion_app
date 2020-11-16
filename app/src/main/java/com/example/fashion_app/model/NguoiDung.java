package com.example.fashion_app.model;

public class NguoiDung {
    private String maNguoidung;
    private String tenNguoidung;
    private String matKhau;


    public NguoiDung(String maNguoidung, String tenNguoidung, String matKhau) {
        this.maNguoidung = maNguoidung;
        this.tenNguoidung = tenNguoidung;
        this.matKhau = matKhau;
    }

    public String getMaNguoidung() {
        return maNguoidung;
    }

    public void setMaNguoidung(String maNguoidung) {
        this.maNguoidung = maNguoidung;
    }

    public String getTenNguoidung() {
        return tenNguoidung;
    }

    public void setTenNguoidung(String tenNguoidung) {
        this.tenNguoidung = tenNguoidung;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
