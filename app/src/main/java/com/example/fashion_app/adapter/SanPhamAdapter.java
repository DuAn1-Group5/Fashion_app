package com.example.fashion_app.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion_app.LoginActivity;
import com.example.fashion_app.R;
import com.example.fashion_app.dao.SanPhamDAO;
import com.example.fashion_app.fragment.ChiTietSanPhamFragment;
import com.example.fashion_app.fragment.UpdateMenu;
import com.example.fashion_app.model.SanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.RecyclerHolder> {
    Activity context;
    ArrayList<SanPham> listSP;
    SanPhamDAO dao;

    public static String ma, ten, gia, mota, hinhanh;

    public SanPhamAdapter(Activity context, ArrayList<SanPham> listSP) {
        this.context = context;
        this.listSP = listSP;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.item_sanpham, parent, false);
        return (new RecyclerHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, final int position) {
            holder.tvTenSanPham.setText(listSP.get(position).getTenSanPham());
            holder.tvGiaTien.setText(listSP.get(position).getGiaTien()+"$");
            try{
                Picasso.get().load(listSP.get(position).getHinhanh()).into(holder.iv_product);
            }catch (Exception e){

            }

            if (LoginActivity.chucVu.equals("admin")){
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        final Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.dialog_sua_xoa_sanpham);
                        final Button btnSua = dialog.findViewById(R.id.btnSua);
                        final Button btnXoa = dialog.findViewById(R.id.btnXoa);

                        btnSua.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle bundle = new Bundle();
                                bundle.putString("masanpham", listSP.get(position).getMaSanPham());
                                bundle.putString("tensanpham", listSP.get(position).getTenSanPham());
                                bundle.putString("mota", listSP.get(position).getMoTa());
                                bundle.putString("giatien", listSP.get(position).getGiaTien());
                                bundle.putString("soluong", listSP.get(position).getSoLuong());
                                bundle.putString("maloai", listSP.get(position).getMaLoai());
                                try {
                                    bundle.putString("hinhanh", listSP.get(position).getHinhanh().toString());
                                }catch (Exception e){

                                }

                                UpdateMenu updateMenu = new UpdateMenu();
                                updateMenu.setArguments(bundle);
                                updateMenu.show(((AppCompatActivity)context).getFragmentManager(), updateMenu.getTag());
                                listSP.clear();
                                dialog.dismiss();
                            }
                        });

                        btnXoa.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dao = new SanPhamDAO(context);
                                dao.delete(listSP.get(position).getMaSanPham());
                                listSP.clear();
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                //notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        return false;
                    }
                });
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ma = listSP.get(position).getMaSanPham();
                    ten = listSP.get(position).getTenSanPham();
                    gia = listSP.get(position).getGiaTien();
                    mota = listSP.get(position).getMoTa();
                    hinhanh = listSP.get(position).getHinhanh();
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = new ChiTietSanPhamFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, myFragment).addToBackStack(null).commit();
                }
            });
    }

    @Override
    public int getItemCount() {
            return listSP.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        CardView menu_item;
        TextView tvTenSanPham, tvGiaTien;//text view layout item sanpham
        ImageView iv_product;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
                menu_item = itemView.findViewById(R.id.items_sanPham);
                tvTenSanPham = itemView.findViewById(R.id.tv_nameProduct);
                tvGiaTien = itemView.findViewById(R.id.tv_priceProduct);
                iv_product = itemView.findViewById(R.id.iv_product);
        }
    }
    public void filterlist(ArrayList<SanPham> listfilter){
        listSP = listfilter;
        notifyDataSetChanged();
    }
}
