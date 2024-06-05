package com.example.androidmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidmobile.adapter.SanPhamGH_Adapter;
import com.example.androidmobile.adapter.SanPhamYT_Adapter;
import com.example.androidmobile.dao.SanPhamDAO;
import com.example.androidmobile.dao.SanPhamGH_dao;
import com.example.androidmobile.dao.SanPhamYT_Dao;
import com.example.androidmobile.dao.ThongBaoDao;
import com.example.androidmobile.model.SanPhamModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class GiaoDienChiTietSP extends AppCompatActivity {
ArrayList<SanPhamModel> list;
    SanPhamGH_dao daoGH;
    SanPhamYT_Dao daoYT;


    SanPhamDAO dao;
    SanPhamGH_Adapter adapter;
    SanPhamYT_Adapter adapter1;
    ArrayList<SanPhamModel> listgiohang;
    ArrayList<SanPhamModel> listyeuthich;


    ThongBaoDao daotb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_chi_tiet_sp);
        adapter = new SanPhamGH_Adapter(this, listgiohang, daoGH);
        adapter1 = new SanPhamYT_Adapter(this, listyeuthich, daoYT);

        dao = new SanPhamDAO(this);
        daoGH = new SanPhamGH_dao(this);
        daoYT = new SanPhamYT_Dao(this);
        daotb = new ThongBaoDao(this);

        listyeuthich=daoYT.getds();
        listgiohang=daoGH.getds();
        list=dao.getds();


        ImageButton imgbtTrove = findViewById(R.id.imgbttv);
        imgbtTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        Intent intent = getIntent();
        String masp = intent.getStringExtra("masp");
        String ten = intent.getStringExtra("ten");
        String gia = intent.getStringExtra("gia");
        String loai = intent.getStringExtra("loai");
        String mota = intent.getStringExtra("mota");
        String manhacc= intent.getStringExtra("manhacc");

        // Hiển thị dữ liệu trong Activity
        TextView tenTextView = findViewById(R.id.txttensp_ct);
        tenTextView.setText(mota);

        TextView giaLoaiTextView = findViewById(R.id.txtgiasp_ct);
        giaLoaiTextView.setText("đ"+gia);




        ImageView img = findViewById(R.id.imgsanpham_chitiet);
        if (loai.equals("panasonic_hp")) {
            Picasso.get().load(R.mipmap.img_panasonic_hp).resize(130, 110).into(img);
        } else if (loai.equals("toshiba_mk")) {
            Picasso.get().load(R.mipmap.img_toshiba_mk).resize(130, 110).centerCrop().into(img);
        } else if (loai.equals("panasonic_nr")) {
            Picasso.get().load(R.mipmap.img_panasonic_nr).resize(130, 110).into(img);
        } else if (loai.equals("toshiba_rc")) {
            Picasso.get().load(R.mipmap.img_toshiba_rc).resize(130, 110).into(img);
        } else if (loai.equals("toshiba_tl")) {
            Picasso.get().load(R.mipmap.img_toshiba_tl).resize(130, 110).into(img);
        } else if (loai.equals("philips_hr")) {
            Picasso.get().load(R.mipmap.img_mayxay_hr).resize(130, 110).into(img);
        } else if (loai.equals("panasonic_mxmg")) {
            Picasso.get().load(R.mipmap.img_mayxay_hr).resize(130, 110).into(img);
        } else if (loai.equals("elmich_smmn")) {
            Picasso.get().load(R.mipmap.img_elmich_smmn).resize(130, 110).into(img);
        } else if (loai.equals("elmich_elya")) {
            Picasso.get().load(R.mipmap.img_elmich_elya).resize(130, 110).into(img);
        }

        ImageButton imgbt_giohang= findViewById(R.id.imgbt_giohang_ct);

        imgbt_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean check =daoGH.themVaoGioHang(Integer.parseInt(masp),ten,Integer.parseInt(gia), loai,mota, Integer.parseInt(manhacc));

        if(check) {
            Toast.makeText(GiaoDienChiTietSP.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();


            String tb = "Bạn đã thêm sản phẩm " + mota + " vào giỏ hàng";
            SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            String username = preferences.getString("username", "");

            boolean check1 = daotb.themthongbao(tb, username);

            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(GiaoDienChiTietSP.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();

        }
            }
        });


        ImageView imgyeuthich=  findViewById(R.id.imgbt_yeuthich_ct);
        final boolean[] isRed = {false};




        imgyeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isRed[0]) {
                    Picasso.get().load(R.drawable.img_love).resize(40, 40).into(imgyeuthich);

                } else {
                    Picasso.get().load(R.drawable.icon_yeuthich).resize(40, 40).into(imgyeuthich);

                    boolean check =daoYT.themVaoYeuthich(Integer.parseInt(masp),ten,Integer.parseInt(gia), loai,mota, Integer.parseInt(manhacc));

                    if(check) {
                        Toast.makeText(GiaoDienChiTietSP.this, "Thêm vào danh sách yêu thích thành công", Toast.LENGTH_SHORT).show();

                        String tb = "Bạn đã thêm sản phẩm " + mota + " vào trang yêu thích";
                        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                        String username = preferences.getString("username", "");

                        boolean check1 = daotb.themthongbao(tb, username);


                        adapter1.notifyDataSetChanged();
                    } else {
                        Toast.makeText(GiaoDienChiTietSP.this, "Sản phẩm đã có trong danh sách yêu thích", Toast.LENGTH_SHORT).show();

                    }
                }
                isRed[0] = !isRed[0];
            }
        });

        TextView txtmuahang = findViewById(R.id.txtmuahang_ct);
        txtmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(GiaoDienChiTietSP.this, GiaoDienThanhToan.class);
                intent1.putExtra("masp", masp);
                intent1.putExtra("mota", mota);
                intent1.putExtra("gia", gia);
                intent1.putExtra("loai", loai);


                startActivity(intent1);
            }
        });


    }
}