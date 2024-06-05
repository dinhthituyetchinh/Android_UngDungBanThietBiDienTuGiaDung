package com.example.androidmobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmobile.R;
import com.example.androidmobile.dao.ChiTietHoaDonDao;
import com.example.androidmobile.dao.SanPhamDAO;
import com.example.androidmobile.model.ChiTietHoaDon;
import com.example.androidmobile.model.SanPhamModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChiTietHoaDonAdapter extends RecyclerView.Adapter<ChiTietHoaDonAdapter.ViewHodel> {
    private Context context;
    private ArrayList<ChiTietHoaDon> list;
    private ChiTietHoaDonDao dao;

    public void a(ArrayList<ChiTietHoaDon> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    SanPhamDAO daosp;

    public ChiTietHoaDonAdapter(Context context, ArrayList<ChiTietHoaDon> list, ChiTietHoaDonDao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
        this.daosp = new SanPhamDAO(context);
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_chitiethoadon, parent, false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
        int masp = list.get(position).getMasp();

//        KhachHangModel customer = dao.getCustomerByUsername(username);
//        if (customer != null) {
//            // Dữ liệu đã được truy xuất thành công
//            String ten = customer.getTen();
//            String pass = customer.getPasswork();
//            String sdt = customer.getSdt();
//            String diachi = customer.getDiachi();
//            // Tiếp tục xử lý dữ liệu
//            txttekh.setText(ten+"  |");
//            txtsdtkh.setText(sdt);
//            txtdiachikh.setText(diachi);
//        }

        SanPhamModel sp = daosp.laythongtin(String.valueOf(masp));
        String mota = sp.getMotasp();
        String loai = sp.getLoai();
        holder.txtten.setText(mota);


        if (loai.equals("panasonic_hp")) {
            Picasso.get().load(R.mipmap.img_panasonic_hp).resize(130, 110).into(holder.img);
        } else if (loai.equals("toshiba_mk")) {
            Picasso.get().load(R.mipmap.img_toshiba_mk).resize(130, 110).centerCrop().into(holder.img);
        } else if (loai.equals("panasonic_nr")) {
            Picasso.get().load(R.mipmap.img_panasonic_nr).resize(130, 110).into(holder.img);
        } else if (loai.equals("toshiba_rc")) {
            Picasso.get().load(R.mipmap.img_toshiba_rc).resize(130, 110).into(holder.img);
        } else if (loai.equals("toshiba_tl")) {
            Picasso.get().load(R.mipmap.img_toshiba_tl).resize(130, 110).into(holder.img);
        } else if (loai.equals("philips_hr")) {
            Picasso.get().load(R.mipmap.img_mayxay_hr).resize(130, 110).into(holder.img);
        } else if (loai.equals("panasonic_mxmg")) {
            Picasso.get().load(R.mipmap.img_mayxay_hr).resize(130, 110).into(holder.img);
        } else if (loai.equals("elmich_smmn")) {
            Picasso.get().load(R.mipmap.img_elmich_smmn).resize(130, 110).into(holder.img);
        } else if (loai.equals("elmich_elya")) {
            Picasso.get().load(R.mipmap.img_elmich_elya).resize(130, 110).into(holder.img);
        }


        holder.txtgia.setText("" + list.get(position).getGiasp());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView txtten, txtgia;
        ImageView img;

        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.txtten);
            txtgia = itemView.findViewById(R.id.txtgia);
            img = itemView.findViewById(R.id.imgsanpham);

        }
    }
}
