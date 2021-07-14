package com.example.player2.main_windows;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.player2.R;
import com.example.player2.ui.MediaActivity;
import com.example.player2.utils.AppLifeCycleHandler;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Logo_Activity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Boolean ball = false;
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_);
        Find_Promos();

        Objects.requireNonNull(getSupportActionBar()).hide();
        AppLifeCycleHandler handler = new AppLifeCycleHandler();

            registerActivityLifecycleCallbacks(handler);

        registerComponentCallbacks(handler);
    }

    private void Find_Promos() {
        FirebaseFirestore root = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar311);
        SharedPreferences getPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = getPreferences.getString("ValidPromo", "");
        String Ticket = getPreferences.getString("status", "");
        DocumentReference documentReference = root.collection("Promocodes").document("jhNeUCSn4CsfXuc4I8Se");
        documentReference.get().addOnSuccessListener(documentSnapshot -> {
            progressBar.setVisibility(View.VISIBLE);
            for (int i = 1; i < 10; i++) {
                String number = Integer.toString(i);
                if (checkbox.trim().equals(documentSnapshot.getString(number))) {
                    ball = true;
                    Intent intent = new Intent(Logo_Activity.this, MediaActivity.class);
                    SharedPreferences gePreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    int thirty = gePreferences.getInt("30days", 0);
                    if (thirty == 333) {
                        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("30days", 333);
                        editor.apply();
                    } else if (thirty != 111) {
                        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("30days", 333);
                        editor.apply();
                    }
                    startActivity(intent);
                    finish();
                }

            }
            if (Ticket.equals("trial")) {
                Intent intent=new Intent(Logo_Activity.this,MediaActivity.class);
                intent.putExtra("Portal","agree");
                startActivity(intent);
                finish();
            } else if (Ticket.equals("")) {
                startActivity(new Intent(Logo_Activity.this, RegisterActivity.class));
                finish();

            } else if (Ticket.equals("expired")) {
                startActivity(new Intent(Logo_Activity.this, MediaActivity.class));
                finish();

            } else if (!ball) {
                startActivity(new Intent(Logo_Activity.this, MediaActivity.class));
                finish();

                Toast.makeText(Logo_Activity.this, "Ваш раннее активированный промокод больше недействителен", Toast.LENGTH_LONG).show();
            }


        });
    }

}