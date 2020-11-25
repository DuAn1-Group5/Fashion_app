package com.example.fashion_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.fashion_app.adapter.SliderAdapter;

public class Wellcomactivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout linearLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;
    private Button btnL,btnR,btnSkip;
    private int CurrentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcomactivity);
        viewPager = findViewById(R.id.slideVP);
        linearLayout = findViewById(R.id.dots);
        btnL = findViewById(R.id.btnL);
        btnR = findViewById(R.id.btnR);
        btnSkip = findViewById(R.id.btnSkip);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);

        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(CurrentPage - 1);
            }
        });

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CurrentPage == mDots.length -1) {
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                }else {
                    viewPager.setCurrentItem(CurrentPage + 1);
                }
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
    }

    public void addDotsIndicator(int potion){
        mDots = new TextView[3];
        linearLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.grey));

            linearLayout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            mDots[potion].setTextColor(getResources().getColor(R.color.black));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);

            CurrentPage = i;
            if (i == 0){
                btnR.setEnabled(true);
                btnL.setEnabled(false);
                btnL.setVisibility(View.INVISIBLE);

                btnR.setText("Next");
                btnL.setText("");
            }
            else if (i == mDots.length - 1){
                btnR.setEnabled(true);
                btnL.setEnabled(true);
                btnL.setVisibility(View.VISIBLE);

                btnR.setText("Finish");
                btnL.setText("Back");
            }
            else {
                btnR.setEnabled(true);
                btnL.setEnabled(true);
                btnL.setVisibility(View.VISIBLE);

                btnR.setText("Next");
                btnL.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


}
