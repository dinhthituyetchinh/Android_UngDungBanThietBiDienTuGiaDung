package com.example.androidmobile.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidmobile.R;
import com.example.androidmobile.adapter.ThongBaoAdapter;
import com.example.androidmobile.dao.ThongBaoDao;
import com.example.androidmobile.model.ThongBaoModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Fragment_thongbao extends Fragment {

    RecyclerView rc;
    ThongBaoAdapter adapter;
    ArrayList<ThongBaoModel> list;
    ThongBaoDao dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_thongbao, container, false);


        SharedPreferences preferences = getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");


        rc = view.findViewById(R.id.rcthongbao);
        dao = new ThongBaoDao(getContext());
        list = dao.getds();
        ArrayList<ThongBaoModel> listtbtheokh = new ArrayList<>();
        for (ThongBaoModel tb : list) {
            if (tb.getMakh().equals(username)) {
                listtbtheokh.add(tb);
            }
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        rc.setLayoutManager(layoutManager);
        adapter = new ThongBaoAdapter(getContext(), listtbtheokh, dao);

        Collections.sort(listtbtheokh, new ThongBao());
        rc.setAdapter(adapter);


        return view;
    }

    public class ThongBao implements Comparator<ThongBaoModel> {
        @Override
        public int compare(ThongBaoModel tb1, ThongBaoModel tb2) {
            return tb2.getMatb() - (tb1.getMatb());
        }
    }
}