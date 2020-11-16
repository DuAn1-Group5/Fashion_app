package com.example.fashion_app.model;

public class GiamGia {
    private String maGiamgia;
    private String ngayBatdau;
    private String ngayKetthuc;
    private String soTiengiamgia;
    private String maHoadon;


    public GiamGia(String maGiamgia, String ngayBatdau, String ngayKetthuc, String soTiengiamgia, String maHoadon) {
        this.maGiamgia = maGiamgia;
        this.ngayBatdau = ngayBatdau;
        this.ngayKetthuc = ngayKetthuc;
        this.soTiengiamgia = soTiengiamgia;
        this.maHoadon = maHoadon;
    }

    public String getMaGiamgia() {
        return maGiamgia;
    }

    public void setMaGiamgia(String maGiamgia) {
        this.maGiamgia = maGiamgia;
    }

    public String getNgayBatdau() {
        return ngayBatdau;
    }

    public void setNgayBatdau(String ngayBatdau) {
        this.ngayBatdau = ngayBatdau;
    }

    public String getNgayKetthuc() {
        return ngayKetthuc;
    }

    public void setNgayKetthuc(String ngayKetthuc) {
        this.ngayKetthuc = ngayKetthuc;
    }

    public String getSoTiengiamgia() {
        return soTiengiamgia;
    }

    public void setSoTiengiamgia(String soTiengiamgia) {
        this.soTiengiamgia = soTiengiamgia;
    }

    public String getMaHoadon() {
        return maHoadon;
    }

    public void setMaHoadon(String maHoadon) {
        this.maHoadon = maHoadon;
    }
}
