package com.example.fashion_app.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fashion_app.LoginActivity;
import com.example.fashion_app.R;
import com.example.fashion_app.adapter.ChiTietHoaDonAdapter;
import com.example.fashion_app.adapter.HoaDonAdapter;
import com.example.fashion_app.dao.ChiTietHoaDonDAO;
import com.example.fashion_app.dao.HoaDonDAO;
import com.example.fashion_app.model.ChiTietHoaDon;
import com.example.fashion_app.model.HoaDon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class ChiTietHoaDonFragment extends Fragment implements ChiTietHoaDonDAO.ChiTietHoaDonInterface {
    ChiTietHoaDonDAO dao;
    ChiTietHoaDonAdapter adapter;
    HoaDonAdapter hoaDonAdapter;
    ArrayList<ChiTietHoaDon> listCTHD;

    DatabaseReference mData;
    TextView tvMaHoaDon, tvThoiGian, tvTongTien, tvMaNguoiDung, tvTenNguoiDung, tvDiaChi;
    Button btnTongTien;
    RecyclerView recyclerView_hoadon;

    String maHoaDon;
    double tongTien;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chi_tiet_hoa_don, container, false);
        btnTongTien = view.findViewById(R.id.btnTongTien);
        tvMaHoaDon = view.findViewById(R.id.tvMaHoaDon);
        tvThoiGian = view.findViewById(R.id.tvThoiGian);
        tvTongTien = view.findViewById(R.id.tvTongTien);
        recyclerView_hoadon = view.findViewById(R.id.recyclerView_hoadon);
        tvMaNguoiDung = view.findViewById(R.id.tvMaNguoiDung);
        tvTenNguoiDung = view.findViewById(R.id.tvTenNguoiDung);
        tvDiaChi = view.findViewById(R.id.tvDiaChi);


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
            tvThoiGian.setText("Ngày: "+HoaDonAdapter.thoiGian);
            tvMaHoaDon.setText("Mã hóa đơn: "+HoaDonAdapter.maHoaDon);
            tvMaNguoiDung.setText("Mã người dùng: "+HoaDonAdapter.maNguoiDung);
            tvTenNguoiDung.setText("Tên người dùng: "+LoginActivity.tenNguoiDung);
            btnTongTien.setText(""+HoaDonAdapter.trangThai);
            tvDiaChi.setText("Địa chỉ giao hàng: "+HoaDonAdapter.diaChiGiaoHang);
            if (HoaDonAdapter.trangThai.equalsIgnoreCase("Đã thanh toán")){
                btnTongTien.setEnabled(false);
            }
        }

        if (LoginActivity.chucVu.equalsIgnoreCase("admin")){
            btnTongTien.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HoaDonDAO hoaDonDAO = new HoaDonDAO(getContext());
                    hoaDonDAO.update(HoaDonAdapter.maHoaDon, new HoaDon(HoaDonAdapter.thoiGian, "Đã thanh toán", HoaDonAdapter.maNguoiDung, tongTien+"", HoaDonAdapter.diaChiGiaoHang));
                    btnTongTien.setText("Đã thanh toán");
                    btnTongTien.setEnabled(false);
                }
            });
        }else{
            btnTongTien.setEnabled(false);
        }


        adapter = new ChiTietHoaDonAdapter(getActivity(), listCTHD);
        recyclerView_hoadon.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_hoadon.setAdapter(adapter);

        return view;
    }

    @Override
    public void notifyData() {
        adapter.notifyDataSetChanged();
    }

    public void layTongGiaTien(){
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("ChiTietHoaDon").orderByChild("maHoadon").equalTo(maHoaDon).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot x : snapshot.getChildren()){
                    Map<String, Object> map = (Map<String, Object>) x.getValue();
                    Object giaSanPham = map.get("giaSanPham");
                    Object soLuong = map.get("soLuong");
                    tongTien += Double.parseDouble(String.valueOf(giaSanPham)) * Double.parseDouble(String.valueOf(soLuong));

                    tvTongTien.setText("Tổng tiền: "+tongTien);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}