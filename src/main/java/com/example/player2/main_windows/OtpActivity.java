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
import com.example.player2.models.PhoneUser;
import com.example.player2.ui.MediaActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OtpActivity extends AppCompatActivity {
    private EditText otpText;
    private FirebaseAuth mAuth;
    private String mAuthVerificationId;
    private Button Verify;
    private ProgressBar otpProgress;
    private TextView otpFeedback;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        mAuth = FirebaseAuth.getInstance();
        Objects.requireNonNull(getSupportActionBar()).hide();
        otpProgress = findViewById(R.id.progressBar3);
        otpText = findViewById(R.id.editTextCode);
        Verify=findViewById(R.id.SubmitButton);
        otpFeedback=findViewById(R.id.oshibka);
        mAuthVerificationId=getIntent().getStringExtra("Authcred");

otpText.setOnEditorActionListener((v, actionId, event) -> {
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

            PhoneAuthCredential credential=PhoneAuthProvider.getCredential(mAuthVerificationId,otp);
            signInWithPhoneAuthCredential(credential);
        }
        return true;
    }
    return false;
});
        otpText.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                hideKeyboard(v);
            }
        });
        Verify.setOnClickListener(v -> {
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
        });
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        SharedPreferences sharedPreferences=getSharedPreferences("phone", MODE_PRIVATE);
                        phone=   sharedPreferences.getString("phone","");
                        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("status", "trial");
                        editor.apply();
                        insertData();
                       startActivity(new Intent(OtpActivity.this, MediaActivity.class));








                        finish();

                    } else {

                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            otpFeedback.setVisibility(View.VISIBLE);
                            otpFeedback.setText("Ошибка верификации");
                        }
                    }
                    otpProgress.setVisibility(View.INVISIBLE);
                    Verify.setEnabled(true);
                });
    }

    private void insertData() {
        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();


        firebaseFirestore.collection("Clients").document(phone).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
if(task.isSuccessful()){
    DocumentSnapshot result=task.getResult();
    if(!result.exists()){
        Map<String,String> items = new HashMap<>();
        items.put("Time","1");
        firebaseFirestore.collection("Clients").document(phone).set(items);
    }
}
            }
        });





    }



}