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
import java.util.Map;

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

    public ArrayList<HoaDon> getAll(String maNguoiDung) {
        ArrayList<HoaDon> list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("HoaDon");
        databaseReference.orderByChild("maKhachhang").equalTo(maNguoiDung).addValueEventListener(new ValueEventListener() {
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


    public ArrayList<HoaDon> getTrangThaiHoaDon(String trangThai, String maKhachHang) {
        ArrayList<HoaDon> list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("HoaDon");
        databaseReference.orderByChild("trangThai").equalTo(trangThai).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HoaDon hoaDon = snapshot.getValue(HoaDon.class);
                    hoaDon.setMaHoadon(snapshot.getKey());

                    try{
                        if (maKhachHang.equalsIgnoreCase("admin")){
                            HoaDon hoaDon1 = snapshot.getValue(HoaDon.class);
                            hoaDon1.setMaHoadon(snapshot.getKey());
                            list.add(hoaDon1);
                        }
                        else if (hoaDon.getMaKhachhang().equalsIgnoreCase(maKhachHang)){
                            list.add(hoaDon);
                        }
                    }catch (Exception e){

                    }





//                    Map<String,Object> map = (Map<String,Object>) snapshot.getValue();
//                    Object price = map.get("tongTien");
//                    Object trangThai = map.get("trangThai");
//                    Object ngay = map.get("ngay");
//                    Object maKhachhang = map.get("maKhachhang");
//                    Object diaChiGiaoHang = map.get("diaChiGiaoHang");
//
//                    try{
//                        if (maKhachhang.equals(maKhachHang)){
//                            HoaDon hoaDon = new HoaDon(String.valueOf(ngay), String.valueOf(trangThai), String.valueOf(maKhachhang), String.valueOf(price), String.valueOf(diaChiGiaoHang));
//                            //hoaDon.setMaHoadon(snapshot.getKey());
//                            list.add(hoaDon);
//                        }else{
//
//                        }
//                    }catch (Exception e){
//
//                    }

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
