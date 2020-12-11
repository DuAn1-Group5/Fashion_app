package com.example.fashion_app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion_app.LoginActivity;
import com.example.fashion_app.MainActivity;
import com.example.fashion_app.R;
import com.example.fashion_app.adapter.ChiTietHoaDonAdapter;
import com.example.fashion_app.adapter.HoaDonAdapter;
import com.example.fashion_app.adapter.SanPhamAdapter;
import com.example.fashion_app.dao.ChiTietHoaDonDAO;
import com.example.fashion_app.dao.HoaDonDAO;
import com.example.fashion_app.model.ChiTietHoaDon;
import com.example.fashion_app.model.HoaDon;
import com.example.fashion_app.model.NguoiDung;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class GioHangFragment extends Fragment implements ChiTietHoaDonDAO.ChiTietHoaDonInterface{
    ChiTietHoaDonDAO dao;
    ChiTietHoaDonAdapter adapter;
    HoaDonAdapter hoaDonAdapter;
    ArrayList<ChiTietHoaDon> listCTHD;
    HoaDonDAO hoaDonDAO;
    RecyclerView RcvGioHang;
    Button btnBuyNow;
    ArrayList<HoaDon> listHD;

    DatabaseReference mData;
    public static String maHoaDon1;
    String maHoaDon;

    double tongTien;
    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_giohang, container, false);

        RcvGioHang = view.findViewById(R.id.RcvGioHang);
        btnBuyNow = view.findViewById(R.id.btnBuyNow);

        listHD = new ArrayList<>();
        dao = new ChiTietHoaDonDAO(getContext(), this);
        listCTHD = new ArrayList<>();
        maHoaDon = HoaDonAdapter.maHoaDon;

        if(maHoaDon.equals("")){
            listCTHD.clear();
            listCTHD = dao.getAll("");
            layTongGiaTien();
        }else {
            listCTHD.clear();
            listCTHD = dao.getAll(HoaDonAdapter.maHoaDon);

            layTongGiaTien();
            btnBuyNow.setEnabled(false);
            btnBuyNow.setText(""+HoaDonAdapter.trangThai);
        }




        adapter = new ChiTietHoaDonAdapter(getActivity(), listCTHD);
        RcvGioHang.setLayoutManager(new LinearLayoutManager(getContext()));
        RcvGioHang.setAdapter(adapter);
        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Thanh Toán thành công", Toast.LENGTH_SHORT).show();
                mData = FirebaseDatabase.getInstance().getReference();
                maHoaDon1 = mData.push().getKey();
                for (int i = 0; i<listCTHD.size(); i++){
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon("1", listCTHD.get(i).getSize(), maHoaDon1,
                            listCTHD.get(i).getMaSanpham(), listCTHD.get(i).getTenSanPham(), listCTHD.get(i).getGiaSanPham(), listCTHD.get(i).getHinh());
                    dao.update(listCTHD.get(i).getMaChitiethoadon(), chiTietHoaDon);
                }

                hoaDonDAO = new HoaDonDAO(getContext());
                Date d = new Date();
                CharSequence s  = DateFormat.format("dd-MM-yyyy ", d.getTime());
                String ngay = (String)s;
                HoaDon hoaDon = new HoaDon(ngay, "Đang xử lý", LoginActivity.ma, String.valueOf(tongTien));
                hoaDonDAO.insert(hoaDon);

            }
        });


        return view;
    }

    @Override
    public void notifyData() {
        adapter.notifyDataSetChanged();
    }

    public void layTongGiaTien(){
        mData =FirebaseDatabase.getInstance().getReference();
        mData.child("ChiTietHoaDon").orderByChild("maHoadon").equalTo(maHoaDon).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot x : snapshot.getChildren()){
                    Map<String, Object> map = (Map<String, Object>) x.getValue();
                    Object giaSanPham = map.get("giaSanPham");
                    Object soLuong = map.get("soLuong");
                    tongTien += Double.parseDouble(String.valueOf(giaSanPham)) * Double.parseDouble(String.valueOf(soLuong));

                    btnBuyNow.setText("BUY NOW "+tongTien);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
