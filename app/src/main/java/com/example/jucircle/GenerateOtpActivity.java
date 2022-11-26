package com.example.jucircle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jucircle.Registration.RegistrationActivity1;
import com.example.jucircle.databinding.ActivityGenerateOtpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class GenerateOtpActivity extends AppCompatActivity {
    ActivityGenerateOtpBinding binding ;
  String phoneNumber ;
  FirebaseAuth mauth ;
  String otp ;
  ProgressDialog dialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGenerateOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         dialog = new ProgressDialog(this);
         dialog.setTitle("logging in");
        dialog.setMessage("logging in into your account");
        mauth=FirebaseAuth.getInstance();
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        initializeotp();
        binding.btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(binding.editotptext.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Blank Field can not processed ", Toast.LENGTH_SHORT).show();
                }
                else if(binding.editotptext.getText().toString().length()!=6)
                {
                    Toast.makeText(getApplicationContext(), "not valid otp ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dialog.show();
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otp,binding.editotptext.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });
    }
    private void initializeotp()
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        otp=s;
                        Log.i("method","code sent");
                    }
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                        Log.i("method","automatic");
                    }
                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(GenerateOtpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
        });
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
    {
        mauth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                dialog.dismiss();
                if(task.isSuccessful())
                {
                    startActivity(new Intent(getApplicationContext(), RegistrationActivity1.class));
                    finish();
                }
                else
                {
                    Toast.makeText(GenerateOtpActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
