package com.example.androidmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.androidmobile.fragment.Fragment_home;
import com.example.androidmobile.fragment_quanly.fragment_QLHoaDon;
import com.example.androidmobile.fragment_quanly.fragment_QLKhachHang;
import com.example.androidmobile.fragment_quanly.fragment_QLNhaCungCap;
import com.example.androidmobile.fragment_quanly.fragment_QLSanPham;
import com.google.android.material.navigation.NavigationView;

public class Activity_admin extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar toolbar =findViewById(R.id.toolBar);
        FrameLayout frameLayout=findViewById(R.id.frameLayout);
        NavigationView navigationView = findViewById(R.id.navew);
        drawerLayout =findViewById(R.id.drawerlayout);


        setSupportActionBar(toolbar);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.icon_menu);
        getSupportActionBar().setTitle("Trang chủ");




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if(item.getItemId()==R.id.hometrangchu){

                    fragment = new Fragment_home();
                } else if(item.getItemId()==R.id.QLsanpham){

                    fragment = new fragment_QLSanPham();
                }else if(item.getItemId()==R.id.QLkhachhang){

                    fragment = new fragment_QLKhachHang();
                }else if(item.getItemId()==R.id.QLnhacc){

                    fragment = new fragment_QLNhaCungCap();
                }else if(item.getItemId()==R.id.QLhoadon){

                    fragment = new fragment_QLHoaDon();
                }
                else if(item.getItemId()==R.id.mThoat){
                    Intent intent = new Intent(Activity_admin.this, DangNhap.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
              }//else if (item.getItemId()==R.id.mDoimk){
//                    showDialogDoiMK();
//                }else if (item.getItemId()==R.id.mTop10){
//                    fragment = new ThongKeTop10Fragment();
//                }
//                else if (item.getItemId()==R.id.mDoanhTHu){
//                    fragment = new ThongKeDoanhThu();
//                }

                if(fragment!=null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit();
                    toolbar.setTitle(item.getTitle());
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return  false;
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }



}
