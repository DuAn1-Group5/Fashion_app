package com.example.fashion_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fashion_app.model.NguoiDung;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ChangePassActivity extends AppCompatActivity {
    Button btnChangePass;
    EditText edtOldPas,edtNewPas, edtNewPasAgain;
    ArrayList<NguoiDung> listNd;
    //NguoiDungDAO nguoiDungDAO;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        btnChangePass = findViewById(R.id.btnChangePas);
        edtNewPas = findViewById(R.id.edtNewPas);
        edtOldPas = findViewById(R.id.edtOldPas);
        edtNewPasAgain = findViewById(R.id.edtNewPasAgain);
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String id = listNd.get().getMatKhau();
                String newPas = edtNewPas.getText().toString();
                String oldPas = edtOldPas.getText().toString();
                String newPassAgain = edtNewPasAgain.getText().toString();
                HashMap hashMap = new HashMap();
                hashMap.put("matKhau",newPas);
                Toast.makeText(ChangePassActivity.this, ""+LoginActivity.ma, Toast.LENGTH_SHORT).show();

                if (newPas.isEmpty() || oldPas.isEmpty() || newPassAgain.isEmpty()){
                    Toast.makeText(ChangePassActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }else if(!oldPas.equals(LoginActivity.matkhau)){
                    edtOldPas.setError("Mật khẩu cũ không đúng");
                    edtOldPas.requestFocus();
                }
                else if(!newPas.equals(newPassAgain)){
                    edtNewPasAgain.setError("Hai mật khẩu mới không trùng");
                        edtNewPasAgain.requestFocus();
                } else {
                    databaseReference.child(LoginActivity.ma).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(ChangePassActivity.this, "change pass complete", Toast.LENGTH_SHORT).show();
                            LoginActivity.matkhau = edtNewPasAgain.getText().toString();
                            Intent i = new Intent(ChangePassActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    });

                }

            }
        });
    }
}