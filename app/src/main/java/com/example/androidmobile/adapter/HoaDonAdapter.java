package com.example.androidmobile.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmobile.R;
import com.example.androidmobile.dao.HoaDonDao;
import com.example.androidmobile.dao.KhachHangDao;
import com.example.androidmobile.dao.SanPhamDAO;
import com.example.androidmobile.dao.ThongBaoDao;
import com.example.androidmobile.model.HoaDonModel;
import com.example.androidmobile.model.KhachHangModel;
import com.example.androidmobile.model.SanPhamModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHodel> {
    KhachHangDao daokh;

    ArrayList<KhachHangModel> listkh;

    SanPhamDAO daosp;

    private Context context;
    private ArrayList<HoaDonModel> list;
    private HoaDonDao dao;

    ThongBaoDao daotb;

    public void a(ArrayList<HoaDonModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public HoaDonAdapter(Context context, ArrayList<HoaDonModel> list, HoaDonDao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
        daosp = new SanPhamDAO(context);
        daotb = new ThongBaoDao(context);

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
        holder.txtmasp.setText("" + list.get(position).getMasp());
        holder.txttiendh.setText("" + list.get(position).getTienhoadon());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDialog1(list.get(position));
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
                dao.delete(String.valueOf(check));
                Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
                list.clear();
                list = dao.getds();
                notifyDataSetChanged();


                String masp = "" + holder.txtmasp.getText().toString();
                SanPhamModel spp = daosp.laythongtin(masp);
                String mota = spp.getMotasp();

                String tb = "Đơn hàng" + mota + " đã bị hủy";
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
        TextView txtmahd, txtmakh, txtmasp, txttiendh, txtxoa;

        public ViewHodel(@NonNull View itemView) {

            super(itemView);
            txtmahd = itemView.findViewById(R.id.txtmahd_hoadon);
            txtmakh = itemView.findViewById(R.id.txtmakh_hoadon);
            txttiendh = itemView.findViewById(R.id.txttienhd_hoadon);

            txtxoa = itemView.findViewById(R.id.txtxoa_hoadon);


        }
    }

    public void showDialog1(HoaDonModel hd) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_hienthichitiethoadon, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextView txttenkh, txtsdtkh, txtdiachi, txttensp, txtgiasp;
        txttenkh = view.findViewById(R.id.txttenkh_thanhtoan);
        txtsdtkh = view.findViewById(R.id.txtsdt_thanhtoan);
        txtdiachi = view.findViewById(R.id.txtdiachi_thanhtoan);

        txttensp = view.findViewById(R.id.txtten);
        txtgiasp = view.findViewById(R.id.txtgia);

        String makh = hd.getMakh();
        String masp = String.valueOf(hd.getMasp());

        daokh = new KhachHangDao(context);
        KhachHangModel khang = daokh.getCustomerByUsername(makh);
        String tenkh = khang.getTen();
        String sdtkh = khang.getSdt();
        String diachikh = khang.getDiachi();

        txttenkh.setText(tenkh);
        txtsdtkh.setText(sdtkh);
        txtdiachi.setText(diachikh);

        daosp = new SanPhamDAO(context);
        SanPhamModel spp = daosp.laythongtin(masp);
        String tensp = spp.getMotasp();
        String giasp = String.valueOf(spp.getGia());
        String loai = spp.getLoai();

        txttensp.setText(tensp);
        txtgiasp.setText(giasp);
        ImageView img = view.findViewById(R.id.imgsanpham);
        if (loai.equals("panasonic_hp")) {
            Picasso.get().load(R.mipmap.img_panasonic_hp).resize(130, 110).into(img);
        } else if (loai.equals("toshiba_mk")) {
            Picasso.get().load(R.mipmap.img_toshiba_mk).resize(130, 110).centerCrop().into(img);
        } else if (loai.equals("panasonic_nr")) {
            Picasso.get().load(R.mipmap.img_panasonic_nr).resize(130, 110).into(img);
        } else if (loai.equals("toshiba_rc")) {
            Picasso.get().load(R.mipmap.img_toshiba_rc).resize(130, 110).into(img);
        } else if (loai.equals("toshiba_tl")) {
            Picasso.get().load(R.mipmap.img_toshiba_tl).resize(130, 110).into(img);
        } else if (loai.equals("philips_hr")) {
            Picasso.get().load(R.mipmap.img_mayxay_hr).resize(130, 110).into(img);
        } else if (loai.equals("panasonic_mxmg")) {
            Picasso.get().load(R.mipmap.img_mayxay_hr).resize(130, 110).into(img);
        } else if (loai.equals("elmich_smmn")) {
            Picasso.get().load(R.mipmap.img_elmich_smmn).resize(130, 110).into(img);
        } else if (loai.equals("elmich_elya")) {
            Picasso.get().load(R.mipmap.img_elmich_elya).resize(130, 110).into(img);
        }


    }
}