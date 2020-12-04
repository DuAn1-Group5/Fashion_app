package com.example.fashion_app.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashion_app.R;
import com.example.fashion_app.adapter.LoaiSanPhamAdapter;
import com.example.fashion_app.adapter.SanPhamAdapter;
import com.example.fashion_app.dao.LoaiSanPhamDAO;
import com.example.fashion_app.dao.SanPhamDAO;
import com.example.fashion_app.model.LoaiSanPham;
import com.example.fashion_app.model.SanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;
import static androidx.recyclerview.widget.GridLayoutManager.*;
import static com.facebook.FacebookSdk.getApplicationContext;


public class SanPhamNamFragment extends Fragment implements SanPhamDAO.SanPhamInterface {
    SanPhamDAO sanPhamDAO;
    SanPhamAdapter sanPhamAdapter;
    ArrayList<SanPham> list;
    public static RecyclerView rcv_sanpham;
    FloatingActionButton fab_addSanPham;
    ArrayList<LoaiSanPham> listmaLoai = new ArrayList<>();
    String maLoai = "";
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    ImageView iv_addmenu;
    ArrayAdapter<LoaiSanPham> dataAdapter;

    private final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath;
    StorageReference storageReference;

    TextView toolbarTittle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_san_pham_nam, container, false);

        rcv_sanpham = view.findViewById(R.id.rcv_sanpham);
        fab_addSanPham = view.findViewById(R.id.fab_addSanPham);

        sanPhamDAO = new SanPhamDAO(getActivity(), this);
        list = new ArrayList<SanPham>();
        list.clear();
        list = sanPhamDAO.getAll();
        sanPhamAdapter = new SanPhamAdapter(getActivity(), list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        rcv_sanpham.setLayoutManager(gridLayoutManager);
        rcv_sanpham.setAdapter(sanPhamAdapter);
        list.clear();

        fab_addSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_add_sanpham);
                final Spinner spn_maloai = dialog.findViewById(R.id.spn_maloai);
                final EditText edtTenSp = dialog.findViewById(R.id.edtTenSanPham);
                final EditText edtgiaTien = dialog.findViewById(R.id.edtGiaTien);
                final EditText edtmoTa = dialog.findViewById(R.id.edtMoTa);
                final EditText edtsoLuong = dialog.findViewById(R.id.edtSoLuong);
                iv_addmenu = dialog.findViewById(R.id.iv_addmenu);

                final Button btnLuu = dialog.findViewById(R.id.btnLuu);
                final Button btnHuy = dialog.findViewById(R.id.btnHuy);

                //chon anh
                iv_addmenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        chooseImage();
                    }
                });


                databaseReference.child("LoaiSanPham").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        listmaLoai.clear();
                        for(DataSnapshot item : snapshot.getChildren()){
                            listmaLoai.add(item.getValue(LoaiSanPham.class));
                        }
                        dataAdapter.notifyDataSetChanged();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dataAdapter = new ArrayAdapter<LoaiSanPham>(getContext(),
                        android.R.layout.simple_spinner_item, listmaLoai);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spn_maloai.setAdapter(dataAdapter);
                spn_maloai.setSelection(checkPositionmaLoai(maLoai));


                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ma = spn_maloai.getSelectedItem().toString();
                        String tenSp = edtTenSp.getText().toString();
                        String giaTien = edtgiaTien.getText().toString();
                        String moTa = edtmoTa.getText().toString();
                        String soLuong = edtsoLuong.getText().toString();

                        if(tenSp.length() == 0){
                            Toast.makeText(getActivity(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                        }else{

                            SanPham sanPham = new SanPham(tenSp, giaTien, moTa, soLuong, null, ma);
                            list.clear();
//                            databaseReference.push().setValue(sanPham);
//                            databaseReference.addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                    for (DataSnapshot x : snapshot.getChildren()) {
//                                        SanPham sanPham = snapshot.getValue(SanPham.class);
//                                        sanPham.setMaSanPham(snapshot.getKey());
//                                        list.add(sanPham);
//                                        sanPhamAdapter.notifyDataSetChanged();
//                                    }
//
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError error) {
//
//                                }
//                            });
                            sanPhamDAO.insert(sanPham, filePath);
//
                            list.clear();
                            dialog.dismiss();
                        }

                    }
                });

                dialog.show();
            }
        });

        return view;
    }

    @Override
    public void notifyData() {
        sanPhamAdapter.notifyDataSetChanged();
    }



    public int checkPositionmaLoai(String strLoaiSanPham){
        for (int i = 0; i <listmaLoai.size(); i++){
            if (strLoaiSanPham.equals(listmaLoai.get(i).getTenLoai())){
                return i;
            }
        }
        return 0;
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                iv_addmenu.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


}