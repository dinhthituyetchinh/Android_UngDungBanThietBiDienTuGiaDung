package com.example.androidmobile.fragment;

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
import android.widget.ImageButton;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.androidmobile.GiaoDienGioHang;
import com.example.androidmobile.R;
import com.example.androidmobile.adapter.SanPhamAdapter;
import com.example.androidmobile.dao.SanPhamDAO;
import com.example.androidmobile.model.SanPhamModel;

import java.util.ArrayList;

public class Fragment_home extends Fragment {

SanPhamDAO dao;
RecyclerView rc ;
SanPhamAdapter adapter;
ArrayList<SanPhamModel> list;

SanPhamModel sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
//
        ImageSlider imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels= new ArrayList<>();
        slideModels.add(new SlideModel(R.mipmap.img_slideqc, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.mipmap.img_slideqc1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.mipmap.img_slideqc2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.mipmap.img_slideqc3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.mipmap.img_slideqc4, ScaleTypes.FIT));


        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
//

       rc= view.findViewById(R.id.rcsanphamhome);
       dao = new SanPhamDAO(getContext());
       list = dao.getds();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        rc.setLayoutManager(layoutManager);
        adapter= new SanPhamAdapter(getContext(),list,dao);
        rc.setAdapter(adapter);


        ImageButton imgbt = view.findViewById(R.id.ibtGioHang);
        imgbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), GiaoDienGioHang.class);
                startActivity(intent);
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


}