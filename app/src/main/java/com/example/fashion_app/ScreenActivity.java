package com.example.fashion_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ScreenActivity extends AppCompatActivity {
    ImageView ivlg;
    Animation tranlatey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

//        ivlg = findViewById(R.id.ivLogo);
//        tranlatey = AnimationUtils.loadAnimation(ScreenActivity.this, R.anim.translatey);
//        ivlg.startAnimation(tranlatey);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        },2200);
    }
}