package com.example.androidmobile.fragment_quanly;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidmobile.R;
import com.example.androidmobile.adapter.KhachHangAdapter;
import com.example.androidmobile.dao.KhachHangDao;
import com.example.androidmobile.model.KhachHangModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class fragment_QLKhachHang extends Fragment {

    KhachHangDao dao;
    RecyclerView rc ;
    KhachHangAdapter adapter;
    ArrayList<KhachHangModel> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment__q_l_khach_hang, container, false);

        rc = view.findViewById(R.id.rckhachhang);
        dao = new KhachHangDao(getContext());
        list = dao.getds();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        rc.setLayoutManager(layoutManager);
        adapter = new KhachHangAdapter(getContext(), list, dao);
        rc.setAdapter(adapter);

        FloatingActionButton fladd = view.findViewById(R.id.fladdkh);
        fladd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });


        SearchView timkiem = view.findViewById(R.id.seach);
        timkiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<KhachHangModel> listsp = new ArrayList<>();
                for (KhachHangModel sp : list) {
                    String ten = String.valueOf(sp.getTen());
                    if (ten.toLowerCase().contains(newText.toLowerCase().trim())) {
                        listsp.add(sp);
                        adapter.a(listsp);
                    }
                }
                return false;
            }
        });
        return view;


    }

    private void showdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themkhachhang, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        EditText edtten, edtsdt, edtdiachi, edtpss, edtmatk;

        edtten = view.findViewById(R.id.edtten_ttctkh);
        edtsdt = view.findViewById(R.id.edtsdt_ttctkh);
        edtdiachi = view.findViewById(R.id.edtdc_ttctkh);
        edtpss = view.findViewById(R.id.edtpass_ttctkh);
        edtmatk = view.findViewById(R.id.edtusername_ttctkh);

        Button btnthem, btnhuy;
        btnthem = view.findViewById(R.id.btnthemkh);
        btnhuy = view.findViewById(R.id.btnhuy);


        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
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


                for (KhachHangModel kh : list) {
                    if (kh.getUsername().equals(matk)) {
                        Toast.makeText(getContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean check = dao.themkhachhang(matk, ten, pass, sdt, diachi);
                        if (check) {
                            alertDialog.dismiss();
                            dao = new KhachHangDao(getContext());
                            list = dao.getds();
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
                            rc.setLayoutManager(layoutManager);
                            adapter = new KhachHangAdapter(getContext(), list, dao);
                            rc.setAdapter(adapter);
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                        }
                    }
                }


            }
        });


    }

}