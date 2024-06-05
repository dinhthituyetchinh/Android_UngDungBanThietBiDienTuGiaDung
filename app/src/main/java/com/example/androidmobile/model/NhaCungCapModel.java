package com.example.androidmobile.model;

public class NhaCungCapModel {
    private int manhacc;
    private String tennhacc;
    private String email;
    private String sdt;

    public NhaCungCapModel() {
    }

    public NhaCungCapModel(int manhacc, String tennhacc, String email, String sdt) {
        this.manhacc = manhacc;
        this.tennhacc = tennhacc;
        this.email = email;
        this.sdt = sdt;
    }

    public int getManhacc() {
        return manhacc;
    }

    public void setManhacc(int manhacc) {
        this.manhacc = manhacc;
    }

    public String getTennhacc() {
        return tennhacc;
    }

    public void setTennhacc(String tennhacc) {
        this.tennhacc = tennhacc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
