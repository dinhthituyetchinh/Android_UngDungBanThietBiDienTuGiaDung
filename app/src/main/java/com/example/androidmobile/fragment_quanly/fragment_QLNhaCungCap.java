package com.example.androidmobile.fragment_quanly;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Toast;

import com.example.androidmobile.R;
import com.example.androidmobile.adapter.NhaccAdapter;
import com.example.androidmobile.dao.NhaccDao;
import com.example.androidmobile.model.NhaCungCapModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class fragment_QLNhaCungCap extends Fragment {
    NhaccDao dao;
    RecyclerView rc ;
    NhaccAdapter adapter;
    ArrayList<NhaCungCapModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__q_l_nha_cung_cap, container, false);
        rc = view.findViewById(R.id.rcnhacc);
        dao = new NhaccDao(getContext());
        list = dao.getds();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        rc.setLayoutManager(layoutManager);
        adapter = new NhaccAdapter(getContext(), list, dao);
        rc.setAdapter(adapter);

        FloatingActionButton fladd = view.findViewById(R.id.fladd_nhacc);
        fladd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhaCungCapModel kh = new NhaCungCapModel();
                showDialog1(kh);
                adapter.notifyDataSetChanged();
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
                ArrayList<NhaCungCapModel> listsp = new ArrayList<>();
                for (NhaCungCapModel sp : list) {
                    String ten = String.valueOf(sp.getTennhacc());
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


    public void showDialog1(NhaCungCapModel cc) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity) getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_capnhap_nhacc, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        EditText edtten, edtemail, edtsdt;
        edtten = view.findViewById(R.id.edttennhacc);
        edtemail = view.findViewById(R.id.edtemailnhacc);
        edtsdt = view.findViewById(R.id.edtsdtnhacc);


        Button btncn, btnhuy;
        btncn = view.findViewById(R.id.btncn_nhcc);
        btnhuy = view.findViewById(R.id.btnhuy_nhacc);


        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btncn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ten = edtten.getText().toString();
                String email = edtemail.getText().toString();
                String sdt = edtsdt.getText().toString();

                boolean check = dao.themnhacc(ten, email, sdt);
                if (check) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                    list.clear();
                    list = dao.getds();
                    GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
                    rc.setLayoutManager(layoutManager);
                    adapter = new NhaccAdapter(getContext(), list, dao);
                    rc.setAdapter(adapter);
                    alertDialog.dismiss();


                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }


}