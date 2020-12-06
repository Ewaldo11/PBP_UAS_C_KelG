package com.example.tubes_kelg_c.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tubes_kelg_c.HomeFragment;
import com.example.tubes_kelg_c.MainActivity;
import com.example.tubes_kelg_c.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private TextInputEditText email, password;
    FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private FirebaseUser user;
    String emailuser, userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_loginpage);
        email = findViewById(R.id.emaillogin);
        password = findViewById(R.id.passwordlogin);

        emailuser = email.getText().toString();

        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email", emailuser);
        editor.putBoolean("login", true);

        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userid = user.getUid();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.getText().toString().isEmpty()){
                    email.setError("Email kosong");
                    email.requestFocus();
                }else if(password.getText().toString().isEmpty()){
                    password.setError("Pasword kosong");
                    password.requestFocus();
                }else{
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        if (firebaseAuth.getCurrentUser().isEmailVerified()){
                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        }else{
                                            Toast.makeText(LoginActivity.this, "Verifikasi email anda terlebih dahulu",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }else{
                                        Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }

            }
        });
    }
}