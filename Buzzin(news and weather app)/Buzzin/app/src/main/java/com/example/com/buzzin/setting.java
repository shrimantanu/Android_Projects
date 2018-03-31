package com.example.com.buzzin;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_setting);
            ActionBar ac = this.getSupportActionBar();
            if (ac != null) {
                ac.setDisplayHomeAsUpEnabled(true);
            }
        }catch (Exception e){
Log.e("ashj",e.getMessage());
        }
    }
}