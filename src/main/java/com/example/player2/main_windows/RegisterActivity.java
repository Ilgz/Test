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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.player2.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {
    private TextView feedback;
    private EditText mPhoneNumber;
    private Button mGenerateBtn;
    private ProgressBar progressBar;
    private EditText countryCode;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mPhoneNumber = findViewById(R.id.editTextPhone);
        mGenerateBtn = findViewById(R.id.buttonContininue);
        feedback = findViewById(R.id.feedback);
        progressBar = findViewById(R.id.progressBar);
        countryCode = findViewById(R.id.countrycode);
        mAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.INVISIBLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        clicked();


        mPhoneNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });

        mPhoneNumber.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String phone_number = mPhoneNumber.getText().toString();
                String complete = countryCode.getText().toString() + phone_number;
                hideKeyboard(v);
                if (phone_number.isEmpty()) {
                    feedback.setVisibility(View.VISIBLE);
                    feedback.setText("Некорректный номер");

                } else {

                    progressBar.setVisibility(View.VISIBLE);
                    mGenerateBtn.setEnabled(false);
                    SharedPreferences preferences = getSharedPreferences("phone", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("phone", complete);
                    editor.apply();
                    PhoneAuthOptions options= PhoneAuthOptions.newBuilder(mAuth).setPhoneNumber(complete).setTimeout(60L,TimeUnit.SECONDS).setActivity(RegisterActivity.this).setCallbacks(mCallbacks).build();
                    PhoneAuthProvider.verifyPhoneNumber(options);

                }

                return true;
            }
            return false;
        });
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
                feedback.setVisibility(View.VISIBLE);
                feedback.setText("Некорректный номер");
                progressBar.setVisibility(View.INVISIBLE);
                mGenerateBtn.setEnabled(true);
            }

            @Override
            public void onCodeSent(@NonNull @NotNull String s, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                Intent otpIntent = new Intent(RegisterActivity.this, OtpActivity.class);
                otpIntent.putExtra("Authcred", s);

                startActivity(otpIntent);

                finish();
            }

        };

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    void clicked() {
        mGenerateBtn.setOnClickListener(v -> {
            String phone_number = mPhoneNumber.getText().toString();
            String complete = countryCode.getText().toString() + phone_number;
            hideKeyboard(v);
            if (phone_number.isEmpty()) {
                feedback.setVisibility(View.VISIBLE);
                feedback.setText("Некорректный номер");

            } else {

                progressBar.setVisibility(View.VISIBLE);
                mGenerateBtn.setEnabled(false);
                SharedPreferences preferences = getSharedPreferences("phone", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("phone", complete);
                editor.apply();
               PhoneAuthOptions options= PhoneAuthOptions.newBuilder(mAuth).setPhoneNumber(complete).setTimeout(60L,TimeUnit.SECONDS).setActivity(RegisterActivity.this).setCallbacks(mCallbacks).build();
               PhoneAuthProvider.verifyPhoneNumber(options);



            }

        });
    }
}