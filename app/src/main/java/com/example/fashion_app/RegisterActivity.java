package com.example.fashion_app;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashion_app.model.NguoiDung;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class RegisterActivity extends AppCompatActivity {
    EditText edtregUsername, edtregPassword, edtregEmail, edtregPasswordAgain;
    Button btnregDangKi;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    String TAG = "RegisterActivity";
    private NguoiDung user;

    @Override
    protected void onCreate(Bundle savInstanceState) {
        super.onCreate(savInstanceState);
        setContentView(R.layout.activity_register);
        edtregUsername = findViewById(R.id.edtregUsername);
        edtregEmail = findViewById(R.id.edtregEmail);
        edtregPassword = findViewById(R.id.edtregPassword);
        btnregDangKi = findViewById(R.id.btnregDangKi);
        edtregPasswordAgain = findViewById(R.id.edtregPasswordAgain);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference("users");
        mAuth = FirebaseAuth.getInstance();


        btnregDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtregUsername.getText().toString();
                String password = edtregPassword.getText().toString();
                String passwordAgain = edtregPasswordAgain.getText().toString();
                String email = edtregEmail.getText().toString();

                if(userName.isEmpty() || password.isEmpty() || passwordAgain.isEmpty() || email.isEmpty()){
                    if (userName.isEmpty()){
                        edtregUsername.setError("Không được bỏ trống tên đăng nhập");
                    }else if (password.isEmpty()){
                        edtregPassword.setError("Không được bỏ trống mật khẩu");
                    }else if (passwordAgain.isEmpty()){
                        edtregPasswordAgain.setError("Không được bỏ trống nhập lại mật khẩu");
                    }else if(email.isEmpty()){
                        edtregEmail.setError("Không được bỏ trống email");
                    }

                }else if(!passwordAgain.equals(password)){
                    edtregPasswordAgain.setError("Hai mật khẩu không trùng nhau");
                }else{
                    user = new NguoiDung(userName, email,"user", password);
                    registerUser(email,password);
                }

//                if(edtregUsername.getText().toString().isEmpty()){
//                    edtregUsername.setError("Vui Long Nhap Username");
//                    edtregUsername.requestFocus();
//                    return;
//                }
//                if(edtregEmail.getText().toString().isEmpty()){
//                    edtregEmail.setError("Vui Long Nhap Email");
//                    edtregEmail.requestFocus();
//                    return;
//                }
//                if(edtregPassword.getText().toString().isEmpty()){
//                    edtregPassword.setError("Vui Long Nhap Password");
//                    edtregPassword.requestFocus();
//                    return;
//                }
//                if(edtregPassword.getText().toString().length()<6){
//                    edtregPassword.setError("Vui Long Nhap Password Nhieu Hon 6 Ki Tu");
//                    edtregPassword.requestFocus();
//                    return;
//                }
//
//                if (!password.equals(passwordAgain) || passwordAgain.length() == 0){
//                    edtregPassword.setError("Không trùng mật khẩu");
//                    edtregPassword.requestFocus();
//                }else{
//                    user = new NguoiDung(userName, email,"user", password);
//                    registerUser(email,password);
//                }
            }
        });
    }
    public void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //
                            Log.d(TAG, "Dang Ki Thanh Cong ");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            //
                            Log.w(TAG, "Dang Ki That Bai", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void updateUI(FirebaseUser currentUser) {
        String keyID = mDatabase.push().getKey();
        mDatabase.child(keyID).setValue(user); //
        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }
}
