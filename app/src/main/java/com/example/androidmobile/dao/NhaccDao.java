package com.example.androidmobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidmobile.database.DatabaseHelper;
import com.example.androidmobile.model.NhaCungCapModel;

import java.util.ArrayList;

public class NhaccDao {
    DatabaseHelper dbHelper ;


    public NhaccDao(Context context){
        dbHelper= new DatabaseHelper(context);
    }

    public ArrayList<NhaCungCapModel> getds(){
        ArrayList<NhaCungCapModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from nhacc", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new NhaCungCapModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));

            } while ((cursor.moveToNext()));
        }
        return list;

    }

    public boolean themnhacc(String ten, String email, String sdt) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("tennhacc", ten);
        contentValues.put("email", email);
        contentValues.put("sdt", sdt);

        long check = sqLiteDatabase.insert("nhacc", null, contentValues);
        if (check == -1)
            return false;
        return true;

    }


    public boolean capnhapnhacc(int manhacc, String ten, String email, String sdt) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("manhacc", manhacc);
        contentValues.put("tennhacc", ten);
        contentValues.put("email", email);
        contentValues.put("sdt", sdt);
        long check = sqLiteDatabase.update("nhacc", contentValues, "manhacc=?", new String[]{String.valueOf(manhacc)});
        if (check == -1)
            return false;
        return true;

    }


    public int xoanhacc(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from sanpham where manhacc=? ", new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = sqLiteDatabase.delete("nhacc", "manhacc=?", new String[]{String.valueOf(id)});
        if (check == -1)
            return 0;
        return 1;


    }


    public long delete(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("nhacc", "mamnhacc = ?", new String[]{String.valueOf(id)});
    }

}
