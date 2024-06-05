package com.example.androidmobile.model;

public class SanPhamModel {
    private int masp;
    private String ten;
    private int gia;
    private String loai;
    private String motasp;
    private int manhacc;

    public SanPhamModel() {
    }

    public SanPhamModel(int masp, String ten, int gia, String loai, String motasp, int manhacc) {
        this.masp = masp;
        this.ten = ten;
        this.gia = gia;
        this.loai = loai;
        this.motasp = motasp;
        this.manhacc = manhacc;
    }

    public String getMotasp() {
        return motasp;
    }

    public void setMotasp(String motasp) {
        this.motasp = motasp;
    }

    public int getManhacc() {
        return manhacc;
    }

    public void setManhacc(int manhacc) {
        this.manhacc = manhacc;
    }

    public int getMasp() {
        return masp;
    }

    public SanPhamModel(int masp, String ten, int gia, String loai) {
        this.masp = masp;
        this.ten = ten;
        this.gia = gia;
        this.loai = loai;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public SanPhamModel(String ten, int gia, String loai) {
        this.ten = ten;
        this.gia = gia;
        this.loai = loai;
    }
}
