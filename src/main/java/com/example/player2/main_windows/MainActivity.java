package com.example.player2.main_windows;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.player2.R;
import com.example.player2.Registration;
import com.example.player2.models.User;
import com.example.player2.ui.GlavStranitsa;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.facebook.FacebookSdk;

import java.util.Arrays;

public class  MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    Button Signin, register;
    RelativeLayout root;
    SignInButton signInButton;
    LoginButton loginButton;
    GoogleSignInClient mGoogleSignin;
   CallbackManager callbackManager = CallbackManager.Factory.create();
    MaterialEditText name;
    SharedPreferences sharedPreferences;
public static final String Shared_pref_Name="mypref";
public static final String Key_Name="name";

    private static int RC_sign_in = 100;
public static final String EMAIL="email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tester);
        getSupportActionBar().hide();
        FacebookSdk.sdkInitialize(getApplicationContext());
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // If using in a fragment

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override

            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(MainActivity.this, "Вход прошел успешно", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this, GlavStranitsa.class);
                startActivity(intent);

                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "true");
                editor.apply();
                finish();
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(MainActivity.this, "Вход был отменен", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(MainActivity.this, "Ошибка входа"+exception.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        Signin = findViewById(R.id.newBTNsignin);
        register = findViewById(R.id.newBTNRegister);
        root = findViewById(R.id.root_element);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignin = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        SharedPreferences getpreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = getpreferences.getString("remember", "");
        if (checkbox.equals("true")) {
            Intent intent = new Intent(MainActivity.this, GlavStranitsa.class);
            startActivity(intent);
            finish();
        }

        SignInButton signInButton = findViewById(R.id.google_sign_in);
sharedPreferences =getSharedPreferences(Shared_pref_Name,MODE_PRIVATE);

        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIN();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Registration.class));

            }
        });
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(Signin.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                showSigninWindow();


            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            callbackManager.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);

        }
        else

            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RC_sign_in) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);

        }
    }


    private void showSigninWindow() {

        EditText email = findViewById(R.id.emailField);

        EditText pass = findViewById(R.id.pasField);


        if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(pass.getText().toString())) {
            Toast toast = Toast.makeText(MainActivity.this, "Заполните все поля ", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 10);
            toast.show();
            return;
        }

        if (pass.getText().toString().length() < 6) {
            Toast toast = Toast.makeText(MainActivity.this, "Введите пароль который более 6 символов ", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 10);
            toast.show();
            return;
        }

        //Регистрация
        auth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "true");
                editor.apply();
SharedPreferences.Editor editor1=sharedPreferences.edit();
editor1.putString(Key_Name,email.getText().toString());
editor1.apply();
                Intent intent=new Intent(MainActivity.this,GlavStranitsa.class);



                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast toast = Toast.makeText(MainActivity.this, "Ошибка авторизации", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 10);
                toast.show();
                return;
            }
        });


    }


    private void signIN() {
        Intent signinIntent = mGoogleSignin.getSignInIntent();
        startActivityForResult(signinIntent, RC_sign_in);
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
                Toast.makeText(this, "User email:" + personEmail, Toast.LENGTH_LONG).show();
            }
            SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("remember", "true");
            editor.apply();
            startActivity(new Intent(MainActivity.this, GlavStranitsa.class));
            finish();
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("Message", e.toString());
        }
    }

    private void showRegisterWindow() {
        Context context;
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Введите данные ");
        LayoutInflater inflater = LayoutInflater.from(this);
        View registerwindow = inflater.inflate(R.layout.register_window, null);
        dialog.setView(registerwindow);
        MaterialEditText email = registerwindow.findViewById(R.id.emailField);
         name = registerwindow.findViewById(R.id.nameField);
        MaterialEditText fami = registerwindow.findViewById(R.id.familField);
        MaterialEditText pass = registerwindow.findViewById(R.id.pasFieldf);
        MaterialEditText conpas = registerwindow.findViewById(R.id.conpasField);
        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Зарегистрироваться", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {


                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(fami.getText().toString()) || TextUtils.isEmpty(pass.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Заполните все поля", Toast.LENGTH_LONG).show();
                }
                if (pass.getText().toString().length() < 6) {
                    Toast.makeText(MainActivity.this, "Пароль должен быть более 6 символов", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!pass.getText().toString().contains(conpas.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Пароли должны совпадать", Toast.LENGTH_LONG).show();
                    return;
                }

                //Регистрация
                auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User user = new User();
                        user.setEmail(email.getText().toString());
                        user.setName(name.getText().toString());
                        user.setPass(pass.getText().toString());
                        user.setFam(fami.getText().toString());
                        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                            }

                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast toast = Toast.makeText(MainActivity.this, "Ошибка регистрации",Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 0, 10);
                        toast.show();
                        return;
                    }
                });

            }
        });
        dialog.show();
    }

    public void ZabylKodIntent(View view) {
        Intent intent = new Intent(MainActivity.this, ZabylParol.class);
        startActivity(intent);

    }


    public void phic(View view) {
        startActivity(new Intent(MainActivity.this,RegisterActivity.class));
    }
}