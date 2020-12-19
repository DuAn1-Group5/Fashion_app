package com.example.fashion_app.dao;

import android.content.Context;

import com.example.fashion_app.model.DanhGia;
import com.example.fashion_app.model.HoaDon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DanhGiaDAO {
    Context context;
    DatabaseReference databaseReference;
    DanhGiaInterface danhGiaInterface;

    public DanhGiaDAO(Context context, DanhGiaInterface danhGiaInterface) {
        this.context = context;
        this.danhGiaInterface = danhGiaInterface;
    }

    public ArrayList<DanhGia> getAll() {
        ArrayList<DanhGia> list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("DanhGia");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DanhGia danhGia = snapshot.getValue(DanhGia.class);
                    list.add(danhGia);
                }
                danhGiaInterface.notifyData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return list;
    }

    public interface DanhGiaInterface {
        void notifyData();
    }
}
