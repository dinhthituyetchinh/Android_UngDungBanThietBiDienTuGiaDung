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
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidmobile.adapter.SanPhamGH_Adapter;
import com.example.androidmobile.dao.ChiTietHoaDonDao;
import com.example.androidmobile.dao.HoaDonDao1;
import com.example.androidmobile.dao.KhachHangDao;
import com.example.androidmobile.dao.SanPhamGH_dao;
import com.example.androidmobile.dao.ThongBaoDao;
import com.example.androidmobile.model.HoaDon1;
import com.example.androidmobile.model.KhachHangModel;
import com.example.androidmobile.model.SanPhamModel;

import java.util.ArrayList;

public class GiaoDienThanhToan_nhieusp extends AppCompatActivity {
    private KhachHangDao dao;
    private HoaDonDao1 daohd;


    SanPhamGH_dao daogh;
    ArrayList<SanPhamModel> listgiohang;

    SanPhamGH_Adapter adapterGH;
    ArrayList<HoaDon1> listhd;


    ChiTietHoaDonDao daocthd;


    ThongBaoDao daotb;
    RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_thanh_toan_nhieusp);

        daocthd = new ChiTietHoaDonDao(this);


        rc = findViewById(R.id.rcdanhsachspgiohang);
        daogh = new SanPhamGH_dao(this);
        listgiohang = daogh.getds();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        rc.setLayoutManager(layoutManager);
        adapterGH = new SanPhamGH_Adapter(this, listgiohang, daogh);
        rc.setAdapter(adapterGH);


        daotb = new ThongBaoDao(this);

        daohd = new HoaDonDao1(this);


        dao = new KhachHangDao(this);

        //ánh xạ
        TextView txttekh, txtsdtkh, txtdiachikh, txttensp, txtgiap, txttongtien;

        txttekh = findViewById(R.id.txttenkh_thanhtoan);
        txtsdtkh = findViewById(R.id.txtsdt_thanhtoan);
        txtdiachikh = findViewById(R.id.txtdiachi_thanhtoan);

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
                int gia = 0;
                for (SanPhamModel sp : listgiohang) {
                    gia += sp.getGia();

                    txtgiahang.setText("" + gia);

                    int tongtiten = Integer.parseInt(txtgiavc.getText().toString()) + Integer.parseInt(txtgiahang.getText().toString());
                    txttienthanhtoan.setText("" + tongtiten);

                    txttongtien.setText("" + tongtiten);
                }


            }
        });
        rdonhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtgiavc = findViewById(R.id.txtgiavc);
                TextView txtgiahang = findViewById(R.id.txtgiahang);
                TextView txttienthanhtoan = findViewById(R.id.txttienthanhtoan);


                txtgiavc.setText("30000");
                int gia = 0;
                for (SanPhamModel sp : listgiohang) {
                    gia += sp.getGia();

                    txtgiahang.setText("" + gia);

                    int tongtiten = Integer.parseInt(txtgiavc.getText().toString()) + Integer.parseInt(txtgiahang.getText().toString());
                    txttienthanhtoan.setText("" + tongtiten);

                    txttongtien.setText("" + tongtiten);
                }


            }
        });
        rdohoatoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtgiavc = findViewById(R.id.txtgiavc);
                TextView txtgiahang = findViewById(R.id.txtgiahang);
                TextView txttienthanhtoan = findViewById(R.id.txttienthanhtoan);


                txtgiavc.setText("50000");
                int gia = 0;
                for (SanPhamModel sp : listgiohang) {
                    gia += sp.getGia();

                    txtgiahang.setText("" + gia);

                    int tongtiten = Integer.parseInt(txtgiavc.getText().toString()) + Integer.parseInt(txtgiahang.getText().toString());
                    txttienthanhtoan.setText("" + tongtiten);

                    txttongtien.setText("" + tongtiten);
                }


            }
        });


        TextView txtdathang = findViewById(R.id.txtdathang);
        txtdathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                listhd = daohd.getds();


                if (txttongtien.getText().toString().equals("0")) {
                    Toast.makeText(GiaoDienThanhToan_nhieusp.this, "Vui lòng chọn phương thức vận chuyển", Toast.LENGTH_SHORT).show();
                } else {
                    for (SanPhamModel sp : listgiohang) {

                        int mahd = Integer.MIN_VALUE;
                        for (HoaDon1 hd : listhd) {
                            if (hd.getMahd() > mahd) {
                                mahd = hd.getMahd();
                            }
                        }

                        int masp = sp.getMasp();
                        int giasp = sp.getGia();
                        boolean check = daocthd.addChiTietHoaDon(mahd, masp, giasp);

                        if (check) {
                            Toast.makeText(GiaoDienThanhToan_nhieusp.this, "Đặt hàng thành công" + mahd, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(GiaoDienThanhToan_nhieusp.this, Activity_khachhang.class);
                            startActivity(intent);
                            for (SanPhamModel spgh : listgiohang) {
                                int check1 = spgh.getMasp();
                                daogh.delete(check1);
                            }
                        } else {
                            Toast.makeText(GiaoDienThanhToan_nhieusp.this, "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();

                        }


                    }

                }

            }
        });
        RelativeLayout layout = findViewById(R.id.layout_dc);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(GiaoDienThanhToan_nhieusp.this, ThongTinKhachHangChiTiet.class);
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