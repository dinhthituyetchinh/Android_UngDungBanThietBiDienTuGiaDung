package com.example.androidmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidmobile.adapter.SanPhamGH_Adapter;
import com.example.androidmobile.dao.HoaDonDao1;
import com.example.androidmobile.dao.SanPhamGH_dao;
import com.example.androidmobile.model.SanPhamModel;

import java.util.ArrayList;

public class GiaoDienGioHang extends AppCompatActivity {

    ArrayList<SanPhamModel>listgiohang;
    SanPhamGH_dao dao;
    SanPhamGH_Adapter adapter;
    RecyclerView rc;
    HoaDonDao1 daohd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_gio_hang);
        daohd1 = new HoaDonDao1(this);
        rc = findViewById(R.id.rcviewgiohang);
        dao = new SanPhamGH_dao(this);
        listgiohang = dao.getds();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        rc.setLayoutManager(layoutManager);
        adapter= new SanPhamGH_Adapter(this,listgiohang,dao);
        rc.setAdapter(adapter);


        ImageButton img = findViewById(R.id.ibtback_GH);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GiaoDienGioHang.this, Activity_khachhang.class);
                startActivity(intent);
            }
        });

        TextView txttongtien = findViewById(R.id.txttongtien_tt);

        int tien = 0;
        for (SanPhamModel sp : listgiohang) {
            tien += sp.getGia();
        }
        txttongtien.setText("" + tien);

        TextView txtdathang = findViewById(R.id.txtdathang);
        txtdathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GiaoDienGioHang.this, GiaoDienThanhToan_nhieusp.class);
                startActivity(intent);

                SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                String username = preferences.getString("username", "");
                String tien = txttongtien.getText().toString();
                boolean check = daohd1.addHoaDon(username, Integer.parseInt(tien));
                if (check) {
                    Toast.makeText(GiaoDienGioHang.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GiaoDienGioHang.this, "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }
}