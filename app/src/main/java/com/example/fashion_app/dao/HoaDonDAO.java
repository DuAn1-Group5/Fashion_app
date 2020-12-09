package com.example.fashion_app.dao;

import android.content.Context;

import com.example.fashion_app.fragment.GioHangFragment;
import com.example.fashion_app.model.HoaDon;
import com.example.fashion_app.model.LoaiSanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HoaDonDAO {
    Context context;
    DatabaseReference databaseReference;
    HoaDonInterface hoaDonInterface;
    long id;

    public HoaDonDAO(Context context, HoaDonInterface hoaDonInterface) {
        this.context = context;
        this.hoaDonInterface = hoaDonInterface;
    }

    public HoaDonDAO(Context context) {
        this.context = context;
    }

    public void insert(HoaDon hoaDon) {
        databaseReference = FirebaseDatabase.getInstance().getReference("HoaDon");

        databaseReference.child(GioHangFragment.maHoaDon1).setValue(hoaDon);
    }

    public void update(String id, HoaDon hoaDon){
        databaseReference = FirebaseDatabase.getInstance().getReference("HoaDon").child(id);
        databaseReference.setValue(hoaDon);
    }

    public void delete(String maLoai) {
        databaseReference = FirebaseDatabase.getInstance().getReference("HoaDon").child(maLoai);
        databaseReference.removeValue();
    }

    public ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("HoaDon");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HoaDon hoaDon = snapshot.getValue(HoaDon.class);
                    hoaDon.setMaHoadon(snapshot.getKey());
                    list.add(hoaDon);
                }
                hoaDonInterface.notifyData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return list;
    }


    public interface HoaDonInterface {
        void notifyData();
    }
}
