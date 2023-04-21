package com.example.table;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class SlideInFromRightTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(@NonNull View view, float position) {
        int pageWidth = view.getWidth();
        if (position < -1) {
            // Page is off-screen to the left
            view.setAlpha(0f);
        } else if (position <= 0) {
            // Page is entering the screen from the left
            view.setAlpha(1f);
            view.setTranslationX(0f);
        } else if (position <= 1) {
            // Page is exiting the screen to the right
            view.setAlpha(1 - position);
            view.setTranslationX(pageWidth * -position);
        } else {
            // Page is off-screen to the right
            view.setAlpha(0f);
        }
    }


}
