package com.example.fashion_app.model;

public class NguoiDung {
    private String maNguoidung;
    private String tenNguoidung;
    private String email;
    private String chucvu;
    private String matKhau;


    public NguoiDung() {
    }

    public NguoiDung(String maNguoidung, String tenNguoidung, String email, String chucvu, String matKhau) {
        this.maNguoidung = maNguoidung;
        this.tenNguoidung = tenNguoidung;
        this.email = email;
        this.chucvu = chucvu;
        this.matKhau = matKhau;
    }

    public NguoiDung(String tenNguoidung, String email, String chucvu, String matKhau) {
        this.tenNguoidung = tenNguoidung;
        this.email = email;
        this.chucvu = chucvu;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
