package com.example.table;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class NewActivity extends AppCompatActivity {
    public static final String EXTRA_LAYOUT_ID = "layout_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        int layoutId = getIntent().getIntExtra(EXTRA_LAYOUT_ID, R.layout.activity_main);
        Toast.makeText(this, layoutId, Toast.LENGTH_SHORT).show();
        switch (layoutId) {
            case 1:
                setContentView(R.layout.activity_detail_1);
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                setContentView(R.layout.activity_detail_2);
                break;
            case 3:
                setContentView(R.layout.activity_detail_3);
                break;
            // Add additional cases for other layout IDs as needed
        }
    }

}
