package com.example.player2.main_windows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.player2.R;
import com.example.player2.ui.GlavStranitsa;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class PhoneNumber extends AppCompatActivity {
    private EditText txtPhone,txtVerOTP;
    int randomNumber;

    private Button btnLogin,btnVerify;
    private String phone, otp;
    String verificationId;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        txtPhone = findViewById(R.id.txtPhone);
        txtVerOTP = findViewById(R.id.txtVerify);
        btnVerify = findViewById(R.id.btnVerify);
        btnLogin = findViewById(R.id.sendOtp);
        mAuth = FirebaseAuth.getInstance();

    }
}