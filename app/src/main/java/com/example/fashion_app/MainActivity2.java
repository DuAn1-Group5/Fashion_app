package com.example.fashion_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashion_app.adapter.HoaDonAdapter;
import com.example.fashion_app.fragment.GioHangFragment;
import com.example.fashion_app.fragment.LoaiSanPhamFragment;
import com.example.fashion_app.fragment.SanPhamNamFragment;
import com.example.fashion_app.fragment.ShowDanhGiaFragment;
import com.example.fashion_app.fragment.TabFragment;
import com.example.fashion_app.fragment.ThongkeFragment;
import com.example.fashion_app.model.DanhGia;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity2 extends AppCompatActivity {

    DrawerLayout drawer;
    TextView toolbar_title;
    ImageView iv_header_nav;
    TextView tv_NameHead, tv_EmailHead;
    FirebaseAuth firebaseAuth;
    DatabaseReference ref;
    public static String danhgia;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // anh xa view
        Toolbar myToolbar = findViewById(R.id.toolBar);
        toolbar_title = findViewById(R.id.toolbar_title);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ref = FirebaseDatabase.getInstance().getReference().child("DanhGia");



        View headerView = navigationView.getHeaderView(0);
        iv_header_nav = headerView.findViewById(R.id.iv_header_nav);
        tv_NameHead = headerView.findViewById(R.id.tv_NameHead);
        tv_EmailHead = headerView.findViewById(R.id.tv_EmailHead);

        setSupportActionBar(myToolbar);


        //myToolbar.setLogo(R.mipmap.app_book);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar_title.setText("Home");

        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new SanPhamNamFragment()).commit();



        ActionBarDrawerToggle toggle  = new ActionBarDrawerToggle(this, drawer,myToolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new SanPhamNamFragment()).commit();
                        Toast.makeText(MainActivity2.this, "Trang chủ", Toast.LENGTH_SHORT).show();
                        toolbar_title.setText("Home");
                        break;
                    case R.id.nav_cart:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new GioHangFragment()).commit();
                        Toast.makeText(MainActivity2.this, "Giỏ Hàng", Toast.LENGTH_SHORT).show();
                        toolbar_title.setText("Giỏ hàng");
                        break;
                    case R.id.nav_change:
                        Intent i = new Intent(MainActivity2.this, ChangePassActivity.class);
                        startActivity(i);
                        break;
                    case R.id.nav_gioithieu:
                        startActivity(new Intent(getApplicationContext(),GioiThieuActivity.class));
                        Toast.makeText(MainActivity2.this, "Giới Thiệu", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_caidat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new ShowDanhGiaFragment()).commit();
                        Toast.makeText(MainActivity2.this, "List FeedBack", Toast.LENGTH_SHORT).show();
                        toolbar_title.setText("Danh Sách Đánh Giá");
                        break;
                    case R.id.nav_hoadon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new TabFragment()).commit();
                        Toast.makeText(MainActivity2.this, "Hóa đơn", Toast.LENGTH_SHORT).show();
                        toolbar_title.setText("Hóa đơn");
                        break;
                    case R.id.nav_thoat:
                        final Dialog dialog = new Dialog(MainActivity2.this);
                        dialog.setContentView(R.layout.danhgia_fragment);

                        final Button btnLuu = dialog.findViewById(R.id.sendToFxbk);
                        final Button btnHuy = dialog.findViewById(R.id.btnBoQua);
                        final RadioButton rb1,rb2,rb3,rb4,rb5;
                        rb1 = dialog.findViewById(R.id.RBtoatvoi);
                        rb2 = dialog.findViewById(R.id.RBrathailong);
                        rb3 = dialog.findViewById(R.id.RBhailong);
                        rb4 = dialog.findViewById(R.id.RBkhonghailong);
                        rb5 = dialog.findViewById(R.id.RBtaotuk);
                        final EditText edtCment = dialog.findViewById(R.id.edtComment);




                        btnHuy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(MainActivity2.this, LoginActivity.class));
                                LoginActivity.matkhau = "";
                                Toast.makeText(MainActivity2.this, "Da Thoat", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                        btnLuu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(rb1.isChecked()){
                                    danhgia = "Tuyệt vời";
                                }else  if(rb2.isChecked()){
                                    danhgia = "Rất hài lòng";
                                }else  if(rb3.isChecked()){
                                    danhgia = "Hài lòng";
                                }else  if(rb4.isChecked()){
                                    danhgia = "Không hài lòng";
                                }else  if(rb5.isChecked()){
                                    danhgia = "Lòng không hài";
                                }
                                String Comment = edtCment.getText().toString();

                                DanhGia danhGia = new DanhGia(MainActivity2.danhgia,Comment,LoginActivity.tenNguoiDung,LoginActivity.Mail);
                                ref.push().setValue(danhGia);
                                Toast.makeText(MainActivity2.this, "Đã gữi", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(MainActivity2.this, LoginActivity.class));
                                LoginActivity.matkhau = "";
                                finish();
                            }
                        });

                        dialog.show();
                        break;


                }
                drawer.setSelected(true);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        try{
            layThongtinNguoiDung();
        }catch (Exception e){

        }

        onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        /*final MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("search here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.cart) {
            //code vô
            toolbar_title.setText("Giỏ hàng");
            HoaDonAdapter.maHoaDon = "";
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new GioHangFragment()).commit();
            Toast.makeText(this, "Giỏ Hàng", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void layThongtinNguoiDung(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("infomation");
        String email = bundle.getString("email", "Không có Email");
        String name = bundle.getString("name", "Không có thông tin");
        String avatar = bundle.getString("avatar", "");
        tv_EmailHead.setText(email);
        tv_NameHead.setText(name);

        Picasso.get()
                .load("https://graph.facebook.com/" + avatar+ "/picture?type=large")
                .into(iv_header_nav);
        //iv_header_nav.setImageBitmap(profilePic);
    }

//    public static Bitmap getFacebookProfilePicture(String userID) throws SocketException, SocketTimeoutException, MalformedURLException, IOException, Exception
//    {
//        String imageURL;
//
//        Bitmap bitmap = null;
//        imageURL = "http://graph.facebook.com/"+userID+"/picture?type=large";
//        InputStream in = (InputStream) new URL(imageURL).getContent();
//        bitmap = BitmapFactory.decodeStream(in);
//
//        return bitmap;
//    }


    @Override
    public void onBackPressed() {
        toolbar_title.setText("Home");
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new SanPhamNamFragment()).commit();
    }
}