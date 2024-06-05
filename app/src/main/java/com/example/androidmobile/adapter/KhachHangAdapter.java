package com.example.androidmobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmobile.R;
import com.example.androidmobile.ThongTinKhachHangChiTiet;
import com.example.androidmobile.dao.KhachHangDao;
import com.example.androidmobile.model.KhachHangModel;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHodel> {

    private Context context;
    private ArrayList<KhachHangModel> list;
    private KhachHangDao dao;

    public void a(ArrayList<KhachHangModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public KhachHangAdapter(Context context, ArrayList<KhachHangModel> list, KhachHangDao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khachhang, parent, false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
        holder.txtuser.setText("  " + list.get(position).getUsername());
        holder.txtten.setText(list.get(position).getTen());
        holder.txtsdt.setText(list.get(position).getSdt());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, ThongTinKhachHangChiTiet.class);
                String username = list.get(position).getUsername();
                SharedPreferences preferences = context.getSharedPreferences("MyData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", username);
                editor.apply();
                context.startActivity(intent);

                return false;
            }
        });

        holder.txtxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = dao.xoakhachang(list.get(holder.getAdapterPosition()).getUsername());
                switch (check) {
                    case 1:
                        list.clear();
                        list = dao.getds();
                        notifyDataSetChanged();
                        Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();

                        break;
                    case -1:
                        Toast.makeText(context, "Không thể xóa khách hàng khi đang có hóa đơn", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                    default:
                        break;
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView txtuser ,txtten, txtsdt,txtxoa;

        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            txtuser = itemView.findViewById(R.id.txtuser);
            txtten = itemView.findViewById(R.id.txtten);
            txtsdt = itemView.findViewById(R.id.txtsdt);
            txtxoa = itemView.findViewById(R.id.txtxoa);




        }
    }
}
