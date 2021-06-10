package com.example.player2.main_windows;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.player2.R;
import com.example.player2.ui.GlavStranitsa;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    TextView name;
    Button exit;
    ImageButton home, profile, search, channels;
    GoogleApiClient googleApiClient;
    GoogleSignInOptions googleSignInOptions;
    float x1, x2, y1, y2;
    CircularImageView circularImageView;
    public static final int Pick_Image = 1;
    Uri imageUri;
private FirebaseAuth mAuth;
private FirebaseUser mCurrentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Профиль");

        ini();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Context context;
        Api api;
        circularImageView =findViewById(R.id.circularImageView);
        Intent intent = getIntent();
        if (name.getText().toString().contains("Имя пользователя")) {
            String chidor = intent.getStringExtra("newjourney");
            if (chidor != null) {
                chidor = chidor.substring(0, chidor.length() - 10);
                name.setText(chidor);
            } else if (chidor == null) {

            }

        }




        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, GlavStranitsa.class));
                Animatoo.animateSlideRight(ProfileActivity.this);
                finish();
            }

        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Вы уже находитесь во вкладке профиль", Toast.LENGTH_SHORT).show();
            }
        });
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if (status.isSuccess()) {
                            SharedPreferences getpreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                            String checkbox = getpreferences.getString("remember", "");
                            finish();
                            if (checkbox.contains("true")) {
                                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("remember", "false");
                                editor.apply();
                                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }


                        } else
                            mAuth.signOut();

                        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
        channels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, TeleKanalActivity.class));
                Animatoo.animateSlideRight(ProfileActivity.this);
                finish();
            }
        });

    }



    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();

                if (x1 < x2) {
                    Intent intent = new Intent(ProfileActivity.this, TeleKanalActivity.class);
                    startActivity(intent);
                    Animatoo.animateSlideRight(ProfileActivity.this);
                    finish();
                } else if (x1 > x2) {
                    Intent intent = new Intent(ProfileActivity.this, GlavStranitsa.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(ProfileActivity.this);
                    finish();
                }

                break;
        }
        return true;
    }

    void ini() {

        name = findViewById(R.id.textView6);
        exit = findViewById(R.id.exit);
        home = findViewById(R.id.homemain);
        profile = findViewById(R.id.profile);
        channels = findViewById(R.id.telekanal);

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }





}