package com.example.fashion_app.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion_app.R;
import com.example.fashion_app.dao.HoaDonDAO;
import com.example.fashion_app.dao.LoaiSanPhamDAO;
import com.example.fashion_app.fragment.ChiTietHoaDonFragment;
import com.example.fashion_app.fragment.ChiTietSanPhamFragment;
import com.example.fashion_app.fragment.GioHangFragment;
import com.example.fashion_app.model.HoaDon;
import com.example.fashion_app.model.LoaiSanPham;

import java.util.ArrayList;


public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.RecyclerHolder> {
    Activity context;
    ArrayList<HoaDon> listHD;
    HoaDonDAO dao;
    public static String maHoaDon = "";
    public static String trangThai = "";
    public static String thoiGian = "";
    public static String maNguoiDung = "";

    public HoaDonAdapter(Activity context, ArrayList<HoaDon> listHD) {
        this.context = context;
        this.listHD = listHD;
    }


    @NonNull
    @Override
    public HoaDonAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.item_hoadon, parent, false);
        return (new RecyclerHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonAdapter.RecyclerHolder holder, final int position) {
        dao = new HoaDonDAO(context);
        holder.tvMaHD.setText(listHD.get(position).getMaHoadon());
        holder.tvNgay.setText("Ngày: "+listHD.get(position).getNgay());
        holder.tvTrangThai.setText("Trạng thái: "+listHD.get(position).getTrangThai());
        holder.tvTongTien.setText("Tổng tiền: "+listHD.get(position).getTongTien());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maHoaDon = listHD.get(position).getMaHoadon();
                Log.d("TAG", "onClick: "+maHoaDon);
                maNguoiDung = listHD.get(position).getMaKhachhang();
                trangThai = listHD.get(position).getTrangThai();
                thoiGian = listHD.get(position).getNgay();
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new ChiTietHoaDonFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, myFragment).addToBackStack(null).commit();
            }
        });

        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.delete(listHD.get(position).getMaHoadon());
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                listHD.clear();
            }
        });

    }

    @Override
    public int getItemCount() {
            return listHD.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        CardView menu_item;
        TextView tvMaHD;
        TextView tvNgay;
        TextView tvTrangThai;
        TextView tvTongTien;
        ImageView btnDel;

        ImageView iv_Menu;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvMaHD = itemView.findViewById(R.id.tvMaHD);
            tvNgay = itemView.findViewById(R.id.tvNgay);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            tvTongTien = itemView.findViewById(R.id.tvTongTien);
            btnDel = itemView.findViewById(R.id.btnDel);
        }
    }
    public void filterlist(ArrayList<HoaDon> listfilter){
        listHD = listfilter;
        notifyDataSetChanged();
    }
}
