package com.example.androidmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidmobile.database.DatabaseHelper;

public class DangNhap extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        databaseHelper = new DatabaseHelper(this);
        EditText usernameEditText=findViewById(R.id.edtuse_dn);
        EditText passwordEditText=findViewById(R.id.edtmk_dn);


        Button loginButton = findViewById(R.id.btn_dn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if( username.isEmpty()||password.isEmpty() ){
                    Toast.makeText(DangNhap.this, "Không được để trống", Toast.LENGTH_SHORT).show();

                }
                else {
                    String role = databaseHelper.checkCredentials(username, password);

                    if (role.equals("admin")) {
                        Intent intent = new Intent(DangNhap.this, Activity_admin.class);
//
                        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("username", username);
                        editor.apply();
                        startActivity(intent);
                    } else if (role.equals("khachhang")) {
                        Intent intent = new Intent(DangNhap.this, Activity_khachhang.class);
                        // Intent intent1 =new Intent(DangNhap.this, Activity_khachhang.class);

                        intent.putExtra("username", username);

                        // Lưu trữ dữ liệu vào SharedPreferences
                        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("username", username);
                        editor.apply();
                        startActivity(intent);
                    } else {
                        Toast.makeText(DangNhap.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


        TextView txtdk = findViewById(R.id.txtdk_dn);
        txtdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(DangNhap.this, DangKy.class);
                startActivity(inten);
            }
        });

    }
}