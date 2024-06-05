package com.example.androidmobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidmobile.database.DatabaseHelper;
import com.example.androidmobile.model.HoaDonModel;

import java.util.ArrayList;

public class HoaDonDao {
    DatabaseHelper dbHelper;

    public HoaDonDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public ArrayList<HoaDonModel> getds() {
        ArrayList<HoaDonModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from hoadon", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new HoaDonModel(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
            } while ((cursor.moveToNext()));
        }
        return list;
    }

    public boolean themhoadon(String makh, int masp, int tienhoadon) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("makh", makh);
        contentValues.put("masp", masp);
        contentValues.put("tienhoadon", tienhoadon);

        long check = sqLiteDatabase.insert("hoadon", null, contentValues);
        if (check == -1)
            return false;
        return true;

    }


    public boolean capnhaphoadon(int mahd, String makh, int masp, int tienhoadon) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("mahd", mahd);
        contentValues.put("makh", makh);
        contentValues.put("masp", masp);
        contentValues.put("tienhoadon", tienhoadon);
        long check = sqLiteDatabase.update("hoadon", contentValues, "mahd=?", new String[]{String.valueOf(mahd)});
        if (check == -1)
            return false;
        return true;


    }


    public long delete(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("hoadon", "mahd = ?", new String[]{String.valueOf(id)});
    }


}
