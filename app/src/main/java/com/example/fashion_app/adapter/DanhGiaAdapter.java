package com.example.fashion_app.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion_app.R;
import com.example.fashion_app.dao.ChiTietHoaDonDAO;
import com.example.fashion_app.model.ChiTietHoaDon;
import com.example.fashion_app.model.DanhGia;

import java.util.ArrayList;

public class DanhGiaAdapter extends RecyclerView.Adapter<DanhGiaAdapter.RecyclerHolder> {
    Activity context;
    ArrayList<DanhGia> listDanhGia;

    public DanhGiaAdapter(Activity context, ArrayList<DanhGia> listDanhGia) {
        this.context = context;
        this.listDanhGia = listDanhGia;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = context.getLayoutInflater();
        view = inflater.inflate(R.layout.item_danhgia, parent, false);
        return (new RecyclerHolder(view));
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.tv_username.setText(listDanhGia.get(position).getHoTenDayDu());
        holder.tv_danhgia.setText(listDanhGia.get(position).getCamXuc());
        holder.tv_comment.setText(listDanhGia.get(position).getBinhLuan());
    }

    @Override
    public int getItemCount() {
        return listDanhGia.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
       TextView tv_username,tv_danhgia,tv_comment;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
           tv_comment = itemView.findViewById(R.id.tv_comment);
           tv_danhgia = itemView.findViewById(R.id.tv_danhgia);
           tv_username = itemView.findViewById(R.id.tv_danhgia);
        }
    }

}
