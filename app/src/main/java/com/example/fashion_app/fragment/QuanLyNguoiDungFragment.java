package com.example.fashion_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion_app.R;
import com.example.fashion_app.adapter.NguoiDungAdapter;
import com.example.fashion_app.model.NguoiDung;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class QuanLyNguoiDungFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    NguoiDung nguoiDung;
    ArrayList<NguoiDung> listNguoiDung;
    DatabaseReference databaseReference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_nguoi_dung,container,false);
        recyclerView = view.findViewById(R.id.rv_loaisanpham);
        floatingActionButton = view.findViewById(R.id.fab_addQuanLyNguoiDung);
        nguoiDung = new NguoiDung();
        listNguoiDung = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        return view;
    }
    private void loadReyclerNguoiDung(){
        listNguoiDung.clear();
        databaseReference.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                nguoiDung = snapshot.getValue(NguoiDung.class);
                nguoiDung.setMaNguoidung(snapshot.getKey());
                listNguoiDung.add(nguoiDung);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                NguoiDungAdapter nguoiDungAdapter = new NguoiDungAdapter(getContext(),listNguoiDung);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(nguoiDungAdapter);
                nguoiDungAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadReyclerNguoiDung();
    }
}
