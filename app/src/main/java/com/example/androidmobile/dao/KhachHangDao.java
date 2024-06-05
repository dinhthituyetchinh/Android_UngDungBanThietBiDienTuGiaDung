package com.example.androidmobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidmobile.database.DatabaseHelper;
import com.example.androidmobile.model.KhachHangModel;

import java.util.ArrayList;

public class KhachHangDao {
    DatabaseHelper dbHelper ;

    public KhachHangDao(Context context){
        dbHelper= new DatabaseHelper(context);
    }
    public ArrayList<KhachHangModel> getds(){
        ArrayList<KhachHangModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select*from khachhang",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                //String username, String passwork, String ten, String sdt, String diachi
                list.add( new KhachHangModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));

            }while ((cursor.moveToNext()));
        }
        return  list;
    }

    public boolean themkhachhang(String usrname, String ten, String pass, String sdt, String diachi) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        //username text primary key ,hoten text,password text,sdt text,diachi text
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", usrname);
        contentValues.put("hoten", ten);
        contentValues.put("password", pass);
        contentValues.put("sdt", sdt);
        contentValues.put("diachi", diachi);

        long check = sqLiteDatabase.insert("khachhang", null, contentValues);
        if(check==-1)
            return false;
        return true;

    }


    public  boolean capnhapkhachhang(String usrname,String pass,String ten,String sdt, String  diachi){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username",usrname);
        contentValues.put("hoten",ten);
        contentValues.put("password",pass);
        contentValues.put("sdt",sdt);
        contentValues.put("diachi", diachi);
        long check = sqLiteDatabase.update("khachhang", contentValues, "username=?", new String[]{String.valueOf(usrname)});
        if (check == -1)
            return false;
        return true;


    }

    public int xoakhachang(String username) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from hoadon where makh=? ", new String[]{String.valueOf(username)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = sqLiteDatabase.delete("khachhang", "username=?", new String[]{String.valueOf(username)});
        if (check == -1)
            return 0;
        return 1;


    }


    public long delete(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("khachhang", "username = ?", new String[]{String.valueOf(id)});
    }


    public KhachHangModel getCustomerByUsername(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM khachhang WHERE username = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            KhachHangModel customer = new KhachHangModel();
            customer.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            customer.setTen(cursor.getString(cursor.getColumnIndex("hoten")));
            customer.setPasswork(cursor.getString(cursor.getColumnIndex("password")));
            customer.setSdt(cursor.getString(cursor.getColumnIndex("sdt")));
            customer.setDiachi(cursor.getString(cursor.getColumnIndex("diachi")));
            cursor.close();
            return customer;
        } else {
            cursor.close();
            return null;
        }
    }

}
