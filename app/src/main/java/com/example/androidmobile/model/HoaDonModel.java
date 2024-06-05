package com.example.androidmobile.model;

public class HoaDonModel {
    //mahd ,
    private int mahd;
    private String makh;
    private int masp;
    private int tienhoadon;

    public HoaDonModel(int mahd, String makh, int masp, int tienhoadon) {
        this.mahd = mahd;
        this.makh = makh;
        this.masp = masp;
        this.tienhoadon = tienhoadon;
    }

    public int getTienhoadon() {
        return tienhoadon;
    }

    public void setTienhoadon(int tienhoadon) {
        this.tienhoadon = tienhoadon;
    }

    public HoaDonModel() {
    }

    public HoaDonModel(int mahd, String makh, int masp) {
        this.mahd = mahd;
        this.makh = makh;
        this.masp = masp;
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

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }
}
