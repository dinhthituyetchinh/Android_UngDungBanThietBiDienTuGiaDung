package com.example.androidmobile.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.androidmobile.R;
import com.example.androidmobile.adapter.SanPhamAdapter;
import com.example.androidmobile.dao.SanPhamDAO;
import com.example.androidmobile.model.SanPhamModel;

import java.util.ArrayList;

public class fragment_sanphamloai extends AppCompatActivity {
    SanPhamDAO dao;
    RecyclerView rc;
    SanPhamAdapter adapter;
    ArrayList<SanPhamModel> list;
    ArrayList<SanPhamModel> listphanloai = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_sanphamloai);

        ImageButton imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView txtnav = findViewById(R.id.txtnav);
        Intent intent = getIntent();
        String loai = intent.getStringExtra("loai");
        if (loai.equals("panasonic_hp")) {
            txtnav.setText("Sản phẩm máy lạnh");
        }
        if (loai.equals("toshiba_mk")) {
            txtnav.setText("Sản phẩm máy giặt");
        }
        if (loai.equals("panasonic_nr")) {
            txtnav.setText("Sản phẩm tủ lạnh");
        }
        if (loai.equals("toshiba_rc")) {
            txtnav.setText("Sản phẩm nồi cơm");
        }
        if (loai.equals("philips_hr") || loai.equals("panasonic_mxmg")) {
            txtnav.setText("Sản phẩm máy xay sinh tố");
        }
        rc = findViewById(R.id.rcsanphamloai);
        dao = new SanPhamDAO(this);
        list = dao.getds();
        for (SanPhamModel sp : list) {
            if (sp.getLoai().equals(loai)) {
                listphanloai.add(sp);
            }

        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rc.setLayoutManager(layoutManager);
        adapter = new SanPhamAdapter(this, listphanloai, dao);
        rc.setAdapter(adapter);

    }
}