package com.example.player2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.player2.main_windows.MainActivity;
import com.example.player2.models.User;
import com.example.player2.ui.GlavStranitsa;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    Button register;
    GoogleSignInClient mGoogleSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Регистрация");
        register = findViewById(R.id.reg_button);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignin = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        EditText name = findViewById(R.id.fieldName);
        EditText surname = findViewById(R.id.fieldSurname);
        EditText email = findViewById(R.id.FieldMail);
        EditText pass = findViewById(R.id.fieldPas);
        EditText conpas = findViewById(R.id.fieldConPass);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(surname.getText().toString()) || TextUtils.isEmpty(pass.getText().toString())) {
                    Toast.makeText(Registration.this, "Заполните все поля", Toast.LENGTH_LONG).show();
                }
                if (pass.getText().toString().length() < 6) {
                    Toast.makeText(Registration.this, "Пароль должен быть более 6 символов", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!pass.getText().toString().contains(conpas.getText().toString())) {
                    Toast.makeText(Registration.this, "Пароли должны совпадать", Toast.LENGTH_LONG).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User user = new User();
                        user.setEmail(email.getText().toString());
                        user.setName(name.getText().toString());
                        user.setPass(pass.getText().toString());
                        user.setFam(surname.getText().toString());
                        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Registration.this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Registration.this,MainActivity.class);
                                startActivity(intent);
                            }

                        });
                    }
                })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast toast = Toast.makeText(Registration.this, "Ошибка регистрации", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.TOP, 0, 10);
                                toast.show();
                                return;
                            }
                        });


            }
        });
    }
}
