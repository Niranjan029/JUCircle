package com.example.jucircle.UserMode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.jucircle.R;
import com.example.jucircle.databinding.ActivityDateBinding;

public class DateActivity extends AppCompatActivity {
   ActivityDateBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}