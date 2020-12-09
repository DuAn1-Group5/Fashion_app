package com.example.fashion_app.dao;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.fashion_app.adapter.HoaDonAdapter;
import com.example.fashion_app.model.ChiTietHoaDon;
import com.example.fashion_app.model.SanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;

public class ChiTietHoaDonDAO {
    Context context;
    DatabaseReference databaseReference;
    ChiTietHoaDonInterface chiTietHoaDonInterface;
    StorageReference storageReference;

    public ChiTietHoaDonDAO(Context context, ChiTietHoaDonInterface chiTietHoaDonInterface) {
        this.context = context;
        this.chiTietHoaDonInterface = chiTietHoaDonInterface;
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public ChiTietHoaDonDAO(Context context) {
        this.context = context;
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void insert(ChiTietHoaDon chiTietHoaDon) {
        databaseReference = FirebaseDatabase.getInstance().getReference("ChiTietHoaDon");
        databaseReference.push().setValue(chiTietHoaDon);


    }

    public void update(String id, ChiTietHoaDon chiTietHoaDon){
        databaseReference = FirebaseDatabase.getInstance().getReference("ChiTietHoaDon");
        chiTietHoaDon.getHinh();
        databaseReference.child(id).setValue(chiTietHoaDon);

    }
    public void delete(String maChiTietHoaDon) {
        databaseReference = FirebaseDatabase.getInstance().getReference("ChiTietHoaDon").child(maChiTietHoaDon);
        databaseReference.removeValue();
    }

    public ArrayList<ChiTietHoaDon> getAll(String mahoadon) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        ArrayList<ChiTietHoaDon> listCTHD = new ArrayList<>();

        Query query = databaseReference.child("ChiTietHoaDon").orderByChild("maHoadon").equalTo(HoaDonAdapter.maHoaDon);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot x : snapshot.getChildren()) {
                        ChiTietHoaDon cthd = x.getValue(ChiTietHoaDon.class);
                        cthd.setMaChitiethoadon(x.getKey());

                            String soLuong = cthd.getSoLuong();
                            String gia = cthd.getGiaSanPham();
                            String maCTHD = cthd.getMaChitiethoadon();
                            String maHD = cthd.getMaHoadon();
                            String maSP = cthd.getMaSanpham();
                            String size = cthd.getSize();
                            String hinh = cthd.getHinh();
                            String ten = cthd.getTenSanPham();

                            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                            chiTietHoaDon.setMaChitiethoadon(maCTHD);
                            chiTietHoaDon.setGiaSanPham(gia);
                            chiTietHoaDon.setHinh(hinh);
                            chiTietHoaDon.setMaSanpham(maSP);
                            chiTietHoaDon.setSize(size);
                            chiTietHoaDon.setSoLuong(soLuong);
                            chiTietHoaDon.setTenSanPham(ten);
                            chiTietHoaDon.setMaHoadon(maHD);

                            listCTHD.add(chiTietHoaDon);
                            chiTietHoaDonInterface.notifyData();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return listCTHD;
    }
    public ArrayList<ChiTietHoaDon> getAll1() {
        ArrayList<ChiTietHoaDon> listCTHD = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("ChiTietHoaDon").orderByChild("maHoadon");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot x : snapshot.getChildren()) {
                        ChiTietHoaDon cthd = x.getValue(ChiTietHoaDon.class);
                        cthd.setMaChitiethoadon(x.getKey());

                        String soLuong = cthd.getSoLuong();
                        String gia = cthd.getGiaSanPham();
                        String maCTHD = cthd.getMaChitiethoadon();
                        String maHD = cthd.getMaHoadon();
                        String maSP = cthd.getMaSanpham();
                        String size = cthd.getSize();
                        String hinh = cthd.getHinh();
                        String ten = cthd.getTenSanPham();

                        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                        chiTietHoaDon.setMaChitiethoadon(maCTHD);
                        chiTietHoaDon.setGiaSanPham(gia);
                        chiTietHoaDon.setHinh(hinh);
                        chiTietHoaDon.setMaSanpham(maSP);
                        chiTietHoaDon.setSize(size);
                        chiTietHoaDon.setSoLuong(soLuong);
                        chiTietHoaDon.setTenSanPham(ten);
                        chiTietHoaDon.setMaHoadon(maHD);

                        listCTHD.add(chiTietHoaDon);
                        chiTietHoaDonInterface.notifyData();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return listCTHD;
    }

    public String getFileExtention(Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public interface ChiTietHoaDonInterface {
        void notifyData();
    }

}
