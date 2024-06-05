package com.example.androidmobile.model;

public class ChiTietHoaDon {
    private int mact;
    private int mahd;
    private int masp;
    private int giasp;

    public ChiTietHoaDon(int mact, int mahd, int masp, int giasp) {
        this.mact = mact;
        this.mahd = mahd;
        this.masp = masp;
        this.giasp = giasp;
    }

    public ChiTietHoaDon() {
    }

    public int getMact() {
        return mact;
    }

    public void setMact(int mact) {
        this.mact = mact;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }
}
