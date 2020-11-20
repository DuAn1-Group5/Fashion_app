package com.example.fashion_app.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fashion_app.R;
import com.example.fashion_app.adapter.LoaiSanPhamAdapter;
import com.example.fashion_app.dao.LoaiSanPhamDAO;
import com.example.fashion_app.model.LoaiSanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LoaiSanPhamFragment extends Fragment implements LoaiSanPhamDAO.LoaiSanPhamInterface {
    LoaiSanPhamDAO loaiSanPhamDAO;
    LoaiSanPhamAdapter loaiSanPhamAdapter;
    ArrayList<LoaiSanPham> list;
    RecyclerView rv_loaisanpham;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loai_san_pham, container, false);

        rv_loaisanpham = view.findViewById(R.id.rv_loaisanpham);

//        Button btnThemLoaiSanPham = view.findViewById(R.id.btnThemLoaiSanPham);
//        Button btnDelete = view.findViewById(R.id.btndelete);
        loaiSanPhamDAO = new LoaiSanPhamDAO(getContext(), this);
        list = new ArrayList<LoaiSanPham>();
        list = loaiSanPhamDAO.getAll();
        loaiSanPhamAdapter = new LoaiSanPhamAdapter(getActivity(), list);
        rv_loaisanpham.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_loaisanpham.setAdapter(loaiSanPhamAdapter);

//        btnThemLoaiSanPham.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final Dialog dialog = new Dialog(getContext());
//                dialog.setContentView(R.layout.dialog_add_loaisp);
//
//                final EditText edtLoaiSanPham = dialog.findViewById(R.id.edtLoaiSanPham);
//                final EditText edtMaLoai = dialog.findViewById(R.id.edtMaLoai);
//                final Button btnThem = dialog.findViewById(R.id.btnThem);
//                final Button btnHuy = dialog.findViewById(R.id.btnHuy);
//
//                btnThem.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String loaiSanPham = edtLoaiSanPham.getText().toString();
//                        int maLoai = Integer.parseInt(edtMaLoai.getText().toString());
//                        if(loaiSanPham.length() == 0){
//                            Toast.makeText(getActivity(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
//                        }else {
//                            loaiSanPhamDAO.insert(""+maLoai, loaiSanPham);
//                            dialog.dismiss();
//                        }
//                    }
//                });
//                btnHuy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//            }
//        });
//
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loaiSanPhamDAO.delete("-MMQjwoLIFQk-e6X86-L");
//            }
//        });
        return view;
    }

    @Override
    public void notifyData() {
        loaiSanPhamAdapter.notifyDataSetChanged();
    }
}