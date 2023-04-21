package com.example.table;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {


    private TextView mTextView1;
    private TextView mTextView2;
    private int mLayoutId;
    public static final String EXTRA_LAYOUT_ID = "extraLayoutId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutId = getIntent().getIntExtra(MainActivity.EXTRA_LAYOUT_ID, 0);


        switch (layoutId) {
            case 1:
                setContentView(R.layout.activity_detail_1);
                break;
            case 2:
                setContentView(R.layout.activity_detail_2);
                break;
            case 3:
                setContentView(R.layout.activity_detail_3);
                break;
            default:
                setContentView(R.layout.activity_detail);
        }


    }
}
