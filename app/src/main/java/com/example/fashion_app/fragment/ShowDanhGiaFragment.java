package com.example.fashion_app.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fashion_app.R;
import com.example.fashion_app.adapter.DanhGiaAdapter;
import com.example.fashion_app.adapter.SanPhamAdapter;
import com.example.fashion_app.dao.DanhGiaDAO;
import com.example.fashion_app.model.DanhGia;
import com.example.fashion_app.model.LoaiSanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ShowDanhGiaFragment extends Fragment implements DanhGiaDAO.DanhGiaInterface {
    DanhGiaAdapter danhGiaAdapter;
    RecyclerView rcv_danhgia;
    DanhGiaDAO danhGiaDAO;
    ArrayList<DanhGia> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_danh_gia, container, false);
        // Inflate the layout for this fragment
        rcv_danhgia = view.findViewById(R.id.rcv_danhgia);
        danhGiaDAO = new DanhGiaDAO(getContext(),this);
        list = new ArrayList<>();
        list = danhGiaDAO.getAll();
        danhGiaAdapter = new DanhGiaAdapter(getActivity(), list);
        rcv_danhgia.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv_danhgia.setAdapter(danhGiaAdapter);
        //list.clear();

        return view;
    }

    @Override
    public void notifyData() {
        danhGiaAdapter.notifyDataSetChanged();
    }
}