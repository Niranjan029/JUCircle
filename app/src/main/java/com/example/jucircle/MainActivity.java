package com.example.jucircle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.jucircle.databinding.ActivityMainBinding;

public class MainActivity extends NavigationActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(this);
        View v=inflater.inflate(R.layout.activity_main,null ,false);
        drawer.addView(v,0);


    }
}