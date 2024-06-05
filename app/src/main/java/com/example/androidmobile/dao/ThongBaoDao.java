package com.example.androidmobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidmobile.database.DatabaseHelper;
import com.example.androidmobile.model.ThongBaoModel;

import java.util.ArrayList;

public class ThongBaoDao {
    DatabaseHelper dbHelper;

    public ThongBaoDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public ArrayList<ThongBaoModel> getds() {
        ArrayList<ThongBaoModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from thongbao", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new ThongBaoModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while ((cursor.moveToNext()));
        }
        return list;
    }


    public boolean themthongbao(String thongtin, String makh) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("thongtin", thongtin);
        contentValues.put("makh", makh);


        long check = sqLiteDatabase.insert("thongbao", null, contentValues);
        if (check == -1)
            return false;
        return true;

    }
}
