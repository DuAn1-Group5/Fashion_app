package com.example.fashion_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;


import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.fashion_app.model.NguoiDung;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    CallbackManager callbackManager = CallbackManager.Factory.create();
    EditText edtUsername,edtPassword;
    TextView txtRegister;
    Button btnDangNhap, btnDangNhapFb;
    LoginButton loginButton;
    String email;
    String name;
    String image;
    String profilePicUrl;
    FirebaseAuth firebaseAuth;

    public static String ma;
    public static String matkhau;
    public static String tenNguoiDung;
    public static String chucVu;
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //anh xa
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangNhapFb = findViewById(R.id.btnDangNhapFb);
        loginButton =findViewById(R.id.login_button);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        txtRegister = findViewById(R.id.txtRegister);
        firebaseAuth = FirebaseAuth.getInstance();

        //Our custom Facebook button
        btnDangNhapFb = (Button) findViewById(R.id.btnDangNhapFb);



        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions("public_profile");
        loginButton.setReadPermissions("email");
        loginButton.setReadPermissions("user_birthday");

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
//AuthStateListener
        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                else {
                    Toast.makeText(LoginActivity.this, "Hãy Đăng Nhập", Toast.LENGTH_SHORT).show();
                }
            }
        };


        // Callback registration
        // bam vao thi dang nhap bang facebook
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.getJSONObject().toString());
                                // Application code
                                try{
                                    email = object.getString("email");
                                }catch (Exception e){
                                    // do something
                                }
                                try{
                                    //String birthday = object.getString("birthday"); // 01/31/1980 format
                                    name = object.getString("name");
                                    //profilePicUrl = object.getJSONObject("picture").getJSONObject("data").getString("url");
                                }catch (Exception e){
                                    // do something
                                }
                                try{
                                    image = loginResult.getAccessToken().getUserId();
                                    Log.v("LoginActivity", image+"id");

                                }catch (Exception e){
                                    // do something
                                }

                                Intent i = new Intent(getBaseContext(), MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("email", email);
                                bundle.putString("name", name);
                                bundle.putString("avatar", image);

                                i.putExtra("infomation", bundle);
                                startActivity(i);
                                Toast.makeText(getBaseContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday, picture.type(large)");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                Query query = databaseReference.child("users").orderByChild("tenNguoidung").equalTo(edtUsername.getText().toString().trim());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot user : dataSnapshot.getChildren()) {
                                NguoiDung users = user.getValue(NguoiDung.class);
                                users.setMaNguoidung(user.getKey());
                                if (users.getMatKhau().equals(edtPassword.getText().toString().trim())) {
//                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                    startActivity(intent);

                                   if (users.getChucvu().equalsIgnoreCase("admin")){
                                       Intent i = new Intent(getBaseContext(), MainActivity.class);
                                       Bundle bundle = new Bundle();
                                       String email = users.getEmail();
                                       String name = users.getTenNguoidung();
                                       tenNguoiDung = name;
                                       String image = "";
                                       bundle.putString("email", email);
                                       bundle.putString("name", users.getHoTenDayDu());
                                       bundle.putString("avatar", image);
                                       ma = users.getMaNguoidung();
                                       matkhau = users.getMatKhau();
                                       chucVu = users.getChucvu();
                                       i.putExtra("infomation", bundle);
                                       startActivity(i);
                                       Toast.makeText(getBaseContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                       finish();
                                   }else {
                                       Intent i = new Intent(getBaseContext(), MainActivity2.class);
                                       Bundle bundle = new Bundle();
                                       String email = users.getEmail();
                                       String name = users.getTenNguoidung();
                                       tenNguoiDung = name;
                                       String image = "";
                                       bundle.putString("email", email);
                                       bundle.putString("name", users.getHoTenDayDu());
                                       bundle.putString("avatar", image);
                                       ma = users.getMaNguoidung();
                                       matkhau = users.getMatKhau();
                                       chucVu = users.getChucvu();
                                       i.putExtra("infomation", bundle);
                                       startActivity(i);
                                       Toast.makeText(getBaseContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                       finish();
                                   }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Password is wrong", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }


    public void printHashKey(Context pContext) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("TAG", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("TAG", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("TAG", "printHashKey()", e);
        }
    }


    // ham nay de khi bam vao nut dang nhap bang facebook thi no se nhan nut com.facebook.button
    public void onClickFacebookButton(View view) {
        if (view == btnDangNhapFb) {
            loginButton.performClick();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        firebaseAuth.addAuthStateListener(authStateListener);
        super.onStart();
    }
}