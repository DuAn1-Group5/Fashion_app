package com.example.fashion_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashion_app.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    TextView toolbar_title;
    ImageView iv_header_nav;
    TextView tv_NameHead, tv_EmailHead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // anh xa view
        Toolbar myToolbar = findViewById(R.id.toolBar);
        toolbar_title = findViewById(R.id.toolbar_title);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);



        View headerView = navigationView.getHeaderView(0);
        iv_header_nav = headerView.findViewById(R.id.iv_header_nav);
        tv_NameHead = headerView.findViewById(R.id.tv_NameHead);
        tv_EmailHead = headerView.findViewById(R.id.tv_EmailHead);

        setSupportActionBar(myToolbar);


        //myToolbar.setLogo(R.mipmap.app_book);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar_title.setText("Home");

        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new HomeFragment()).commit();



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
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new HomeFragment()).commit();
                        Toast.makeText(MainActivity.this, "Trang chủ", Toast.LENGTH_SHORT).show();
                        toolbar_title.setText("Home");
                        break;
                    case R.id.nav_cart:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Fragment1()).commit();
                        Toast.makeText(MainActivity.this, "Giỏ Hàng", Toast.LENGTH_SHORT).show();
                        toolbar_title.setText("Giỏ hàng");
                        break;
                    case R.id.nav_list:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Fragment2()).commit();
                        //Toast.makeText(MainActivity.this, "Quản Lý Chi", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_change:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Fragment3()).commit();
                        //Toast.makeText(MainActivity.this, "Thống Kê", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_gioithieu:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Fragment3()).commit();
                        //Toast.makeText(MainActivity.this, "Thống Kê", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_caidat:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Fragment3()).commit();
                        //Toast.makeText(MainActivity.this, "Thống Kê", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_thoat:
                        finish();
                        Toast.makeText(MainActivity.this, "Da Thoat", Toast.LENGTH_SHORT).show();
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
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new HomeFragment()).commit();
    }
}