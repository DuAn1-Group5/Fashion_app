package com.example.fashion_app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fashion_app.LoginActivity;
import com.example.fashion_app.R;
import com.example.fashion_app.adapter.HoaDonAdapter;
import com.example.fashion_app.dao.HoaDonDAO;
import com.example.fashion_app.model.HoaDon;

import java.util.ArrayList;

public class HoaDonFragment extends Fragment implements HoaDonDAO.HoaDonInterface {
    HoaDonAdapter adapter;
    HoaDonDAO dao;
    ArrayList<HoaDon> listHD;
    RecyclerView rv_hoadon;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);

        rv_hoadon = view.findViewById(R.id.rv_hoadon);

        dao = new HoaDonDAO(getContext(), this);
        listHD = new ArrayList<>();
        if (LoginActivity.chucVu.equalsIgnoreCase("admin")){
            listHD = dao.getAll();
            adapter = new HoaDonAdapter(getActivity(), listHD);
            rv_hoadon.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_hoadon.setAdapter(adapter);
        }else{
            listHD = dao.getAll(LoginActivity.ma);
            adapter = new HoaDonAdapter(getActivity(), listHD);
            rv_hoadon.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_hoadon.setAdapter(adapter);
        }


        return view;
    }

    public void notifyData(){
        adapter.notifyDataSetChanged();
    }
}