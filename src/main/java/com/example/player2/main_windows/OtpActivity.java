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

import com.example.player2.R;
import com.example.player2.ui.GlavStranitsa;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OtpActivity extends AppCompatActivity {
    private String verificationid;
    private EditText otpText;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private String mAuthVerificationId;
    private Button Verify;
    private ProgressBar otpProgress;
    private TextView otpFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        getSupportActionBar().hide();
        otpProgress = findViewById(R.id.progressBar3);
        otpText = findViewById(R.id.editTextCode);
        Verify=findViewById(R.id.SubmitButton);
        otpFeedback=findViewById(R.id.oshibka);
        mAuthVerificationId=getIntent().getStringExtra("Authcred");

otpText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            String otp=otpText.getText().toString();
hideKeyboard(v);
            if(otp.isEmpty()){
                otpFeedback.setVisibility(View.VISIBLE);
                otpFeedback.setText("Пожалуйста заполните форму полностью и попробуйте еще раз.");

            }
            else{
                otpProgress.setVisibility(View.VISIBLE);
                Verify.setEnabled(false);
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "true");
                editor.apply();
                PhoneAuthCredential credential=PhoneAuthProvider.getCredential(mAuthVerificationId,otp);
                signInWithPhoneAuthCredential(credential);
            }
            return true;
        }
        return false;
    }
});
        otpText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });
        Verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp=otpText.getText().toString();
                if(otp.isEmpty()){
                    otpFeedback.setVisibility(View.VISIBLE);
                    otpFeedback.setText("Пожалуйста заполните форму полностью и попробуйте еще раз.");

                }
                else{
                    otpProgress.setVisibility(View.VISIBLE);
                    Verify.setEnabled(false);
                    PhoneAuthCredential credential=PhoneAuthProvider.getCredential(mAuthVerificationId,otp);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("remember", "true");
                            editor.apply();
                           startActivity(new Intent(OtpActivity.this, GlavStranitsa.class));

                            finish();

                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                otpFeedback.setVisibility(View.VISIBLE);
                                otpFeedback.setText("Ошибка верификации");
                            }
                        }
                        otpProgress.setVisibility(View.INVISIBLE);
                        Verify.setEnabled(true);
                    }
                });
    }

}