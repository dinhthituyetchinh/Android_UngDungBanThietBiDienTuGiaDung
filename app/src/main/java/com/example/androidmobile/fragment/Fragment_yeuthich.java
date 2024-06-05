package com.example.androidmobile.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidmobile.R;
import com.example.androidmobile.adapter.SanPhamYT_Adapter;
import com.example.androidmobile.dao.SanPhamYT_Dao;
import com.example.androidmobile.model.SanPhamModel;

import java.util.ArrayList;

public class Fragment_yeuthich extends Fragment {
    ArrayList<SanPhamModel> listyt;
    SanPhamYT_Dao dao;
    SanPhamYT_Adapter adapter;
    RecyclerView rc;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yeuthich, container, false);

        rc= view.findViewById(R.id.rcview_spyeuthich);
        dao = new SanPhamYT_Dao(getContext());
        listyt = dao.getds();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        rc.setLayoutManager(layoutManager);
        adapter= new SanPhamYT_Adapter(getContext(),listyt,dao);
        rc.setAdapter(adapter);

        return  view ;
    }
}