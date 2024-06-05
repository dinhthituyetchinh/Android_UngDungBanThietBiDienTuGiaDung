package com.example.androidmobile.model;

public class KhachHangModel {
    private String username;
    private String passwork;
    private String ten;
    private String sdt;
    private String diachi;

    public String getUsername() {
        return username;
    }

    public KhachHangModel() {
    }

    public KhachHangModel(String username, String ten, String passwork, String sdt, String diachi) {
        this.username = username;
        this.passwork = passwork;
        this.ten = ten;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswork() {
        return passwork;
    }

    public void setPasswork(String passwork) {
        this.passwork = passwork;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
