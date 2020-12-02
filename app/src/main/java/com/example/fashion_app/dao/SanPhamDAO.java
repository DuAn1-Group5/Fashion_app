package com.example.fashion_app.dao;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.fashion_app.model.LoaiSanPham;
import com.example.fashion_app.model.SanPham;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SanPhamDAO {
    Context context;
    DatabaseReference databaseReference;
    SanPhamInterface sanPhamInterface;

    public SanPhamDAO(Context context, SanPhamInterface sanPhamInterface) {
        this.context = context;
        this.sanPhamInterface = sanPhamInterface;
    }

    public SanPhamDAO(Context context) {
        this.context = context;
    }

    public void insert(String tenSp, String giaTien, String moTa, String size, String soLuong,String hinhanh ,String maLoai) {
        databaseReference = FirebaseDatabase.getInstance().getReference("SanPham");
        SanPham sanPham = new SanPham( tenSp, giaTien, moTa,size,soLuong,hinhanh, maLoai);
        databaseReference.push().setValue(sanPham);
    }

    public ArrayList<SanPham> getAll() {
        ArrayList<SanPham> listSp = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("SanPham");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    SanPham sanPham = snapshot.getValue(SanPham.class);
                    sanPham.setMaSanPham(snapshot.getKey());
                    listSp.add(sanPham);
                    sanPhamInterface.notifyData();
                }
            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });
        return listSp;
    }

    public interface SanPhamInterface {
        void notifyData();
    }


}
