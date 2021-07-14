package com.example.player2.main_windows;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.player2.R;
import com.example.player2.ui.MediaActivity;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity  {
    int a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Профиль");
Intent intent=getIntent();
//if(intent.hasExtra("Portal")){
//   String   answer=intent.getStringExtra("Portal");
//    if(answer.contains("agree")){
//        startActivity(new Intent(ProfileActivity.this, MediaActivity.class));
//    }
//}


    }





    @Override
    public void onBackPressed() {
        if(a==0){
            Toast.makeText(this, "Нажмите кнопку \"Назад\" еще раз, чтобы выйти из приложения.", Toast.LENGTH_SHORT).show();
            a++;
        }
        else {
            super.onBackPressed();

        }
    }
}