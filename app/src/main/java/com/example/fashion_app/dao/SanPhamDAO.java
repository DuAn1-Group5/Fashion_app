package com.example.fashion_app.dao;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SanPhamDAO {
    Context context;
    DatabaseReference databaseReference;

    public SanPhamDAO(Context context) {
        this.context = context;
        this.databaseReference = FirebaseDatabase.getInstance().getReference("SanPham");
    }


}
