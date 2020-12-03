package com.example.tubes_kelg_c.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tubes_kelg_c.MainActivity;
import com.example.tubes_kelg_c.R;

public class CheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        final Boolean isloged = prefs.getBoolean("isLoged", false);

        Thread timerThread = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    if (isloged == true){
                        Intent i = new Intent(CheckActivity.this, MainActivity.class);
                        startActivity(i);
                    }else{
                        Intent i = new Intent(CheckActivity.this, SplashActivity.class);
                        startActivity(i);
                    }
                }
            }
        };
        timerThread.start();
    }
}