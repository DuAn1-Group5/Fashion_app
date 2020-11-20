package com.example.fashion_app.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion_app.R;
import com.example.fashion_app.model.LoaiSanPham;
import com.example.fashion_app.model.SanPham;

import java.util.ArrayList;


public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.RecyclerHolder> {
    Activity context;
    ArrayList<LoaiSanPham> listLSP;

    public LoaiSanPhamAdapter(Activity context, ArrayList<LoaiSanPham> listLSP) {
        this.context = context;
        this.listLSP = listLSP;
    }


    @NonNull
    @Override
    public LoaiSanPhamAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.items_loaisanpham, parent, false);
        return (new RecyclerHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSanPhamAdapter.RecyclerHolder holder, final int position) {
            holder.tvTenLoaiSanPham.setText(listLSP.get(position).getTenLoai());

    }

    @Override
    public int getItemCount() {
            return listLSP.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        CardView menu_item;
        TextView tvTenLoaiSanPham;

        ImageView iv_Menu;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvTenLoaiSanPham = itemView.findViewById(R.id.tvTenLoaiSanPham);
        }
    }
    public int convertImage(String imgName){
        int resId = context.getResources().getIdentifier(imgName, "drawable", context.getPackageName());
        return resId;
    }
}
