package com.example.androidmobile.fragment_quanly;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidmobile.R;
import com.example.androidmobile.adapter.HoaDonAdapter1;
import com.example.androidmobile.dao.HoaDonDao1;
import com.example.androidmobile.dao.KhachHangDao;
import com.example.androidmobile.model.HoaDon1;
import com.example.androidmobile.model.KhachHangModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class fragment_QLHoaDon extends Fragment {
    HoaDonDao1 dao;
    RecyclerView rc;
    HoaDonAdapter1 adapter;
    ArrayList<HoaDon1> list;

    KhachHangDao daokh;

    ArrayList<KhachHangModel> listkh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__q_l_hoa_don, container, false);
        rc = view.findViewById(R.id.rcview_hoadon);
        dao = new HoaDonDao1(getContext());
        list = dao.getds();

        daokh = new KhachHangDao(getContext());
        listkh = daokh.getds();

        SharedPreferences preferences = getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");


        for (KhachHangModel kh : listkh) {
            if (!kh.getUsername().equals(username)) {
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
                rc.setLayoutManager(layoutManager);
                adapter = new HoaDonAdapter1(getContext(), list, dao);
                rc.setAdapter(adapter);

            } else {

                ArrayList<HoaDon1> listhdtheokh = new ArrayList<>();
                for (HoaDon1 hd : list) {
                    if (hd.getMakh().equals(username)) {
                        listhdtheokh.add(hd);


                    }
                }
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
                rc.setLayoutManager(layoutManager);
                adapter = new HoaDonAdapter1(getContext(), listhdtheokh, dao);
                rc.setAdapter(adapter);

            }
        }


        FloatingActionButton flthem = view.findViewById(R.id.flthemhoadon);
        flthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        boolean shouldShowFlthem = false;
        for (KhachHangModel kh : listkh) {
            if (kh.getUsername().equals(username)) {
                shouldShowFlthem = true;
                break;
            }
        }
        if (shouldShowFlthem) {
            flthem.setVisibility(View.GONE);
        } else {
            flthem.setVisibility(View.VISIBLE);

        }


        SearchView timkiem = view.findViewById(R.id.seach);
        timkiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<HoaDon1> listsp = new ArrayList<>();
                for (HoaDon1 sp : list) {
                    String ten = String.valueOf(sp.getMahd());
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
}