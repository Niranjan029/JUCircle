package com.example.jucircle.Registration;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.jucircle.MainActivity;
import com.example.jucircle.R;
import com.example.jucircle.Users.Users;
import com.example.jucircle.databinding.ActivityRegistration1Binding;
import com.example.jucircle.databinding.ActivityRegistration2Binding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class RegistrationActivity2 extends AppCompatActivity {
    ActivityRegistration2Binding binding ;
    private DatePickerDialog datePickerDialog ;
    FirebaseDatabase database ;
    private String Gender ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistration2Binding.inflate(getLayoutInflater());
        database = FirebaseDatabase.getInstance();
        setContentView(binding.getRoot());

      database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                      .child("DateOfBirth").addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot snapshot) {
                        binding.etdob.setText(snapshot.getValue().toString());
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError error) {

                  }
              });

         initDatePickerDialog();
        binding.etdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    datePickerDialog.show();
            }
        });
        binding.etdob.setText(getTodayDate());

        binding.imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.rbMale.isChecked() || binding.rbfemale.isChecked() || binding.rbNonbinary.isChecked())
                {
                    startActivity(new Intent(RegistrationActivity2.this, RegistrationActivity3.class));
                }
                else
                {
                    binding.rbMale.setError("please select gender");
                }
            }
        });


    }

    public void setGender(View view) {

        boolean isChecked =((RadioButton) view).isChecked();

        switch (view.getId())
        {
            case R.id.rbMale:
                if(isChecked)
                    database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("Gender").setValue("Male");
                    break;
            case R.id.rbfemale:
                if(isChecked)
                    database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("Gender").setValue("FeMale");
                break;
            case R.id.rbNonbinary:
                if(isChecked)
                    database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("Gender").setValue("Nonbinary");
                break;
        }

    }

    private String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        int year =calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month=month+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1 ;
                String date = makeDateString(day,month,year);
                binding.etdob.setText(date);

                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .child("DateOfBirth").setValue(date)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(RegistrationActivity2.this, "Date uploaded", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year =calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT ;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,day,month,year);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {

        return getMonthformat(month) + " " + day +" "+year ;
    }

    private String getMonthformat(int month) {

        if(month==1)
            return "JAN";
        if(month==2)
            return "FEB";
        if(month==3)
            return "MAR";
        if(month==4)
            return "APR";
        if(month==5)
            return "MAY";
        if(month==6)
            return "JUN";
        if(month==7)
            return "JUL";
        if(month==8)
            return "AUG";
        if(month==9)
            return "SEP ";
        if(month==10)
            return "OCT";
        if(month==11)
            return "NOV";
        if(month==12)
            return "DEC";

        return "JAN" ;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}