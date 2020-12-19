package com.example.fashion_app.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion_app.R;
import com.example.fashion_app.model.NguoiDung;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.ViewHolder> {
    Context context;
    ArrayList<NguoiDung> listNguoiDung;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public NguoiDungAdapter(Context context, ArrayList<NguoiDung> listNguoiDung) {
        this.context = context;
        this.listNguoiDung = listNguoiDung;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_quanlynguoidung,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NguoiDung nguoiDung = listNguoiDung.get(position);
        if(listNguoiDung != null){
            holder.txtNameCustomer.setText(nguoiDung.getHoTenDayDu());
            holder.tvEmail.setText(nguoiDung.getEmail());
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_add_khachhang);
                    final EditText edtTenKhachHang = dialog.findViewById(R.id.edtTenKhachHang);
                    final EditText edtEmail = dialog.findViewById(R.id.edtEmail);
                    final EditText edtCV = dialog.findViewById(R.id.edtCV);
                    final Button btnHuy = dialog.findViewById(R.id.btnHuy);
                    final Button btnLuu = dialog.findViewById(R.id.btnLuu);
                    btnLuu.setText("Cập nhật");

                    edtTenKhachHang.setText(listNguoiDung.get(position).getHoTenDayDu());
                    edtEmail.setText(listNguoiDung.get(position).getEmail());
                    edtCV.setText(listNguoiDung.get(position).getChucvu());

                    btnHuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    btnLuu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            NguoiDung nguoiDung = new NguoiDung(listNguoiDung.get(position).getTenNguoidung(),
                                    edtEmail.getText().toString(), edtCV.getText().toString(), listNguoiDung.get(position).getMatKhau(),
                                    edtTenKhachHang.getText().toString());
                            databaseReference.child("users").child(listNguoiDung.get(position).getMaNguoidung()).setValue(nguoiDung);
                            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            notifyDataSetChanged();

                        }
                    });
                    dialog.show();
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listNguoiDung.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameCustomer;
        TextView tvEmail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameCustomer = itemView.findViewById(R.id.tvHoTenNguoiDung);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
