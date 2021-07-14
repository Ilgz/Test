package com.example.player2.main_windows;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.player2.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class PromotionActivity extends AppCompatActivity {
    private Button Fetch;
    private FirebaseFirestore root;
    private EditText editText;
    private Boolean ball;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);
        initialize();
getSupportActionBar().setTitle("Промокод");
        Fetch.setOnClickListener(v -> {
            hideKeyboard(v);
            fetch();
        });
        editText.setOnEditorActionListener((v, actionId, event) -> {

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard(v);
                DocumentReference documentReference = root.collection("Promocodes").document("jhNeUCSn4CsfXuc4I8Se");
                documentReference.get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String back = editText.getText().toString();
                        ball = false;
                        progressBar.setVisibility(View.VISIBLE);
                        for (int i = 1; i < 10; i++) {
                            String number = Integer.toString(i);
                            if (back.trim().equals(documentSnapshot.getString(number))) {
                                ball = true;
                                Toast.makeText(PromotionActivity.this, "Промокод был успешно активирован", Toast.LENGTH_LONG).show();
                                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("ValidPromo", documentSnapshot.getString(number));
                                editor.apply();
                                startActivity(new Intent(PromotionActivity.this,ProfileActivity.class));
                            }

                        }
                        if (!ball) {
                            Toast.makeText(PromotionActivity.this, "Промокод не годен", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                    }

                }).addOnFailureListener(e -> Toast.makeText(PromotionActivity.this, "Failed to get Data", Toast.LENGTH_SHORT).show());
            }
            return false;
        });
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void fetch() {
        DocumentReference documentReference = root.collection("Promocodes").document("jhNeUCSn4CsfXuc4I8Se");
        documentReference.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String back = editText.getText().toString();
                ball = false;
                progressBar.setVisibility(View.VISIBLE);
                for (int i = 1; i < 10; i++) {
                    String number = Integer.toString(i);
                    if (back.trim().equals(documentSnapshot.getString(number))) {
                        ball = true;
                        Toast.makeText(PromotionActivity.this, "Промокод был успешно активирован", Toast.LENGTH_LONG).show();
                        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("ValidPromo", documentSnapshot.getString(number));
                        editor.apply();
                        startActivity(new Intent(PromotionActivity.this,ProfileActivity.class));

                    }

                }
                if (!ball) {
                    Toast.makeText(PromotionActivity.this, "Промокод не годен", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }

        }).addOnFailureListener(e -> Toast.makeText(PromotionActivity.this, "Failed to get Data", Toast.LENGTH_SHORT).show());

    }

    private void initialize() {
        Fetch = findViewById(R.id.SubmitButton1);
        root = FirebaseFirestore.getInstance();
        editText = findViewById(R.id.editTextCode1);
        progressBar = findViewById(R.id.progressBar31);

    }
}