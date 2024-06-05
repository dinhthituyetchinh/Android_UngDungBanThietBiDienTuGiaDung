package com.example.androidmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidmobile.dao.ChiTietHoaDonDao;
import com.example.androidmobile.dao.HoaDonDao1;
import com.example.androidmobile.dao.KhachHangDao;
import com.example.androidmobile.dao.ThongBaoDao;
import com.example.androidmobile.model.HoaDon1;
import com.example.androidmobile.model.KhachHangModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GiaoDienThanhToan extends AppCompatActivity {
    private KhachHangDao dao;
    private HoaDonDao1 daohd;

    ThongBaoDao daotb;
    ArrayList<HoaDon1> lishd;
    ChiTietHoaDonDao daocthd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_thanh_toan);
        daotb = new ThongBaoDao(this);

        daohd = new HoaDonDao1(this);


        dao = new KhachHangDao(this);
        daocthd = new ChiTietHoaDonDao(this);

        //ánh xạ
        TextView txttekh, txtsdtkh, txtdiachikh, txttensp, txtgiap, txttongtien;

        txttekh = findViewById(R.id.txttenkh_thanhtoan);
        txtsdtkh = findViewById(R.id.txtsdt_thanhtoan);
        txtdiachikh = findViewById(R.id.txtdiachi_thanhtoan);
        txttensp = findViewById(R.id.txtten);
        txtgiap = findViewById(R.id.txtgia);
        txttongtien = findViewById(R.id.txttongtien_tt);


        // Truy xuất dữ liệu của một khách hàng dựa trên mã username
        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");
        KhachHangModel customer = dao.getCustomerByUsername(username);
        if (customer != null) {
            // Dữ liệu đã được truy xuất thành công
            String ten = customer.getTen();
            String pass = customer.getPasswork();
            String sdt = customer.getSdt();
            String diachi = customer.getDiachi();
            // Tiếp tục xử lý dữ liệu
            txttekh.setText(ten + "  |");
            txtsdtkh.setText(sdt);
            txtdiachikh.setText(diachi);
        }

        Intent intent = getIntent();
        String masp = intent.getStringExtra("masp");
        String tensp = intent.getStringExtra("mota");
        String giasp = intent.getStringExtra("gia");
        String loai = intent.getStringExtra("loai");

        txttensp.setText(tensp);
        txtgiap.setText(giasp);
        ImageView img = findViewById(R.id.imgsanpham);
        if (loai == null) {
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

        RadioButton rdobt, rdonhanh, rdohoatoc;
        rdobt = findViewById(R.id.rdobt);
        rdonhanh = findViewById(R.id.rdonhanh);
        rdohoatoc = findViewById(R.id.rdohoatoc);

        rdobt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtgiavc = findViewById(R.id.txtgiavc);
                TextView txtgiahang = findViewById(R.id.txtgiahang);
                TextView txttienthanhtoan = findViewById(R.id.txttienthanhtoan);

                txtgiavc.setText("20000");
                String gia = txtgiap.getText().toString();
                txtgiahang.setText(gia);
                int tongtiten = Integer.parseInt(txtgiavc.getText().toString()) + Integer.parseInt(txtgiahang.getText().toString());
                txttienthanhtoan.setText("" + tongtiten);
                txttongtien.setText("" + tongtiten);


            }
        });
        rdonhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtgiavc = findViewById(R.id.txtgiavc);
                TextView txtgiahang = findViewById(R.id.txtgiahang);
                TextView txttienthanhtoan = findViewById(R.id.txttienthanhtoan);

                txtgiavc.setText("30000");
                String gia = txtgiap.getText().toString();
                txtgiahang.setText(gia);
                int tongtiten = Integer.parseInt(txtgiavc.getText().toString()) + Integer.parseInt(txtgiahang.getText().toString());
                txttienthanhtoan.setText("" + tongtiten);
                txttongtien.setText("" + tongtiten);


            }
        });
        rdohoatoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtgiavc = findViewById(R.id.txtgiavc);
                TextView txtgiahang = findViewById(R.id.txtgiahang);
                TextView txttienthanhtoan = findViewById(R.id.txttienthanhtoan);

                txtgiavc.setText("50000");
                String gia = txtgiap.getText().toString();
                txtgiahang.setText(gia);
                int tongtiten = Integer.parseInt(txtgiavc.getText().toString()) + Integer.parseInt(txtgiahang.getText().toString());
                txttienthanhtoan.setText("" + tongtiten);

                txttongtien.setText("" + tongtiten);

            }
        });


        TextView txtdathang = findViewById(R.id.txtdathang);
        txtdathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tien = txttongtien.getText().toString();
                boolean check = daohd.addHoaDon(username, Integer.valueOf(tien));
                if (txttongtien.getText().toString().equals("0")) {
                    Toast.makeText(GiaoDienThanhToan.this, "Bạn chưa chọn phương thức vận chuyển", Toast.LENGTH_SHORT).show();
                } else {
                    if (check) {
                        Toast.makeText(GiaoDienThanhToan.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                        Intent inten = new Intent(GiaoDienThanhToan.this, Activity_khachhang.class);
                        startActivity(inten);

                        String tb = "Bạn đã đặt sản phẩm " + txttensp.getText().toString() + "thành công";
                        boolean check1 = daotb.themthongbao(tb, username);

                    } else {
                        Toast.makeText(GiaoDienThanhToan.this, "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();

                    }
                }

                lishd = daohd.getds();
                int mahd = Integer.MIN_VALUE;
                for (HoaDon1 hd : lishd) {
                    if (hd.getMahd() > mahd) {
                        mahd = hd.getMahd();
                    }
                }

                boolean check1 = daocthd.addChiTietHoaDon(mahd, Integer.valueOf(masp), Integer.valueOf(giasp));


            }
        });

        RelativeLayout layout = findViewById(R.id.layout_dc);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(GiaoDienThanhToan.this, ThongTinKhachHangChiTiet.class);
                startActivity(intent1);
            }
        });


        ImageButton imgback = findViewById(R.id.ibtback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}