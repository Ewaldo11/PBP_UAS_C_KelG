package com.example.tubes_kelg_c.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tubes_kelg_c.MainActivity;
import com.example.tubes_kelg_c.Model.User;
import com.example.tubes_kelg_c.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class SplashActivity extends AppCompatActivity{

    TextInputEditText username, notelp, email, password, fullname;
    TextView result;
    Button btn_signup, btn_login;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private FirebaseUser user;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        notelp = findViewById(R.id.notelp);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_signup = findViewById(R.id.btn_signup);
        btn_login = findViewById(R.id.btn_login);
        result= findViewById(R.id.emPass);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userid = user.getUid();

//        firebaseAuth = FirebaseAuth.getInstance();
//        if (firebaseAuth != null) {
//            userid = firebaseAuth.getUid();
//        }
//        userid = firebaseAuth.getCurrentUser().getUid();
//        userid = firebaseAuth.getCurrentUser();
//        id = userid.getUid();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeMD5Hash(password.toString());
                String emailuser = email.getText().toString();
                String pass = password.getText().toString();
                //String emPass = result.getText().toString();

                SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("email", emailuser);
                editor.putBoolean("login", true);
                editor.putString("userid", String.valueOf(userid));
                editor.apply();

                if(fullname.getText().toString().isEmpty()){
                    fullname.setError("Fullname kosong");
                    fullname.requestFocus();
                }else if(username.getText().toString().isEmpty()){
                    username.setError("Username kosong");
                    username.requestFocus();
                }else if(notelp.getText().toString().isEmpty()){
                    notelp.setError("Telepon kosong");
                    notelp.requestFocus();
                }else if(email.getText().toString().isEmpty()){
                    email.setError("Email kosong");
                    email.requestFocus();
                }else if(password.getText().toString().isEmpty()){
                    password.setError("Username kosong");
                    password.requestFocus();
                }else{
                    firebaseAuth.createUserWithEmailAndPassword(emailuser,pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        firebaseAuth.getCurrentUser().sendEmailVerification()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            Toast.makeText(SplashActivity.this, "Register berhasil, silahkan verifikasi email sebelum login",
                                                                    Toast.LENGTH_LONG).show();
                                                            email.setText("");
                                                            password.setText("");
                                                            fullname.setText("");
                                                            notelp.setText("");
                                                            username.setText("");
                                                        }else{
                                                            Toast.makeText(SplashActivity.this, task.getException().getMessage(),
                                                                    Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });
                                    }else{
                                        Toast.makeText(SplashActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    reference = firebaseDatabase.getReference("users");

                    String nama = fullname.getEditableText().toString();
                    String user = username.getEditableText().toString();
                    String telp = notelp.getEditableText().toString();
                    String email1 = email.getEditableText().toString();
                    //String password1 = password.getEditableText().toString();
                    String emPass = result.getText().toString();

                    User userDatabase= new User(nama, user, telp, email1, emPass);
                    reference.child(userid).setValue(userDatabase);
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
    public void computeMD5Hash(String password){
        try{
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer MD5Hash = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++){
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }
            result.setText(MD5Hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}