package com.example.fashion_app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fashion_app.R;
import com.example.fashion_app.adapter.ChiTietHoaDonAdapter;
import com.example.fashion_app.adapter.HoaDonAdapter;
import com.example.fashion_app.adapter.SanPhamAdapter;
import com.example.fashion_app.dao.ChiTietHoaDonDAO;
import com.example.fashion_app.dao.HoaDonDAO;
import com.example.fashion_app.model.ChiTietHoaDon;
import com.example.fashion_app.model.HoaDon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ChiTietSanPhamFragment extends Fragment {
    Button btn_ctsp_them;
    TextView tv_stsp_mota, tv_stsp_price, tv_stsp_name;
    ImageView iv_ctsp_img;
    CheckBox chkS, chkM, chkL, chkXL;
    public static String size;

    ChiTietHoaDonDAO dao;


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
        chkS = view.findViewById(R.id.chkS);
        chkM = view.findViewById(R.id.chkM);
        chkL = view.findViewById(R.id.chkL);
        chkXL = view.findViewById(R.id.chkXL);

        dao = new ChiTietHoaDonDAO(getContext());

        tv_stsp_name.setText(SanPhamAdapter.ten);
        tv_stsp_mota.setText(SanPhamAdapter.mota);
        tv_stsp_price.setText(SanPhamAdapter.gia+"$");

        Picasso.get().load(SanPhamAdapter.hinhanh).into(iv_ctsp_img);


        btn_ctsp_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkS.isChecked()){
                    size = "S";
                }else  if(chkM.isChecked()){
                    size = "M";
                }else  if(chkL.isChecked()){
                    size = "L";
                }else  if(chkXL.isChecked()){
                    size = "XL";
                }


                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon("1", ChiTietSanPhamFragment.size, "", SanPhamAdapter.ma, SanPhamAdapter.ten, SanPhamAdapter.gia, SanPhamAdapter.hinhanh);
                dao.insert(chiTietHoaDon);

                HoaDonAdapter.maHoaDon = "";
                getFragmentManager().beginTransaction().replace(R.id.framelayout, new GioHangFragment()).commit();

            }
        });
        return view;
    }


}