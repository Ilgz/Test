package com.example.player2.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.player2.R;
import com.example.player2.adapters.MovieAdapter;
import com.example.player2.adapters.MovieItemClickList;
import com.example.player2.main_windows.ProfileActivity;
import com.example.player2.main_windows.TeleChannelActivity;
import com.example.player2.models.Movie;
import com.example.player2.utils.DataSource;
import com.example.player2.utils.SpacingItemDecoratorVertical;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Objects;

public class PokazSecond extends AppCompatActivity implements MovieItemClickList {
    final DataSource dataSource=new DataSource();

    final MovieAdapter noviyAdapter = new MovieAdapter(this, (List<Movie>) dataSource.getSeries(), this);
RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    public static final String Shared_pref_Name="mypref";
    public static final String Key_Name="name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokaz_first);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Сериалы");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setSelectedItemId(R.id.glavstr);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
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
        intent.putExtra("descript", movie.getDescription());
        intent.putExtra("url", movie.getStreamingLink());
        intent.putExtra("siteLink",movie.getSiteLink());


        Activity activity;
        View view;
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PokazSecond.this, movieImageView, "sharedName");
        Toast.makeText(this, "item clicked: " + movie.getTitle(), Toast.LENGTH_LONG);
        startActivity(intent, options.toBundle());
    }
}