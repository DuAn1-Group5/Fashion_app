package com.example.fashion_app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fashion_app.R;
import com.example.fashion_app.adapter.TabAdapter;
import com.google.android.material.tabs.TabLayout;


public class TabFragment extends Fragment {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        viewPager = view.findViewById(R.id.viewPager_sanpham);
        tabLayout = view.findViewById(R.id.tabLayout_sanpham);

        adapter = new TabAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new SanPhamNamFragment(), "Sản phẩm nam");
        adapter.addFragment(new SanPhamNuFragment(), "Sản phẩm nữ");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}