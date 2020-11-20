package com.example.fashion_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText edtUsername1, edtPassword1, edtEmail;
    Button btnDangKi1, btnDangNhap1;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword1 = findViewById(R.id.edtPassword1);
        btnDangKi1 = findViewById(R.id.btnDangKi1);
        //progressBar = findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        btnDangKi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String memail = edtEmail.getText().toString().trim();
                String password = edtPassword1.getText().toString().trim();
                if (TextUtils.isEmpty(memail)){
                    edtEmail.setError("vui long nhap email");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    edtPassword1.setError("vui long nhap password");
                    return;
                }
                if (password.length()<5){
                    edtPassword1.setError("password tren 5 ki tu ");
                    return;
                }

//firebase
                fAuth.createUserWithEmailAndPassword(memail, password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Succesed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        } else {
                            Toast.makeText(RegisterActivity.this, "Loi" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}