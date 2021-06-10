package com.example.player2.main_windows;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.player2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView feedback;
    private EditText mPhoneNumber;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private Button mGenerateBtn;
    private ProgressBar progressBar;
    private EditText countrycode;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String joul;

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
        mPhoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
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
}