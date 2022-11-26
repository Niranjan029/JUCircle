package com.example.jucircle.Registration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jucircle.Users.Users;
import com.example.jucircle.databinding.ActivityRegistration1Binding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RegistrationActivity1 extends AppCompatActivity {
  ActivityRegistration1Binding binding ;
  FirebaseDatabase database ;
  FirebaseStorage storage ;
  ArrayList<Uri> images ;
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistration1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();
        storage= FirebaseStorage.getInstance();
       images = new ArrayList<>();
//
           database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                   .child("image"+33).addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                           Users users = snapshot.getValue(Users.class);
                             Picasso.get().load(users.getProfilePic()).into(binding.img1);
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
          database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                  .child("image"+34).addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {

                          Users users = snapshot.getValue(Users.class);
                          Picasso.get().load(users.getProfilePic()).into(binding.img2);
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });



        binding.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }

        });
        binding.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 34);
            }
        });
        binding.nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity1.this,RegistrationActivity2.class));
            }
        });
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null)
        {
            Uri sfile = data.getData() ;
            final StorageReference reference = storage.getReference().child("user_images").child(FirebaseAuth.getInstance().getUid()).child("image"+requestCode);

            if(requestCode==33) {
                binding.img1.setImageURI(sfile);
            }
            if(requestCode==34)
            {
                binding.img2.setImageURI(sfile);
            }
            reference.putFile(sfile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
//                            DatabaseReference database1 = database.getReference().child("images").child(FirebaseAuth.getInstance().getUid());
//                            HashMap<String,String> hashMap = new HashMap<>() ;
//                            hashMap.put("imglnk",uri.toString());
//                            database1.push().setValue(hashMap);

                            Users users = new Users(uri.toString());
                              String userid = "image"+requestCode ;
                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child((userid)).setValue(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(RegistrationActivity1.this, "image uploaded", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            });
        }
    }

}