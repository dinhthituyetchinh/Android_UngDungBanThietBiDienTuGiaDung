package com.example.androidmobile.model;

public class ThongBaoModel {
    private int matb;
    private String thongtin;
    private String makh;

    public ThongBaoModel(int matb, String thongtin, String makh) {
        this.matb = matb;
        this.thongtin = thongtin;
        this.makh = makh;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public int getMatb() {
        return matb;
    }

    public ThongBaoModel(int matb, String thongtin) {
        this.matb = matb;
        this.thongtin = thongtin;
    }

    public void setMatb(int matb) {
        this.matb = matb;
    }

    public ThongBaoModel(String thongtin) {
        this.thongtin = thongtin;
    }

    public String getThongtin() {
        return thongtin;
    }

    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }
}
