package com.example.player2.main_windows;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.player2.R;

import java.util.Objects;


public class Support extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Поддержка");
    }
}
