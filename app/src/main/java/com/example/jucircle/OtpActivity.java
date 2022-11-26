package com.example.jucircle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.jucircle.databinding.ActivityOtpBinding;
import com.hbb20.CCPCountryGroup;
import com.hbb20.CountryCodePicker;

public class OtpActivity extends AppCompatActivity {
  ActivityOtpBinding binding ;
  CountryCodePicker ccp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ccp=findViewById(R.id.ccp) ;
        binding.btnotpsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.editphone.getText().length()!=10)
                {
                     binding.editphone.setError("please enter an valid mobile number");
                }
                else{
                    ccp.registerCarrierNumberEditText(binding.editphone);
                    Intent intent =  new Intent(getApplicationContext(),GenerateOtpActivity.class);
                    intent.putExtra("phoneNumber",ccp.getFullNumberWithPlus().trim());
                    startActivity(intent);
                }

            }
        });

    }
}