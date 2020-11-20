package com.example.fashion_app.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion_app.R;
import com.example.fashion_app.model.SanPham;

import java.util.ArrayList;


public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.RecyclerHolder> {
    Activity context;
    ArrayList<SanPham> listSP;

    public SanPhamAdapter(Activity context, ArrayList<SanPham> listSP) {
        this.context = context;
        this.listSP = listSP;
    }


    @NonNull
    @Override
    public SanPhamAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.item_sanpham, parent, false);
        return (new RecyclerHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.RecyclerHolder holder, final int position) {
            holder.tvTenSanPham.setText(listSP.get(position).getTenSanPham());
            holder.tvGiaTien.setText(listSP.get(position).getGiaTien());

    }

    @Override
    public int getItemCount() {
            return listSP.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        CardView menu_item;
        TextView tvTenSanPham, tvMoTa, tvGiaTien;//text view layout item sanpham

        ImageView iv_Menu;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
                menu_item = itemView.findViewById(R.id.menu_item);
//                tvTenSanPham = itemView.findViewById(R.id.tv);
//                tvGiaTien = itemView.findViewById(R.id.tvGiaTien);
        }
    }
    public int convertImage(String imgName){
        int resId = context.getResources().getIdentifier(imgName, "drawable", context.getPackageName());
        return resId;
    }
}
