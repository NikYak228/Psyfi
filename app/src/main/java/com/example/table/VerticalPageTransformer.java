package com.example.table;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.viewpager.widget.ViewPager;

public class VerticalPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.75f;

    @Override
    public void transformPage(View view, float position) {
        if (position < -1) {
            view.setAlpha(0);
        } else if (position <= 0) {
            view.setAlpha(1);
            view.setTranslationX(view.getWidth() * -position);
            float yPosition = position * view.getHeight();
            view.setTranslationY(yPosition);
            view.setScaleX(1);
            view.setScaleY(1);
        } else if (position <= 1) {
            view.setAlpha(1);
            view.setTranslationX(view.getWidth() * -position);
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        } else {
            view.setAlpha(0);
        }

        // Add animation code for cardview slide up
        if (position == 0f) { // card is centered
            CardView cardView = view.findViewById(R.id.card_view); // assuming cardview has id card_view
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ObjectAnimator slideUp = ObjectAnimator.ofFloat(
                            cardView,
                            "translationY",
                            0f,
                            -cardView.getHeight()
                    );
                    slideUp.setDuration(500);
                    slideUp.setInterpolator(new DecelerateInterpolator());
                    slideUp.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            // do something after animation ends
                        }
                    });
                    slideUp.start();
                }
            });
        }
    }
}

