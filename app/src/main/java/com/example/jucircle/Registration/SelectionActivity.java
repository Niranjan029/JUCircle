package com.example.jucircle.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.jucircle.MainActivity;
import com.example.jucircle.R;
import com.example.jucircle.UserMode.DateActivity;
import com.example.jucircle.databinding.ActivitySelectionBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.units.qual.A;

import java.util.Date;

public class SelectionActivity extends AppCompatActivity {
  ActivitySelectionBinding binding;
  FirebaseDatabase database ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();


   binding.btnnextpage.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {

           if(binding.rbdate.isChecked())
           {
               startActivity(new Intent(getApplicationContext(), MainActivity.class));
           }
           else if(binding.rbbff.isChecked())
           {
               startActivity(new Intent(getApplicationContext(), MainActivity.class));
           }
           else if(binding.rbcareer.isChecked())
           {
               startActivity(new Intent(getApplicationContext(), MainActivity.class));
           }
           else
           {
               binding.rbcareer.setError("please select mode");
           }
       }
   });

    }
    public void setMode(View view) {

        boolean isChecked =((RadioButton) view).isChecked();

        switch (view.getId())
        {
            case R.id.rbdate:
                if(isChecked)
                {
                    database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("Mode").setValue("Date");
                    break;
                }

            case R.id.rbbff:
                if(isChecked)
                {
                    database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("Mode").setValue("BFF");
                    break;
                }

            case R.id.rbcareer:
                if(isChecked)
                {
                    database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("Mode").setValue("Bizz");
                    break;
                }

        }

    }
}