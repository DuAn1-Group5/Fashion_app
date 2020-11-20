package com.example.fashion_app.dao;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.fashion_app.adapter.LoaiSanPhamAdapter;
import com.example.fashion_app.model.LoaiSanPham;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoaiSanPhamDAO {
    Context context;
    DatabaseReference databaseReference;
    LoaiSanPhamInterface loaiSanPhamInterface;

    public LoaiSanPhamDAO(Context context, LoaiSanPhamInterface loaiSanPhamInterface) {
        this.context = context;
        this.loaiSanPhamInterface = loaiSanPhamInterface;
    }


    public void insert(String id, String tenLoaiSanPham) {
        databaseReference = FirebaseDatabase.getInstance().getReference("LoaiSanPham");
        LoaiSanPham loaiSanPham = new LoaiSanPham(id, tenLoaiSanPham);
        databaseReference.push().setValue(loaiSanPham);
    }

    public void delete(String key) {
        databaseReference = FirebaseDatabase.getInstance().getReference("LoaiSanPham").child(key);
        databaseReference.removeValue();
    }

    public ArrayList<LoaiSanPham> getAll() {
        ArrayList<LoaiSanPham> list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("LoaiSanPham");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LoaiSanPham loaisanpham = snapshot.getValue(LoaiSanPham.class);
                    //sanPham.setMaLoai(snapshot.getKey());
                    list.add(loaisanpham);
                    loaiSanPhamInterface.notifyData();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return list;
    }


    public interface LoaiSanPhamInterface {
        void notifyData();
    }
}
