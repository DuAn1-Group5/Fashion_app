package com.example.fashion_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.fashion_app.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slides_image = {
            R.drawable.ic_slide1,
            R.drawable.ic_slide2,
            R.drawable.ic_slide3
    };

    public String[] slide_headings = {
            "ĐA DẠNG",
            "TIẾT KIỆM",
            "ĐỔI TRẢ"
    };

    public String[] slide_decs = {
            "sam paf svlki alihcioahv aihcioahv aishcn iashcih oiashco[a ioasghcgausgc oasgciuagc oausgcuiagciug uasgciug",
            "sam paf svlki alihcioahv aihcioahv aishcn iashcih oiashco[a ioasghcgausgc oasgciuagc oausgcuiagciug uasgciug",
            "sam paf svlki alihcioahv aihcioahv aishcn iashcih oiashco[a ioasghcgausgc oasgciuagc oausgcuiagciug uasgciug"
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject (View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) v.findViewById(R.id.slide_image);
        TextView slideHeadings = (TextView) v.findViewById(R.id.slide_headings);
        TextView slideDescription = (TextView) v.findViewById(R.id.slide_decs);

        slideImageView.setImageResource(slides_image[position]);
        slideHeadings.setText(slide_headings[position]);
        slideDescription.setText(slide_decs[position]);

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
