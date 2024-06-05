package com.example.androidmobile.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmobile.GiaoDienChiTietSP;
import com.example.androidmobile.R;
import com.example.androidmobile.dao.SanPhamDAO;
import com.example.androidmobile.model.SanPhamModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SanPhamAdapter_QL extends RecyclerView.Adapter<SanPhamAdapter_QL.ViewHodel> {
    private Context context;
    private ArrayList<SanPhamModel> list;
    private SanPhamDAO dao;
    private String loaiDaChon;

    public void a(ArrayList<SanPhamModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public SanPhamAdapter_QL(Context context, ArrayList<SanPhamModel> list, SanPhamDAO dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sampham_quanly, parent, false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder,@SuppressLint("RecyclerView") int position) {
        holder.txtten.setText(list.get(position).getTen());
        holder.txtgia.setText("Giá: " + list.get(position).getGia());

        SanPhamModel sp = list.get(position);


        if (sp.getLoai().equals("panasonic_hp")) {
            Picasso.get().load(R.mipmap.img_panasonic_hp).resize(130, 110).into(holder.img);
        } else if (sp.getLoai().equals("toshiba_mk")) {
            Picasso.get().load(R.mipmap.img_toshiba_mk).resize(130, 110).centerCrop().into(holder.img);
        } else if (sp.getLoai().equals("panasonic_nr")) {
            Picasso.get().load(R.mipmap.img_panasonic_nr).resize(130, 110).into(holder.img);
        } else if (sp.getLoai().equals("toshiba_rc")) {
            Picasso.get().load(R.mipmap.img_toshiba_rc).resize(130, 110).into(holder.img);
        } else if (sp.getLoai().equals("toshiba_tl")) {
            Picasso.get().load(R.mipmap.img_toshiba_tl).resize(130, 110).into(holder.img);
        } else if (sp.getLoai().equals("philips_hr")) {
            Picasso.get().load(R.mipmap.img_mayxay_hr).resize(130, 110).into(holder.img);
        } else if (sp.getLoai().equals("panasonic_mxmg")) {
            Picasso.get().load(R.mipmap.img_mayxay_hr).resize(130, 110).into(holder.img);
        } else if (sp.getLoai().equals("elmich_smmn")) {
            Picasso.get().load(R.mipmap.img_elmich_smmn).resize(130, 110).into(holder.img);
        } else if (sp.getLoai().equals("elmich_elya")) {
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

//////////////////////////////////////////////////////
        holder.txtsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog1(list.get(position));

            }
        });

////////////////////////////////////////////////////
        holder.txtxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = list.get(holder.getAdapterPosition()).getMasp();
                dao.delete(String.valueOf(check));
                Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
                list.clear();
                list=dao.getds();
                notifyDataSetChanged();
            }
        });




    }
    /////////////////////////////////////////////



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView txtten, txtgia, txtloai,txtsua,txtxoa;

        ImageView img;

        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.txtten);
            txtgia = itemView.findViewById(R.id.txtgia);
            txtloai = itemView.findViewById(R.id.txtloai);

            txtxoa = itemView.findViewById(R.id.txtxoasp);
            txtsua = itemView.findViewById(R.id.txtsuasp);
            img = itemView.findViewById(R.id.imgsanpham);


        }


    }


    public void showDialog1(SanPhamModel sp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
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

        edttensp.setText(sp.getTen());
        edtgiasp.setText("" + sp.getGia());
        edtmacc.setText("" + sp.getManhacc());
        edtmota.setText(sp.getMotasp());

        if (sp.getLoai().equals("panasonic_hp")) {
            rdomaylanh.setChecked(true);
        }
        if (sp.getLoai().equals("toshiba_mk")) {
            rdomaygiat.setChecked(true);
        }
        if (sp.getLoai().equals("philips_hr")) {
            rdomayxay.setChecked(true);
        }
        if (sp.getLoai().equals("toshiba_rc")) {
            rdonoicom.setChecked(true);
        }
        if (sp.getLoai().equals("panasonic_nr")) {
            rdotulanh.setChecked(true);
        }

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
                String giasp = edtgiasp.getText().toString();
                String mota = edtmota.getText().toString();
                String manhacc = edtmacc.getText().toString();
                int masp = sp.getMasp();
                if (tensp.isEmpty() || giasp.isEmpty() || mota.isEmpty() || manhacc.isEmpty()) {
                    Toast.makeText(context, "Không được để thông tin trống", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = dao.capnhap(masp, tensp, Integer.parseInt(giasp), loai, mota, Integer.parseInt(manhacc));
                    if (check) {
                        Toast.makeText(context, "Cập nhập thành công", Toast.LENGTH_SHORT).show();

                        list.clear();
                        list = dao.getds();
                        notifyDataSetChanged();
                        alertDialog.dismiss();

                    } else {
                        Toast.makeText(context, "Cập nhập thất bại", Toast.LENGTH_SHORT).show();

                    }
                }


            }
        });


    }

}
