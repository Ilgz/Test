package com.example.player2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.player2.PokazFirst;
import com.example.player2.R;

public class GlavStranitsa extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glav_stranitsa);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


getSupportActionBar().setTitle("Медиа");





    }








    public void hugo(View view) {
        Intent intent = new Intent(GlavStranitsa.this, PokazFirst.class);
        startActivity(intent);
    }

    public void hugo2(View view) {
        Intent intent = new Intent(GlavStranitsa.this, PokazSecond.class);
        startActivity(intent);
    }

    public void hugo3(View view) {
        Intent intent = new Intent(GlavStranitsa.this, PokazThird.class);
        startActivity(intent);
    }





}
