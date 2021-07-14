package com.example.player2.ui;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.player2.R;

import java.util.Objects;

public class MediaActivity extends AppCompatActivity {

    int a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glav_stranitsa);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        Objects.requireNonNull(getSupportActionBar()).setTitle("Медиа");






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
