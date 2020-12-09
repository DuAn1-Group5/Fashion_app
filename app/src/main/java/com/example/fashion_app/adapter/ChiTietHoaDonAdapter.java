package com.example.fashion_app.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion_app.R;
import com.example.fashion_app.dao.ChiTietHoaDonDAO;
import com.example.fashion_app.dao.LoaiSanPhamDAO;
import com.example.fashion_app.fragment.ChiTietSanPhamFragment;
import com.example.fashion_app.model.ChiTietHoaDon;
import com.example.fashion_app.model.LoaiSanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ChiTietHoaDonAdapter extends RecyclerView.Adapter<ChiTietHoaDonAdapter.RecyclerHolder> {
    Activity context;
    ArrayList<ChiTietHoaDon> listCTHD;
    ChiTietHoaDonDAO dao;

    int soLuong = 0;

    public ChiTietHoaDonAdapter(Activity context, ArrayList<ChiTietHoaDon> listCTHD) {
        this.context = context;
        this.listCTHD = listCTHD;
    }


    @NonNull
    @Override
    public ChiTietHoaDonAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.item_giohang, parent, false);
        return (new RecyclerHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietHoaDonAdapter.RecyclerHolder holder, final int position) {
        soLuong = Integer.parseInt(listCTHD.get(position).getSoLuong());
        holder.tv_tenSP.setText(listCTHD.get(position).getTenSanPham());
        holder.tvSize.setText(listCTHD.get(position).getSize());
        holder.tvGia.setText(listCTHD.get(position).getGiaSanPham()+"$");
        holder.tvSoLuong.setText(listCTHD.get(position).getSoLuong());
        try{
            Picasso.get().load(listCTHD.get(position).getHinh()).into(holder.iv_hinh);
        }catch (Exception e){

        }

        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao = new ChiTietHoaDonDAO(context);
                dao.delete(listCTHD.get(position).getMaChitiethoadon());
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        holder.ivCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soLuong++;
                dao = new ChiTietHoaDonDAO(context);
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(String.valueOf(soLuong), listCTHD.get(position).getSize(),
                        "", listCTHD.get(position).getMaSanpham(), listCTHD.get(position).getTenSanPham(),
                        listCTHD.get(position).getGiaSanPham(), listCTHD.get(position).getHinh());
                dao.update(listCTHD.get(position).getMaChitiethoadon(), chiTietHoaDon);
            }
        });
        holder.ivTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soLuong--;
                dao = new ChiTietHoaDonDAO(context);
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(String.valueOf(soLuong), listCTHD.get(position).getSize(),
                        "", listCTHD.get(position).getMaSanpham(), listCTHD.get(position).getTenSanPham(),
                        listCTHD.get(position).getGiaSanPham(), listCTHD.get(position).getHinh());
                dao.update(listCTHD.get(position).getMaChitiethoadon(), chiTietHoaDon);
            }
        });

    }

    @Override
    public int getItemCount() {
            return listCTHD.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        CardView menu_item;
        TextView tv_tenSP;
        TextView tvSize;
        TextView tvGia;
        TextView tvSoLuong;
        ImageView iv_hinh;
        ImageView btnDel;
        ImageView ivCong;
        ImageView ivTru;

        ImageView iv_Menu;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tv_tenSP = itemView.findViewById(R.id.tv_tenSP);
            tvSize = itemView.findViewById(R.id.tvSize);
            tvGia = itemView.findViewById(R.id.tvGia);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            iv_hinh = itemView.findViewById(R.id.iv_hinh);
            btnDel = itemView.findViewById(R.id.btnDel);
            ivCong = itemView.findViewById(R.id.ivCong);
            ivTru = itemView.findViewById(R.id.ivTru);
        }
    }
    public int convertImage(String imgName){
        int resId = context.getResources().getIdentifier(imgName, "drawable", context.getPackageName());
        return resId;
    }
}
