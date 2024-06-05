package com.example.androidmobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidmobile.database.DatabaseHelper;
import com.example.androidmobile.model.ChiTietHoaDon;

import java.util.ArrayList;

public class ChiTietHoaDonDao {
    private DatabaseHelper dbHelper;

    public ChiTietHoaDonDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean addChiTietHoaDon(int maHoaDon, int maSanPham, int giaSanPham) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("mahd", maHoaDon);
        values.put("masp", maSanPham);
        values.put("giasp", giaSanPham);

        long check = db.insert("chitiethd", null, values);
        if (check == -1)
            return false;
        return true;
    }


    public ChiTietHoaDon getCustomerByUsername(String mahd) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM chitiethd WHERE mahd = ?";
        String[] selectionArgs = {mahd};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            ChiTietHoaDon customer = new ChiTietHoaDon();
            customer.setMact(Integer.parseInt(cursor.getString(cursor.getColumnIndex("macthd"))));
            customer.setMahd(Integer.parseInt(cursor.getString(cursor.getColumnIndex("mahd"))));
            customer.setMasp(Integer.parseInt(cursor.getString(cursor.getColumnIndex("masp"))));
            customer.setGiasp(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giasp"))));
            cursor.close();
            return customer;
        } else {
            cursor.close();
            return null;
        }
    }

    public ArrayList<ChiTietHoaDon> getds() {
        ArrayList<ChiTietHoaDon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from chitiethd", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new ChiTietHoaDon(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3)));
            } while ((cursor.moveToNext()));
        }
        return list;
    }


    public long delete(String mahd) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("chitiethd", "mahd = ?", new String[]{String.valueOf(mahd)});
    }

}
