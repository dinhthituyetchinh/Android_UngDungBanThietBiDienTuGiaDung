package com.example.androidmobile.fragment_quanly;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

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
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.androidmobile.R;
import com.example.androidmobile.adapter.SanPhamAdapter_QL;
import com.example.androidmobile.dao.SanPhamDAO;
import com.example.androidmobile.fragment.fragment_sanphamloai;
import com.example.androidmobile.model.SanPhamModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class fragment_QLSanPham extends Fragment {

    SanPhamDAO dao;
    RecyclerView rc ;
    SanPhamAdapter_QL adapter;
    ArrayList<SanPhamModel> list;

    SanPhamModel sp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__q_l_san_pham, container, false);

        rc = view.findViewById(R.id.rcviewQLsp);
        dao = new SanPhamDAO(getContext());
        list = dao.getds();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        rc.setLayoutManager(layoutManager);
        adapter = new SanPhamAdapter_QL(getContext(), list, dao);
        rc.setAdapter(adapter);


        FloatingActionButton fladd = view.findViewById(R.id.flthemsanpham);
        SanPhamModel sp = new SanPhamModel();
        fladd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog1(sp);
                adapter.notifyDataSetChanged();

            }
        });

        //may lanh
        ImageButton img_maylanh = view.findViewById(R.id.ibt_maylanh);
        img_maylanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), fragment_sanphamloai.class);
                intent.putExtra("loai", "panasonic_hp");
                startActivity(intent);

            }
        });
        //may giat
        ImageButton imgmaygiat = view.findViewById(R.id.ibt_maygiat);
        imgmaygiat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), fragment_sanphamloai.class);
                intent.putExtra("loai", "toshiba_mk");
                startActivity(intent);

            }
        });


        ImageButton imgmayxay= view.findViewById(R.id.ibt_mayxay);
        imgmayxay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), fragment_sanphamloai.class);
                intent.putExtra("loai", "philips_hr");
                startActivity(intent);

            }
        });

        ImageButton imgnoicom = view.findViewById(R.id.ibt_noicom);
        imgnoicom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), fragment_sanphamloai.class);
                intent.putExtra("loai", "toshiba_rc");
                startActivity(intent);

            }
        });

        ImageButton imgtulanh = view.findViewById(R.id.ibt_tulanh);
        imgtulanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), fragment_sanphamloai.class);
                intent.putExtra("loai", "panasonic_nr");
                startActivity(intent);

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
                ArrayList<SanPhamModel> listsp = new ArrayList<>();
                for (SanPhamModel sp : list) {
                    String ten = sp.getTen().trim();
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


    public void showDialog1(SanPhamModel sp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity) getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_sp, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        EditText edttensp, edtgiasp, edtmacc, edtmota;
        edttensp = view.findViewById(R.id.edttensp_sua);
        edtgiasp = view.findViewById(R.id.edtgiasp_sua);
        edtmacc = view.findViewById(R.id.edtnhaccsp_sua);
        edtmota = view.findViewById(R.id.edtmotasp_sua);

        RadioButton rdomaylanh, rdomaygiat, rdomayxay, rdonoicom, rdotulanh;
        rdomaylanh = view.findViewById(R.id.rdo_maylanh_sua);
        rdomaygiat = view.findViewById(R.id.rdo_maygiat_sua);
        rdomayxay = view.findViewById(R.id.rdo_mayxay_sua);
        rdonoicom = view.findViewById(R.id.rdo_noicom_sua);
        rdotulanh  = view.findViewById(R.id.rdo_tulanh_sua);

        Button btncn, btnhuy;
        btncn = view.findViewById(R.id.btnsuasp_sua);
        btnhuy = view.findViewById(R.id.btnhuy_sua);


        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btncn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loai = "panasonic_hp";
                if (rdomaylanh.isChecked()) {
                    loai = "panasonic_hp";
                }
                if (rdomaygiat.isChecked()) {
                    loai = "toshiba_mk";
                }
                if (rdomayxay.isChecked()) {
                    loai = "philips_hr";
                }
                if (rdonoicom.isChecked()) {
                    loai = "toshiba_rc";
                }
                if (rdotulanh.isChecked()) {
                    loai = "panasonic_nr";
                }
                String tensp = edttensp.getText().toString();
                int giasp = Integer.parseInt(edtgiasp.getText().toString());
                String mota = edtmota.getText().toString();
                int manhacc = Integer.parseInt(edtmacc.getText().toString());

                boolean check = dao.themVaoGioHang(tensp, giasp, loai, mota, manhacc);
                if (check) {
                    Toast.makeText(getContext(), "Cập nhập thành công", Toast.LENGTH_SHORT).show();


                    list = dao.getds();
                    GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
                    rc.setLayoutManager(layoutManager);
                    adapter = new SanPhamAdapter_QL(getContext(), list, dao);
                    rc.setAdapter(adapter);

                    alertDialog.dismiss();
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(getContext(), "Cập nhập thất bại", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

}