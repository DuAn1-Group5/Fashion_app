package com.example.fashion_app.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.fashion_app.R;
import com.example.fashion_app.adapter.HoaDonAdapter;
import com.example.fashion_app.dao.HoaDonDAO;
import com.example.fashion_app.model.HoaDon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;


public class ThongkeFragment extends Fragment implements HoaDonDAO.HoaDonInterface {
    TextView tvsetThag;
    Button btnTuThg, btnDenThg;
    HoaDonAdapter adapter;
    HoaDonDAO dao;
    ArrayList<HoaDon> listHD;
    RecyclerView rcv_thongke;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("HoaDon");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        // Inflate the layout for this fragment
        tvsetThag = view.findViewById(R.id.setThangNd);
        btnTuThg = view.findViewById(R.id.btntuThg);
        btnDenThg = view.findViewById(R.id.btndenThag);
        rcv_thongke = view.findViewById(R.id.RCVTkNd);

        final Calendar cldr = Calendar.getInstance();
        final int day = cldr.get(Calendar.DAY_OF_MONTH);
        final int month = cldr.get(Calendar.MONTH);
        final int year = cldr.get(Calendar.YEAR);

        listHD = new ArrayList<>();
        dao = new HoaDonDAO(getContext(), this);


        btnTuThg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePicker = new DatePickerDialog(getContext(), R.style.DatePicker, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (monthOfYear < 9 && dayOfMonth < 10) {
                            btnTuThg.setText( "0" + dayOfMonth + "-" + "0" + (monthOfYear + 1) + "-" +year);
                        } else if (monthOfYear < 9) {
                            btnTuThg.setText( dayOfMonth + "-" + "0" + (monthOfYear + 1) + "-" +year);
                        } else if (dayOfMonth < 10) {
                            btnTuThg.setText( "0" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        } else {
                            btnTuThg.setText( dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        btnDenThg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePicker = new DatePickerDialog(getContext(), R.style.DatePicker, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (monthOfYear < 9 && dayOfMonth < 10) {
                            btnDenThg.setText( "0" + dayOfMonth + "-" + "0" + (monthOfYear + 1) + "-" +year);
                        } else if (monthOfYear < 9) {
                            btnDenThg.setText( dayOfMonth + "-" + "0" + (monthOfYear + 1) + "-" +year);
                        } else if (dayOfMonth < 10) {
                            btnDenThg.setText( "0" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        } else {
                            btnDenThg.setText( dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        tvsetThag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listHD.clear();
                Query Qdatas = databaseReference.orderByChild("ngay").startAt(btnTuThg.getText().toString()).endAt(btnDenThg.getText().toString());

                Qdatas.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        int sum = 0;
                        for (DataSnapshot ds : snapshot.getChildren()){
                            Map<String,Object> map = (Map<String,Object>) ds.getValue();
                            Object price = map.get("tongTien");
                            Object trangThai = map.get("trangThai");
                            Object ngay = map.get("ngay");
                            Object maKhachhang = map.get("maKhachhang");
                            Object diaChiGiaoHang = map.get("diaChiGiaoHang");

                            HoaDon hoaDon = new HoaDon(String.valueOf(ngay), String.valueOf(trangThai), String.valueOf(maKhachhang), String.valueOf(price), String.valueOf(diaChiGiaoHang));
                            listHD.add(hoaDon);
                            Log.d("TAG", "onDataChange: "+price);
                            if (trangThai.equals("Đã thanh toán")){
                                double tValue = Double.parseDouble(String.valueOf(price));
                                sum += tValue;


                                //listHD = dao.getTrangThaiHoaDon("Đã thanh toán");

                                adapter = new HoaDonAdapter(getActivity(), listHD);
                                rcv_thongke.setLayoutManager(new LinearLayoutManager(getContext()));
                                rcv_thongke.setAdapter(adapter);
                            }else{

                            }

                        }
                        if (tvsetThag.equals("")){
                            tvsetThag.setText("Không có hóa đơn");
                        }else{
                            tvsetThag.setText(sum+"VND");

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });




//show hoa don da thanh toan



        return view;
    }

    @Override
    public void notifyData() {
        adapter.notifyDataSetChanged();
    }

    public void filter(String text){
        ArrayList<HoaDon> listfilter = new ArrayList<>();

        for (HoaDon item : listHD){
            if (item.getNgay().toUpperCase().contains(text.toUpperCase())){
                listfilter.add(item);
            }
            if (item.getTrangThai().toUpperCase().contains(text.toUpperCase())){
                listfilter.add(item);
            }

        }
        adapter.filterlist(listfilter);
    }
}