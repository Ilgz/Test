package com.example.player2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.player2.adapters.MovieAdapter;
import com.example.player2.adapters.MovieItemClickList;
import com.example.player2.main_windows.ProfileActivity;
import com.example.player2.main_windows.TeleChannelActivity;
import com.example.player2.models.Movie;
import com.example.player2.ui.MovieDetailActivity;
import com.example.player2.utils.DataSource;
import com.example.player2.utils.SpacingItemDecoratorVertical;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class PokazFirst extends AppCompatActivity implements MovieItemClickList {
    MovieAdapter noviyAdapter = new MovieAdapter(this, DataSource.getNewestMovie(), this);
RecyclerView recyclerView;
    ImageButton profile, home, search, channels;
    SharedPreferences sharedPreferences;
    public static final String Shared_pref_Name="mypref";
    public static final String Key_Name="name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokaz_first);
        getSupportActionBar().setTitle("Фильмы");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setSelectedItemId(R.id.glavstr);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.glavstr:

                        return true;
                    case R.id.menu_telek:
                        startActivity(new Intent(getApplicationContext(), TeleChannelActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menu_profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        recyclerView=findViewById(R.id.Kroko);
        SpacingItemDecoratorVertical itemDecorator = new SpacingItemDecoratorVertical(40);

        recyclerView.addItemDecoration(itemDecorator);
        Intest();
        sharedPreferences =getSharedPreferences(Shared_pref_Name,MODE_PRIVATE);
        String name=sharedPreferences.getString(Key_Name,null);


    }
    private void Intest() {
        recyclerView.setAdapter(noviyAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3, GridLayoutManager.VERTICAL, false));

    }

    @Override
    public void OnMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("imgCover", movie.getCoverPhotot());
        intent.putExtra("descript", movie.getDescription());
        intent.putExtra("url", movie.getStreamingLink());


        Activity activity;
        View view;
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PokazFirst.this, movieImageView, "sharedName");
        Toast.makeText(this, "item clicked: " + movie.getTitle(), Toast.LENGTH_LONG);
        startActivity(intent, options.toBundle());
    }
}