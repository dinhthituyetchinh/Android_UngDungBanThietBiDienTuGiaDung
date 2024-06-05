package com.example.androidmobile.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmobile.R;
import com.example.androidmobile.dao.ChiTietHoaDonDao;
import com.example.androidmobile.dao.HoaDonDao1;
import com.example.androidmobile.dao.KhachHangDao;
import com.example.androidmobile.dao.SanPhamDAO;
import com.example.androidmobile.dao.ThongBaoDao;
import com.example.androidmobile.model.ChiTietHoaDon;
import com.example.androidmobile.model.HoaDon1;
import com.example.androidmobile.model.KhachHangModel;

import java.util.ArrayList;

public class HoaDonAdapter1 extends RecyclerView.Adapter<HoaDonAdapter1.ViewHodel> {
    KhachHangDao daokh;

    ArrayList<KhachHangModel> listkh;

    SanPhamDAO daosp;
    private Context context;
    private ArrayList<HoaDon1> list;
    private HoaDonDao1 dao;

    ThongBaoDao daotb;
    ChiTietHoaDonDao daocthd;
    ArrayList<ChiTietHoaDon> listcthd;

    ChiTietHoaDonAdapter adapterCThd;

    public void a(ArrayList<HoaDon1> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public HoaDonAdapter1(Context context, ArrayList<HoaDon1> list, HoaDonDao1 dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
        daosp = new SanPhamDAO(context);
        daotb = new ThongBaoDao(context);
        daocthd = new ChiTietHoaDonDao(context);

    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoadon, parent, false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {

        holder.txtmahd.setText("" + list.get(position).getMahd());
        holder.txtmakh.setText("" + list.get(position).getMakh());
        holder.txttiendh.setText("" + list.get(position).getTongtien());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });

        //////////////////////ẩn chức năng sửa
        daokh = new KhachHangDao(context);
        listkh = daokh.getds();
        SharedPreferences preferences = context.getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");
        boolean shouldShowFlthem = false;


        holder.txtxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = list.get(position).getMahd();
                daocthd.delete(String.valueOf(check));
                dao.delete(String.valueOf(check));
                Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
                list.clear();
                list = dao.getds();
                notifyDataSetChanged();


                String tb = "Đơn hàng " + check + " đã bị hủy";
                SharedPreferences preferences = context.getSharedPreferences("MyData", Context.MODE_PRIVATE);
                String username = preferences.getString("username", "");

                boolean check1 = daotb.themthongbao(tb, username);


            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDialog1(list.get(position));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView txtmahd, txtmakh, txtmasp, txttiendh, txtsua, txtxoa;

        public ViewHodel(@NonNull View itemView) {

            super(itemView);
            txtmahd = itemView.findViewById(R.id.txtmahd_hoadon);
            txtmakh = itemView.findViewById(R.id.txtmakh_hoadon);
            txttiendh = itemView.findViewById(R.id.txttienhd_hoadon);

            txtxoa = itemView.findViewById(R.id.txtxoa_hoadon);

        }
    }


    public void showDialog1(HoaDon1 hd) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_hienthichitiethoadon, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        RecyclerView rc = view.findViewById(R.id.rcdanhsachsphd);
        ArrayList<ChiTietHoaDon> listsptheohd = new ArrayList<>();
        listcthd = daocthd.getds();
        for (ChiTietHoaDon cthd : listcthd) {
            if (hd.getMahd() == cthd.getMahd()) {
                listsptheohd.add(cthd);
            }
        }

        GridLayoutManager layoutManager = new GridLayoutManager(context, 1);
        rc.setLayoutManager(layoutManager);
        adapterCThd = new ChiTietHoaDonAdapter(context, listsptheohd, daocthd);
        rc.setAdapter(adapterCThd);


        TextView txttenkh, txtsdtkh, txtdiachi;
        txttenkh = view.findViewById(R.id.txttenkh_thanhtoan);
        txtsdtkh = view.findViewById(R.id.txtsdt_thanhtoan);
        txtdiachi = view.findViewById(R.id.txtdiachi_thanhtoan);


        String makh = hd.getMakh();
        daokh = new KhachHangDao(context);
        KhachHangModel khang = daokh.getCustomerByUsername(makh);
        String tenkh = khang.getTen();
        String sdtkh = khang.getSdt();
        String diachikh = khang.getDiachi();

        txttenkh.setText(tenkh);
        txtsdtkh.setText("|  " + sdtkh);
        txtdiachi.setText(diachikh);
    }
}