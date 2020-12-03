package com.example.tubes_kelg_c;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tubes_kelg_c.Model.User;
import com.example.tubes_kelg_c.splash.SplashActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfile extends AppCompatActivity {

    TextInputEditText username, fullname, notelp;
    Button btn_save;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private FirebaseUser user;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        String email = prefs.getString("email", "");
        String id = prefs.getString("userid", "");

        fullname = findViewById(R.id.fullname_edit);
        username = findViewById(R.id.username_edit);
        notelp = findViewById(R.id.notelp_edit);
        btn_save = findViewById(R.id.btn_simpan);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("users");
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userid = user.getUid();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("email").getValue().equals(email)){
                        fullname.setText(ds.child("fullname").getValue(String.class));
                        username.setText(ds.child("username").getValue(String.class));
                        notelp.setText(ds.child("notelp").getValue(String.class));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firebaseAuth != null){
                    reference.child(id).child("fullname").setValue(fullname.getEditableText().toString());
                    reference.child(id).child("username").setValue(username.getEditableText().toString());
                    reference.child(id).child("notelp").setValue(notelp.getEditableText().toString());

                    Toast.makeText(UpdateProfile.this, "Update Berhasil", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(UpdateProfile.this, MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(UpdateProfile.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}