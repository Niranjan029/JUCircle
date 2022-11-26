package com.example.jucircle.Registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jucircle.databinding.ActivityRegistration3Binding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity3 extends AppCompatActivity {
 ActivityRegistration3Binding binding ;
 FirebaseDatabase database ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistration3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        database= FirebaseDatabase.getInstance();

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                         binding.etuserName.setText(snapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .child("userBio").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        binding.etuserbio.setText(snapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        binding.btnadddetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.etuserName.getText().toString().isEmpty())
                {
                    binding.etuserName.setError("Please Enter Your Name");
                }
                if(binding.etuserbio.getText().toString().isEmpty())
                {
                    binding.etuserbio.setError("Please Enter About Yourself ");
                }
                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .child("userName").setValue(binding.etuserName.getText().toString());
                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .child("userBio").setValue(binding.etuserbio.getText().toString());
            }
        });

    }
}