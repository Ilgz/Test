package com.example.player2.main_windows;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.player2.R;
import com.example.player2.ui.GlavStranitsa;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {
    private TextView feedback;
    private EditText mPhoneNumber;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private Button mGenerateBtn;
    private ProgressBar progressBar;
    private EditText countrycode;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mPhoneNumber = findViewById(R.id.editTextPhone);
        mGenerateBtn = findViewById(R.id.buttonContininue);
        feedback = findViewById(R.id.feedback);
        progressBar = findViewById(R.id.progressBar);
        countrycode = findViewById(R.id.countrycode);

        progressBar.setVisibility(View.INVISIBLE);
        getSupportActionBar().hide();
        clicked();


        mPhoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mPhoneNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String phone_number = mPhoneNumber.getText().toString();
                    String complete = countrycode.getText().toString() + phone_number;
                    hideKeyboard(v);
                    if (phone_number.isEmpty()) {
                        feedback.setVisibility(View.VISIBLE);
                        feedback.setText("Заполните номер полностью");

                    } else {

                        progressBar.setVisibility(View.VISIBLE);
                        mGenerateBtn.setEnabled(false);
                        SharedPreferences preferences = getSharedPreferences("phone", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("member", complete);
                        editor.apply();
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                complete,
                                60,
                                TimeUnit.SECONDS,
                                RegisterActivity.this,
                                mCallbacks

                        );

                    }

                    return true;
                }
                return false;
            }
        });
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
                feedback.setVisibility(View.VISIBLE);
                feedback.setText("Заполните номер полностью");
                progressBar.setVisibility(View.INVISIBLE);
                mGenerateBtn.setEnabled(true);
            }

            @Override
            public void onCodeSent(@NonNull @NotNull String s, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                Intent otpintent = new Intent(RegisterActivity.this, OtpActivity.class);
                otpintent.putExtra("Authcred", s);

                startActivity(otpintent);

                finish();
            }

        };

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    void clicked() {
        mGenerateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_number = mPhoneNumber.getText().toString();
                String complete = countrycode.getText().toString() + phone_number;
                if (phone_number.isEmpty()) {
                    feedback.setVisibility(View.VISIBLE);
                    feedback.setText("Заполните номер полностью");

                } else {

                    progressBar.setVisibility(View.VISIBLE);
                    mGenerateBtn.setEnabled(false);
                    SharedPreferences preferences = getSharedPreferences("phone", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("member", complete);
                    editor.apply();
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            complete,
                            60,
                            TimeUnit.SECONDS,
                            RegisterActivity.this,
                            mCallbacks

                    );

                }

            }
        });
    }
}