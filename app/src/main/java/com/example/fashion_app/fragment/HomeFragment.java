package com.example.fashion_app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fashion_app.R;

public class HomeFragment extends Fragment {
    Button iv_Men, iv_Women, iv_SaleOff;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        iv_Men = view.findViewById(R.id.iv_Men);
        iv_Women = view.findViewById(R.id.iv_Women);
        iv_SaleOff = view.findViewById(R.id.iv_Sale);

        iv_Men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.framelayout, new SanPhamNamFragment()).commit();
            }
        });
        iv_Women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.framelayout, new SanPhamNuFragment()).commit();
            }
        });
        return view;
    }
}