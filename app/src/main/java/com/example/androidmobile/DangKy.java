package com.example.androidmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidmobile.dao.KhachHangDao;
import com.example.androidmobile.model.KhachHangModel;

import java.util.ArrayList;

public class DangKy extends AppCompatActivity {
    KhachHangDao dao;

    ArrayList<KhachHangModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        dao = new KhachHangDao(this);
        list = dao.getds();

        EditText edtten, edtsdt, edtdiachi, edtpss, edtmatk;

        edtten = findViewById(R.id.edtten_ttctkh);
        edtsdt = findViewById(R.id.edtsdt_ttctkh);
        edtdiachi = findViewById(R.id.edtdc_ttctkh);
        edtpss = findViewById(R.id.edtpass_ttctkh);
        edtmatk = findViewById(R.id.edtusername_ttctkh);

        Button btnthem, btnhuy;
        btnthem = findViewById(R.id.btnthemkh);
        btnhuy = findViewById(R.id.btnhuy);


        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matk = edtmatk.getText().toString();
                String ten = edtten.getText().toString();
                String sdt = edtsdt.getText().toString();
                String diachi = edtdiachi.getText().toString();
                String pass = edtpss.getText().toString();


                if (matk.isEmpty() || ten.isEmpty() || sdt.isEmpty() || diachi.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(DangKy.this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();

                } else {
                    for (KhachHangModel kh : list) {
                        if (kh.getUsername().equals(matk)) {
                            Toast.makeText(DangKy.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        } else {
                            boolean check = dao.themkhachhang(matk, ten, pass, sdt, diachi);
                            if (check) {

                                dao = new KhachHangDao(DangKy.this);
                                list = dao.getds();
                                Intent intent = new Intent(DangKy.this, DangNhap.class);
                                startActivity(intent);

                                Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(DangKy.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                }
            }
        });


    }
}
