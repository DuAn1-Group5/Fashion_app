package com.example.fashion_app.model;

public class DanhGia {
    private String camXuc;
    private String binhLuan;
    private String hoTenDayDu;
    private String email;

    public DanhGia(String camXuc, String binhLuan, String hoTenDayDu, String email) {
        this.camXuc = camXuc;
        this.binhLuan = binhLuan;
        this.hoTenDayDu = hoTenDayDu;
        this.email = email;
    }

    public DanhGia() {
    }

    public String getCamXuc() {
        return camXuc;
    }

    public void setCamXuc(String camXuc) {
        this.camXuc = camXuc;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public String getHoTenDayDu() {
        return hoTenDayDu;
    }

    public void setHoTenDayDu(String hoTenDayDu) {
        this.hoTenDayDu = hoTenDayDu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


