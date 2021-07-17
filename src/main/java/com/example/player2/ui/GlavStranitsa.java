package com.example.player2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.player2.PokazFirst;
import com.example.player2.main_windows.ProfileActivity;
import com.example.player2.main_windows.TeleKanalActivity;
import com.example.player2.models.Movie;
import com.example.player2.adapters.MovieAdapter;
import com.example.player2.adapters.MovieItemClickList;
import com.example.player2.R;
import com.example.player2.utils.DataSource;
import com.example.player2.utils.SpacingItemDecorator;

import java.util.List;

public class GlavStranitsa extends AppCompatActivity implements MovieItemClickList {
    private RecyclerView MoviesRv, moviesRvPop, cartoonsRv;
    ImageButton profile, home, search, channels;
    TextView Thome, Tprofile, Tsearch, Tchannels;
    MovieAdapter popularAdapter = new MovieAdapter(this, (List<Movie>) DataSource.getPopularMovie(), this);
    MovieAdapter movieAdapter = new MovieAdapter(this, DataSource.getNewestMovie(), this);
    MovieAdapter cartoonAdapter = new MovieAdapter(this, DataSource.getCartoons(), this);
    ConstraintLayout constraintLayout;
    float x1, x2, y1, y2;
    SharedPreferences sharedPreferences;
    public static final String Shared_pref_Name="mypref";
    public static final String Key_Name="name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glav_stranitsa);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(15);
        inView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        inNewest();
        inPopularMovies();
        inCartoons();
        constraintLayout = findViewById(R.id.rooot);
        MoviesRv.addItemDecoration(itemDecorator);
        cartoonsRv.addItemDecoration(itemDecorator);
        moviesRvPop.addItemDecoration(itemDecorator);
        getSupportActionBar().setTitle("Главная");
        Context context;
        sharedPreferences =getSharedPreferences(Shared_pref_Name,MODE_PRIVATE);
        String name=sharedPreferences.getString(Key_Name,null);



        constraintLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                constraintLayout.getWindowVisibleDisplayFrame(rect);
                int screenHeight = constraintLayout.getRootView().getHeight();
                int keypadHeight = screenHeight - rect.bottom;
                if (keypadHeight > screenHeight * 0.15) {
                    home.setVisibility(View.INVISIBLE);
                    profile.setVisibility(View.INVISIBLE);
                    channels.setVisibility(View.INVISIBLE);
                    Thome.setVisibility(View.INVISIBLE);
                    Tprofile.setVisibility(View.INVISIBLE);
                    Tchannels.setVisibility(View.INVISIBLE);
                } else {
                    home.setVisibility(View.VISIBLE);
                    profile.setVisibility(View.VISIBLE);
                    channels.setVisibility(View.VISIBLE);
                    Thome.setVisibility(View.VISIBLE);
                    Tprofile.setVisibility(View.VISIBLE);
                    Tchannels.setVisibility(View.VISIBLE);
                }
            }
        });



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GlavStranitsa.this, ProfileActivity.class);
                intent.putExtra("newjourney",name);
                startActivity(intent);
                Animatoo.animateSlideLeft(GlavStranitsa.this);

                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GlavStranitsa.this, "Вы уже находитесь во вкладке главная", Toast.LENGTH_SHORT).show();
            }
        });
        channels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GlavStranitsa.this, TeleKanalActivity.class));
                Animatoo.animateSlideLeft(GlavStranitsa.this);

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
                    Intent intent = new Intent(GlavStranitsa.this, ProfileActivity.class);
                    sharedPreferences =getSharedPreferences(Shared_pref_Name,MODE_PRIVATE);
                    String name=sharedPreferences.getString(Key_Name,null);
                    intent.putExtra("newjourney",name);
                    startActivity(intent);
                    Animatoo.animateSlideRight(GlavStranitsa.this);

                    finish();

                } else if (x1 > x2) {
                    Intent intent = new Intent(GlavStranitsa.this, TeleKanalActivity.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(GlavStranitsa.this);

                    finish();

                }

                break;
        }
        return true;
    }

    private void inView() {
        MoviesRv = findViewById(R.id.Rv_telik);
        moviesRvPop = findViewById(R.id.rv_movies_popular);
        cartoonsRv = findViewById(R.id.rv_cartoons);
        profile = findViewById(R.id.profile);
        home = findViewById(R.id.homemain);
        channels = findViewById(R.id.telekanal);
        Thome = findViewById(R.id.textHome);
        Tprofile = findViewById(R.id.textProfile);
        Tchannels = findViewById(R.id.textChannel);
    }


    private void inCartoons() {
        cartoonsRv.setAdapter(cartoonAdapter);
        cartoonsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void inPopularMovies() {

        moviesRvPop.setAdapter(popularAdapter);
        moviesRvPop.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    private void inNewest() {

        MoviesRv.setAdapter(movieAdapter);
        MoviesRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }


    @Override
    public void OnMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("imgCover", movie.getCoverPhotot());
        intent.putExtra("descript", movie.getDescription());
        intent.putExtra("url", movie.getStreamingLink());
        intent.putExtra("year",movie.getYear());


        Activity activity;
        View view;
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GlavStranitsa.this, movieImageView, "sharedName");
        Toast.makeText(this, "item clicked: " + movie.getTitle(), Toast.LENGTH_LONG);
        startActivity(intent, options.toBundle());
    }


    public void hugo(View view) {
        Intent intent=new Intent(GlavStranitsa.this, PokazFirst.class);
        startActivity(intent);
    }

    public void hugo2(View view) {
        Intent intent=new Intent(GlavStranitsa.this, PokazSecond.class);
        startActivity(intent);
    }

    public void hugo3(View view) {
        Intent intent=new Intent(GlavStranitsa.this, PokazThird.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                cartoonAdapter.getFilter().filter(query);
                movieAdapter.getFilter().filter(query);
                popularAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cartoonAdapter.getFilter().filter(newText);
                movieAdapter.getFilter().filter(newText);
                popularAdapter.getFilter().filter(newText);

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null
                && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE)
                && v instanceof EditText
                && !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop()
                    || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null
                && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView()
                    .getWindowToken(), 0);
        }
    }
}
