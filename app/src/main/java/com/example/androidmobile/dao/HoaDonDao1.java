package com.example.androidmobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidmobile.database.DatabaseHelper;
import com.example.androidmobile.model.HoaDon1;

import java.util.ArrayList;

public class HoaDonDao1 {
    private DatabaseHelper dbHelper;

    public HoaDonDao1(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean addHoaDon(String makh, int tienHoaDon) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("makh", makh);
        contentValues.put("tienhoadon", tienHoaDon);

        long check = sqLiteDatabase.insert("hoadon1", null, contentValues);
        if (check == -1)
            return false;
        return true;

    }

    public ArrayList<HoaDon1> getds() {
        ArrayList<HoaDon1> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from hoadon1", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new HoaDon1(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            } while ((cursor.moveToNext()));
        }
        return list;
    }


    public long delete(String mahd) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("hoadon1", "mahd = ?", new String[]{String.valueOf(mahd)});
    }


}


