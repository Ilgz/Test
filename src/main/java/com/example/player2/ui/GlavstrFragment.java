package com.example.player2.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.player2.R;
import com.example.player2.adapters.MovieAdapter;
import com.example.player2.adapters.MovieItemClickList;
import com.example.player2.adapters.Slide;
import com.example.player2.adapters.SliderPageAdapter;
import com.example.player2.main_windows.ProfileActivity;
import com.example.player2.main_windows.TeleChannelActivity;
import com.example.player2.models.Movie;
import com.example.player2.utils.DataSource;
import com.example.player2.utils.SpacingItemDecorator;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class GlavstrFragment extends Fragment implements MovieItemClickList {
    View v;
    private RecyclerView MoviesRv, moviesRvPop, cartoonsRv;

    private List<Slide> slideList;
    private ViewPager sliderpager;
    private TabLayout indicator;
     MovieAdapter popularAdapter;
     MovieAdapter movieAdapter;
     MovieAdapter cartoonAdapter;
    public GlavstrFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_glavstr, container, false);
        sliderpager = v.findViewById(R.id.slider);
        indicator = v.findViewById(R.id.indicator);
        slideList = new ArrayList<>();
        slideList.add(new Slide(R.drawable.emma, "Круэлла"));
        slideList.add(new Slide(R.drawable.mortal, "Мортал Комбат"));
        slideList.add(new Slide(R.drawable.wrath, "Гнев человеческий"));
        SliderPageAdapter adapter = new SliderPageAdapter(getActivity(), slideList);
        setHasOptionsMenu(true);
        sliderpager.setAdapter(adapter);
        Timer timer = new Timer();
        SliderTimer sliderTimer = new SliderTimer();
        timer.scheduleAtFixedRate(sliderTimer, 8000, 12000);
        BottomNavigationView bottomNavigationView = v.findViewById(R.id.bottomnav);
        bottomNavigationView.setSelectedItemId(R.id.glavstr);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.glavstr:
                        return true;
                    case R.id.menu_telek:
                        startActivity(new Intent(getActivity(), TeleChannelActivity.class));
                        return true;
                    case R.id.menu_profile:
                        startActivity(new Intent(getActivity(), ProfileActivity.class));

                        return true;
                }
                return false;
            }
        });
        indicator.setupWithViewPager(sliderpager, true);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(15);
        if(getActivity()!=null) {
            inView();

            inCartoons();
            MoviesRv.addItemDecoration(itemDecorator);
            cartoonsRv.addItemDecoration(itemDecorator);
            moviesRvPop.addItemDecoration(itemDecorator);
        }
        Context context;


        return v;
    }

    private void inView() {
        MoviesRv = v.findViewById(R.id.Rv_telik);
        moviesRvPop = v.findViewById(R.id.rv_movies_popular);
        cartoonsRv = v.findViewById(R.id.rv_cartoons);
        TextView one=(TextView) v.findViewById(R.id.textView13);
        TextView two=(TextView) v.findViewById(R.id.textView22);
        TextView three=(TextView) v.findViewById(R.id.textView15);
    }


    private void inCartoons() {
          popularAdapter = new MovieAdapter(getActivity(), DataSource.getPopularMovie(), this);
          movieAdapter = new MovieAdapter(getActivity(), DataSource.getNewestMovie(), this);
          cartoonAdapter = new MovieAdapter(getActivity(), DataSource.getCartoons(), this);
        cartoonsRv.setAdapter(cartoonAdapter);
        cartoonsRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        moviesRvPop.setAdapter(popularAdapter);
        moviesRvPop.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        MoviesRv.setAdapter(movieAdapter);
        MoviesRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void OnMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("imgCover", movie.getCoverPhotot());
        intent.putExtra("descript", movie.getDescription());
        intent.putExtra("url", movie.getStreamingLink());
        intent.putExtra("year", movie.getYear());
        intent.putExtra("function", movie.getFunction());


        Activity activity;
        View view;
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), movieImageView, "sharedName");
        Toast.makeText(getActivity(), "item clicked: " + movie.getTitle(), Toast.LENGTH_LONG);
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
       inflater.inflate(R.menu.menu,menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
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


    }

    class SliderTimer extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem() < slideList.size() - 1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem() + 1);

                    } else {
                        sliderpager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
