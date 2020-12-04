package com.example.fashion_app.fragment;


import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.fashion_app.R;
import com.example.fashion_app.adapter.SanPhamAdapter;
import com.example.fashion_app.dao.SanPhamDAO;
import com.example.fashion_app.model.SanPham;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class UpdateMenu extends DialogFragment implements SanPhamDAO.SanPhamInterface{
    ImageView iv_addmenu;
    Uri imageUri;
    EditText edtTenSanPham, edtMoTa, edtGiaTien, edtSoluong;
    SanPhamDAO sanPhamDAO;
    ArrayList<SanPham> listSP;
    SanPhamAdapter sanPhamAdapter;
    String maloai;

    private final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath;

    private static final int GALLER_ACTION_PICK_CODE = 100;

    public UpdateMenu() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_sanpham, container, false);

        edtTenSanPham = view.findViewById(R.id.edtTenSanPham);
        edtMoTa = view.findViewById(R.id.edtMoTa);
        edtGiaTien = view.findViewById(R.id.edtGiaTien);
        Button btnLuu = view.findViewById(R.id.btnLuu);
        Button btnHuy = view.findViewById(R.id.btnHuy);
        iv_addmenu = view.findViewById(R.id.iv_addmenu);
        edtSoluong = view.findViewById(R.id.edtSoLuong);

        Spinner spn_maloai = view.findViewById(R.id.spn_maloai);


        listSP = new ArrayList<>();
        listSP.clear();
        sanPhamDAO = new SanPhamDAO(getActivity(), this);
        listSP = sanPhamDAO.getAll();
        sanPhamAdapter = new SanPhamAdapter(getActivity(), listSP);
        SanPhamNamFragment.rcv_sanpham.setAdapter(sanPhamAdapter);
        listSP.clear();


        // lay du lieu tu MenuAdapter qua de xu ly
        Bundle bundle = getArguments();
        String maSanPham = bundle.getString("masanpham");
        String tenSanPham = bundle.getString("tensanpham");
        String moTa = bundle.getString("mota");
        String giaTien = bundle.getString("giatien");
        String soluong = bundle.getString("soluong");
        String maLoai = bundle.getString("maloai");
        maloai = maLoai; // lay ma loai tu adapter put  qua de show du lieu len recyclerview cho ham capnhat() o ben duoi
        String hinhAnh = bundle.getString("hinhanh");

        edtTenSanPham.setText(tenSanPham);
        edtMoTa.setText(moTa);
        edtGiaTien.setText(giaTien);
        edtSoluong.setText(soluong);
        btnLuu.setText("Cập nhật");
        spn_maloai.setEnabled(false);
        try {
            Picasso.get().load(hinhAnh).into(iv_addmenu);
        }catch (Exception e){

        }

        iv_addmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });


        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sanPhamDAO = new SanPhamDAO(getActivity(), UpdateMenu.this::notifyData);

                if (filePath != null){
                    SanPham sanPham = new SanPham(edtTenSanPham.getText().toString(), edtGiaTien.getText().toString(), edtMoTa.getText().toString(), edtSoluong.getText().toString(),null, maloai);
                    sanPhamDAO.update(maSanPham, sanPham, filePath);
                    listSP.clear();
                }else{
                    SanPham sanPham = new SanPham(edtTenSanPham.getText().toString(), edtGiaTien.getText().toString(), edtMoTa.getText().toString(), edtSoluong.getText().toString(),hinhAnh, maloai);
                    sanPhamDAO.update(maSanPham, sanPham, filePath);
                    listSP.clear();
                }
                dismiss();
                listSP.clear();

            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }




    //Pick Image From Gallery
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

    @Override
    public void notifyData() {
        sanPhamAdapter.notifyDataSetChanged();

    }
}
