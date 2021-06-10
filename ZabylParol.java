package com.example.player2.main_windows;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.player2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ZabylParol extends AppCompatActivity {
private  EditText passwordEmail;
private Button resetPassword;
private FirebaseAuth  firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zabyl_parol);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Сброс пароля");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        passwordEmail=findViewById(R.id.etPaswordMail);
        resetPassword=findViewById(R.id.BTNpasswordReset);
        firebaseAuth=FirebaseAuth.getInstance();
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail=passwordEmail.getText().toString().trim();
                if (TextUtils.isEmpty(useremail)){
                    Toast.makeText(ZabylParol.this, "Заполните поле", Toast.LENGTH_SHORT).show();
                }else {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ZabylParol.this,"Сообщение для сброса пароля  было отправлено по почте ",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ZabylParol.this,MainActivity.class));
                                finish();

                            }
                            else {
                                Toast.makeText(ZabylParol.this,"Ошибка в отправке сообщения для сброса пароля ",Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            }
        });
    }
}