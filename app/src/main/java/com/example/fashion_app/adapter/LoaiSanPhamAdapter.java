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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion_app.R;
import com.example.fashion_app.dao.LoaiSanPhamDAO;
import com.example.fashion_app.model.LoaiSanPham;
import com.example.fashion_app.model.SanPham;

import java.util.ArrayList;


public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.RecyclerHolder> {
    Activity context;
    ArrayList<LoaiSanPham> listLSP;
    LoaiSanPhamDAO dao;

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
        dao = new LoaiSanPhamDAO(context);
            holder.tvTenLoaiSanPham.setText(listLSP.get(position).getTenLoai());
            //String key = listLSP.get(position).getMaLoai();
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_add_loaisanpham);
                    final EditText edtTenLoai = dialog.findViewById(R.id.edtTenLoai);
                    //final EditText edtMaLoai = dialog.findViewById(R.id.edtMaLoai);

                    final Button btnLuu = dialog.findViewById(R.id.btnThemLoaiSanPham);
                    final Button btnHuy = dialog.findViewById(R.id.btnHuyLoaiSanPham);

                    edtTenLoai.setText(listLSP.get(position).getTenLoai());
                    btnLuu.setText("Cập nhật");
                    btnLuu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String id = listLSP.get(position).getMaLoai();
                            String tenLoai = edtTenLoai.getText().toString();
                            if(tenLoai.length() == 0){
                                Toast.makeText(context, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                            }else{
                                dao.update(id, tenLoai);
                                dialog.dismiss();

                                listLSP.clear();
                                //listLSP = dao.getAll();
                                notifyDataSetChanged();
                            }

                        }
                    });

                    btnHuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
//                    dao.delete(key);
//                    listLSP.clear();
//                    notifyDataSetChanged();
                    return false;
                }
            });

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
