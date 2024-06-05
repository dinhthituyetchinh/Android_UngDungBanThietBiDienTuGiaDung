package com.example.androidmobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmobile.R;
import com.example.androidmobile.dao.ThongBaoDao;
import com.example.androidmobile.model.ThongBaoModel;

import java.util.ArrayList;

public class ThongBaoAdapter extends RecyclerView.Adapter<ThongBaoAdapter.ViewHodel> {
    private Context context;
    private ArrayList<ThongBaoModel> list;
    private ThongBaoDao dao;

    public ThongBaoAdapter(Context context, ArrayList<ThongBaoModel> list, ThongBaoDao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thongbao, parent, false);

        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {


        holder.txtthongbao.setText(list.get(position).getThongtin());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView txtthongbao;

        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            txtthongbao = itemView.findViewById(R.id.txtthongbao);
        }
    }
}
