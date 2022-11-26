package com.example.jucircle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class FirstPageActivity extends AppCompatActivity {
   Button btnsignin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        getSupportActionBar().hide();
            btnsignin = findViewById(R.id.btnsignin);
            if(FirebaseAuth.getInstance().getCurrentUser()!=null)
            {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
            btnsignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),OtpActivity.class));
                }
            });

    }
}