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
    EditText edtUsername1, edtPass, edtEmail;
    Button btnDangKi1, btnDangNhap1;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPassword1);
        btnDangKi1 = findViewById(R.id.btnDangKi1);
        //progressBar = findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        btnDangKi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name1 = edtEmail.getText().toString();
                String pwd1 = edtPass.getText().toString();
                if (Name1.isEmpty()){
                    edtEmail.setError("Không để trống EMAIL");
                    edtEmail.requestFocus();
                }
                else if (pwd1.isEmpty()){
                    edtPass.setError("Không để trống PASSWORD");
                    edtPass.requestFocus();
                }
                else if (Name1.isEmpty() && pwd1.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Không để trống", Toast.LENGTH_SHORT).show();
                }
                else if (!(Name1.isEmpty() && pwd1.isEmpty())){
                    firebaseAuth.createUserWithEmailAndPassword(Name1,pwd1).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Đăng ký không thành công, thử lại", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}