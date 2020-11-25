package com.example.fashion_app.fragment;

import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class LoaiSanPhamFragment extends Fragment implements LoaiSanPhamDAO.LoaiSanPhamInterface {
    LoaiSanPhamDAO loaiSanPhamDAO;
    LoaiSanPhamAdapter loaiSanPhamAdapter;
    ArrayList<LoaiSanPham> list;
    RecyclerView rv_loaisanpham;
    FloatingActionButton fab_addLoaiSanPham;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loai_san_pham, container, false);


        rv_loaisanpham = view.findViewById(R.id.rv_loaisanpham);
        fab_addLoaiSanPham = view.findViewById(R.id.fab_addLoaiSanPham);


        loaiSanPhamDAO = new LoaiSanPhamDAO(getContext(), this);
        list = new ArrayList<LoaiSanPham>();
        list = loaiSanPhamDAO.getAll();
        loaiSanPhamAdapter = new LoaiSanPhamAdapter(getActivity(), list);
        rv_loaisanpham.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_loaisanpham.setAdapter(loaiSanPhamAdapter);





        fab_addLoaiSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_add_loaisanpham);
                final EditText edtTenLoai = dialog.findViewById(R.id.edtTenLoai);
                //final EditText edtMaLoai = dialog.findViewById(R.id.edtMaLoai);

                final Button btnLuu = dialog.findViewById(R.id.btnThemLoaiSanPham);
                final Button btnHuy = dialog.findViewById(R.id.btnHuyLoaiSanPham);

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String maLoai = "";
                        String tenLoai = edtTenLoai.getText().toString();
                        if(tenLoai.length() == 0){
                            Toast.makeText(getActivity(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            loaiSanPhamDAO.insert(tenLoai);
                            dialog.dismiss();

                            list.clear();

                            list = loaiSanPhamDAO.getAll();
                            loaiSanPhamAdapter = new LoaiSanPhamAdapter(getActivity(), list);
                            rv_loaisanpham.setLayoutManager(new LinearLayoutManager(getContext()));
                            rv_loaisanpham.setAdapter(loaiSanPhamAdapter);
                        }

                    }
                });
                dialog.show();
            }
        });

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(getActivity(), "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                final int position = viewHolder.getAdapterPosition();
                final String maLoai = list.get(position).getMaLoai();

                Toast.makeText(getActivity(), "Delete ", Toast.LENGTH_SHORT).show();
                //

                    Snackbar.make(getView(), "Bạn có chắc muốn xóa",5000)
                            .setActionTextColor(Color.RED)
                            .setAction("Có", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    loaiSanPhamDAO.delete(maLoai);
                                    list.remove(position);
                                    list.clear();
                                    //loaiSanPhamAdapter.notifyDataSetChanged();
                                }
                            })
                            .show();
                    loaiSanPhamAdapter.notifyDataSetChanged();

                //Remove swiped item from list and notify the RecyclerView



            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(getContext(), c, recyclerView, viewHolder, dX, dY,
                        actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(), R.color.red))
                        .addSwipeLeftLabel("Xóa").create().decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv_loaisanpham);
        //
        return view;
    }

    @Override
    public void notifyData() {
        loaiSanPhamAdapter.notifyDataSetChanged();
    }

}