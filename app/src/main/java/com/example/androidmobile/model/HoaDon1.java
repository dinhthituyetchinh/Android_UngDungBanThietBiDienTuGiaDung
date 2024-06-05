package com.example.androidmobile.model;

public class HoaDon1 {
    private int mahd;
    private String makh;
    private int tongtien;

    public HoaDon1(int mahd, String makh, int tongtien) {
        this.mahd = mahd;
        this.makh = makh;
        this.tongtien = tongtien;
    }

    public HoaDon1() {
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
}
