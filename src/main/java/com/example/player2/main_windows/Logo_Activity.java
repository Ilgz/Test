package com.example.player2.main_windows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.player2.R;
import com.example.player2.ui.GlavStranitsa;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Logo_Activity extends AppCompatActivity {
    private FirebaseFirestore root;
    private ProgressBar progressBar;
    private Boolean ball=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_);
        root = FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.progressBar311);
        SharedPreferences getpreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = getpreferences.getString("remember", "");
        DocumentReference documentReference = root.collection("Promocodes").document("jhNeUCSn4CsfXuc4I8Se");
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                progressBar.setVisibility(View.VISIBLE);
                for (int i = 1; i < 10; i++) {
                    String number = Integer.toString(i);
                    if (checkbox.trim().equals(documentSnapshot.getString(number))) {
                        ball = true;
                        Intent intent = new Intent(Logo_Activity.this, GlavStranitsa.class);
                        startActivity(intent);
                        finish();
                    }

                }
                 if(checkbox.equals("true")){
                    startActivity(new Intent(Logo_Activity.this,GlavStranitsa.class));
                    finish();
                }
                else if(checkbox.equals("")||checkbox.equals("false")){
                    startActivity(new Intent(Logo_Activity.this,RegisterActivity.class));
                    finish();
                }
                else if(ball==false){
                    Toast.makeText(Logo_Activity.this, "Ваш раннее активированный промокод больше недействителен", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Logo_Activity.this,RegisterActivity.class));
                    finish();
                }


            }
        });
      //  StartA();
        getSupportActionBar().hide();
     }

}