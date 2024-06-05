package com.example.androidmobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidmobile.database.DatabaseHelper;
import com.example.androidmobile.model.SanPhamModel;

import java.util.ArrayList;

public class SanPhamGH_dao {
    DatabaseHelper dbHelper ;
    public SanPhamGH_dao(Context context){
        dbHelper= new DatabaseHelper(context);
    }
    public ArrayList<SanPhamModel> getds(){
        ArrayList<SanPhamModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select*from sanphamgh",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                list.add( new SanPhamModel(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5)));

            }while ((cursor.moveToNext()));
        }
        return  list;
    }


    public boolean themVaoGioHang(int masp,String ten ,int gia,String loai,String mota,int manhacc){
        SQLiteDatabase sqLiteDatabase= dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("masp",masp);
        contentValues.put("tensp",ten);
        contentValues.put("giasp",gia);
        contentValues.put("loaisp",loai);
        contentValues.put("motasp",mota);
        contentValues.put("manhacc",manhacc);
        long check = sqLiteDatabase.insert("sanphamgh",null,contentValues);
        if(check==-1)
            return false;
        return true;

    }


    public  boolean capnhap(int masp,String ten ,int gia,String loai,String mota,int manhacc){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp",ten);
        contentValues.put("giasp",gia);
        contentValues.put("loaisp",loai);
        contentValues.put("motasp",mota);
        contentValues.put("nhacc",manhacc);
        long check = sqLiteDatabase.update("sanphamgh",contentValues,"masp=?",new String[]{String.valueOf(masp)});
        if(check==-1)
            return  false;
        return true;



    }

    public long delete(int id) {
        SQLiteDatabase sqLiteDatabase= dbHelper.getWritableDatabase();
        return sqLiteDatabase.delete("sanphamgh", "masp = ?", new String[]{String.valueOf(id)});
    }




}


