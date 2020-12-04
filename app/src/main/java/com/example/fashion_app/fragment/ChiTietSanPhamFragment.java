package com.example.fashion_app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fashion_app.R;
import com.example.fashion_app.adapter.SanPhamAdapter;
import com.squareup.picasso.Picasso;


public class ChiTietSanPhamFragment extends Fragment {
    Button btn_ctsp_them;
    TextView tv_stsp_mota, tv_stsp_price, tv_stsp_name;
    ImageView iv_ctsp_img;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chi_tiet_san_pham, container, false);
        tv_stsp_name = view.findViewById(R.id.tv_stsp_name);
        tv_stsp_mota = view.findViewById(R.id.tv_stsp_mota);
        tv_stsp_price = view.findViewById(R.id.tv_stsp_price);
        btn_ctsp_them = view.findViewById(R.id.btn_ctsp_them);
        iv_ctsp_img = view.findViewById(R.id.iv_ctsp_img);

        tv_stsp_name.setText(SanPhamAdapter.ten);
        tv_stsp_mota.setText(SanPhamAdapter.mota);
        tv_stsp_price.setText(SanPhamAdapter.gia+"$");

        Picasso.get().load(SanPhamAdapter.hinhanh).into(iv_ctsp_img);
        return view;
    }
}