package com.example.table;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class VerticalViewPager extends ViewPager {

    public VerticalViewPager(Context context) {
        super(context);
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private MotionEvent swapXY(MotionEvent event) {
        float newX = (event.getY() / getHeight()) * getWidth();
        float newY = (event.getX() / getWidth()) * getHeight();
        event.setLocation(newX, newY);
        return event;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercepted = super.onInterceptTouchEvent(swapXY(event));
        swapXY(event);
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(swapXY(event));
    }

    @Override
    public void setPageTransformer(boolean reverseDrawingOrder, PageTransformer transformer) {
        super.setPageTransformer(reverseDrawingOrder, transformer);
        setPageTransformer(true, new VerticalPageTransformer(transformer));
    }

    private class VerticalPageTransformer implements PageTransformer {
        private PageTransformer transformer;

        public VerticalPageTransformer(PageTransformer transformer) {
            this.transformer = transformer;
        }

        @Override
        public void transformPage(View view, float position) {
            transformer.transformPage(view, position);
            final float yPosition = position * view.getHeight();
            view.setTranslationY(yPosition);
        }
    }
}

