package com.example.androidmobile.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.androidmobile.DangNhap;
import com.example.androidmobile.GiaoDienGioHang;
import com.example.androidmobile.R;
import com.example.androidmobile.ThongTinKhachHangChiTiet;
import com.example.androidmobile.fragment_quanly.fragment_QLHoaDon;

import java.util.ArrayList;

public class Fragment_thongtin extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thongtin, container, false);

        ImageSlider imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.mipmap.img_slideqc, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.mipmap.img_slideqc1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.mipmap.img_slideqc2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.mipmap.img_slideqc3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.mipmap.img_slideqc4, ScaleTypes.FIT));


        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        /////sự kiện đăng xuất tài khoản của khách hàng
        TextView txtdx = view.findViewById(R.id.txtdx_ftt);
        txtdx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DangNhap.class);
                startActivity(intent);
            }
        });
        ///////////////sự kiện chuyển sang màn hình chi tiết thông tin
        LinearLayout layout = view.findViewById(R.id.layout_tt_ftt);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ThongTinKhachHangChiTiet.class);
                startActivity(intent);
            }
        });

        /////////////////sự kiện các nút xem tiếp thông tin
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



        /////// sự kiện chuyển giao diện hóa đơn
        LinearLayout layouthd = view.findViewById(R.id.layoutdonmua_ftt);
        layouthd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new fragment_QLHoaDon());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        LinearLayout layoutgh = view.findViewById(R.id.layout_gh_ftt);
        layoutgh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), GiaoDienGioHang.class);
                startActivity(intent);
            }
        });
        return view;
    }
}