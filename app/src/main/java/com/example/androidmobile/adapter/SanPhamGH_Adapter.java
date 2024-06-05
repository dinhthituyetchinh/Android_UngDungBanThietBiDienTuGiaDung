package com.example.androidmobile.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmobile.GiaoDienChiTietSP;
import com.example.androidmobile.R;
import com.example.androidmobile.dao.SanPhamGH_dao;
import com.example.androidmobile.dao.ThongBaoDao;
import com.example.androidmobile.model.SanPhamModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SanPhamGH_Adapter  extends RecyclerView.Adapter<SanPhamGH_Adapter.ViewHodel>  {
    private Context context;
    private ArrayList<SanPhamModel> list;
    private SanPhamGH_dao dao;
    private ThongBaoDao daotb;

    public SanPhamGH_Adapter(Context context, ArrayList<SanPhamModel> list, SanPhamGH_dao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
        this.daotb = new ThongBaoDao(context); // Thêm dòng này
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham_ngang, parent, false);

        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder,  @SuppressLint("RecyclerView") int position) {


        holder.txtten.setText(list.get(position).getTen());
        holder.txtgia.setText("Giá: " + list.get(position).getGia());
        String loai = list.get(position).getLoai();
        SanPhamModel sp = list.get(position);
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

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                String masp = ""+list.get(position).getMasp();
                String ten = list.get(position).getTen();
                String gia =""+ list.get(position).getGia();
                String loai = list.get(position).getLoai();
                String mota = list.get(position).getMotasp();
                String manhacc =""+list.get(position).getManhacc();


                Context context = view.getContext();
                Intent intent = new Intent(context, GiaoDienChiTietSP.class);
                intent.putExtra("masp", masp);
                intent.putExtra("ten", ten);
                intent.putExtra("gia", gia);
                intent.putExtra("loai",loai);
                intent.putExtra("mota", mota);
                intent.putExtra("manhacc",manhacc);

                context.startActivity(intent);
                return false;
            }
        });


        holder.txtxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int check = list.get(position).getMasp();
                dao.delete(check);
                Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
                list.clear();
                list = dao.getds();

                notifyDataSetChanged();
                String mota = holder.txtten.getText().toString();
                String tb = "Bạn đã xóa sản phẩm " + mota + " ra khỏi giỏ hàng";
                SharedPreferences preferences = context.getSharedPreferences("MyData", Context.MODE_PRIVATE);
                String username = preferences.getString("username", "");

                boolean check1 = daotb.themthongbao(tb, username);


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView txtten, txtgia,txtxoa;

        ImageView img;


        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.txtten);
            txtgia = itemView.findViewById(R.id.txtgia);
            img = itemView.findViewById(R.id.imgsanpham);
            txtxoa= itemView.findViewById(R.id.txtxoasp);



        }
    }

}
