package com.example.player2.main_windows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.player2.R;

public class Logo_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_);
        StartA();
        getSupportActionBar().hide();
     }
    private void StartA(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(Logo_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }
}