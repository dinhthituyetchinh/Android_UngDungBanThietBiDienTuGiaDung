package com.example.androidmobile.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmobile.R;
import com.example.androidmobile.dao.NhaccDao;
import com.example.androidmobile.model.NhaCungCapModel;

import java.util.ArrayList;

public class NhaccAdapter extends RecyclerView.Adapter<NhaccAdapter.ViewHodel>{

    private Context context;
    private ArrayList<NhaCungCapModel> list;
    private NhaccDao dao;

    public NhaccAdapter(Context context, ArrayList<NhaCungCapModel> list, NhaccDao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    public void a(ArrayList<NhaCungCapModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nhacungcap, parent, false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {

        holder.txtma.setText("" + list.get(position).getManhacc());
        holder.txtten.setText("" + list.get(position).getTennhacc());
        holder.txtemail.setText("" + list.get(position).getEmail());
        holder.txtsdt.setText("" + list.get(position).getSdt());


        holder.txtxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int check = dao.xoanhacc(list.get(holder.getAdapterPosition()).getManhacc());
                switch (check) {
                    case 1:
                        list.clear();
                        list = dao.getds();
                        notifyDataSetChanged();
                        Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();

                        break;
                    case -1:
                        Toast.makeText(context, "Không thể xóa loại sách khi có sản phẩm thuộc thể nhà cung cấp này ", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                    default:
                        break;
                }
            }
        });

        holder.txtsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog1(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView txtma, txtten , txtemail, txtsdt,txtsua,txtxoa;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);

            txtma = itemView.findViewById(R.id.txtmanhacc);
            txtten = itemView.findViewById(R.id.txttennhacc);
            txtemail = itemView.findViewById(R.id.txtemail);
            txtsdt = itemView.findViewById(R.id.txtsdtnhacc);
            txtsua = itemView.findViewById(R.id.txtsuanhacc);
            txtxoa = itemView.findViewById(R.id.txtxoanhacc);

        }
    }

    public void showDialog1(NhaCungCapModel cc) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_capnhap_nhacc, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        EditText edtten, edtemail, edtsdt;
        edtten = view.findViewById(R.id.edttennhacc);
        edtemail = view.findViewById(R.id.edtemailnhacc);
        edtsdt = view.findViewById(R.id.edtsdtnhacc);

        edtten.setText(cc.getTennhacc());
        edtemail.setText(cc.getEmail());
        edtsdt.setText(cc.getSdt());

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
                int ma = cc.getManhacc();
                String ten = edtten.getText().toString();
                String email = edtemail.getText().toString();
                String sdt = edtsdt.getText().toString();

                boolean check = dao.capnhapnhacc(ma, ten, email, sdt);
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
        });


    }

}
