package com.example.player2.ui;

import android.app.ActivityOptions;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.player2.PokazFirst;
import com.example.player2.R;
import com.example.player2.adapters.MovieAdapter;
import com.example.player2.adapters.MovieItemClickList;
import com.example.player2.adapters.Slide;
import com.example.player2.adapters.SliderPageAdapter;
import com.example.player2.main_windows.ProfileActivity;
import com.example.player2.main_windows.ProfileFragment;
import com.example.player2.main_windows.TeleChannelActivity;
import com.example.player2.models.Movie;
import com.example.player2.utils.DataSource;
import com.example.player2.utils.SpacingItemDecorator;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;


public class MediaFragment extends Fragment implements MovieItemClickList {
    View v;
    private RecyclerView MoviesRv, moviesRvPop, cartoonsRv,allRv;
    private final long Start_Time_IN_MILLIS= 172800000;




    private long mTimeLeftInMillis;
    private final String KEYMillisLeft = "mils";
    private long mEndTime;
    private List<Slide> slideList;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private MovieAdapter popularAdapter;
    private MovieAdapter movieAdapter;
    private MovieAdapter cartoonAdapter;
    private MovieAdapter allAdapter;
    private TextView one;
    private TextView two;
    private TextView three;
    TextView tvFilms;
    TextView tvSerials;
    TextView tvCartoons;
    public MediaFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_media, container, false);

         tvFilms=v.findViewById(R.id.tvFilms);
         tvSerials=v.findViewById(R.id.tvSerials);
         tvCartoons=v.findViewById(R.id.tvMultfilms);
        sliderPager = v.findViewById(R.id.slider);
        indicator = v.findViewById(R.id.indicator);
        slideList = new ArrayList<>();
        slideList.add(new Slide("https://mediad.publicbroadcasting.net/p/ketr/files/styles/x_large/public/202105/cruella_-_facebook.jpg", "Круэлла \n2021\u2022 Фильм"));
        slideList.add(new Slide("https://images.wallpapersden.com/image/download/loki-2021_bGtubWiUmZqaraWkpJRqaWWtbmtlZmtuZ2dl.jpg", "Локи \n2021\u2022 Сериал"));

        slideList.add(new Slide("https://static.filmvandaag.nl/news/5418/Tomorrow%20War.jpg", "Война будущего \n2021\u2022 Фильм"));
        slideList.add(new Slide("https://www.heypoorplayer.com/wp-content/uploads/2021/04/doota-dragons-blood.jpg", "Дота: Кровь дракона \n2021\u2022 Сериал"));

        slideList.add(new Slide("https://static.toiimg.com/photo/82352863.cms", "Армия мертвецов \n2021\u2022 Фильм"));
        slideList.add(new Slide("https://static.toiimg.com/photo/82802710.cms", "Волшебный дракон \n2021\u2022 Мультфильм"));

        slideList.add(new Slide("https://thumbnails.cbsig.net/CBS_Production_Entertainment_VMS/2021/05/24/1900723779607/INFI_SAlone_16_9_1920x1080_688909_1920x1080.jpg", "Бесконечность \n2021\u2022 Фильм"));
        slideList.add(new Slide("https://www.starwarsreport.com/wp-content/uploads/2021/04/Bad-Batch-Banner-1-1170x550.jpg", "Звездные войны:Бракованная партия \n2021\u2022 Сериал"));
        slideList.add(new Slide("https://avatars.mds.yandex.net/get-ott/223007/2a00000179d1cbf1bd7ac3ee0ee2d3b081a3/678x380", "Гнев человеческий \n2021\u2022 Фильм"));
        slideList.add(new Slide("https://tvshows.today/wp-content/uploads/m-o-d-o-k-tv-series-poster.jpg", "Модок \n2021\u2022 Сериал"));

        slideList.add(new Slide("https://img-www.tf-cdn.com/movie/2/cherry-2021.jpeg", "По наклонной \n2021\u2022 Фильм"));
        slideList.add(new Slide("https://static3.cbrimages.com/wordpress/wp-content/uploads/2021/04/Falcon-Winter-Soldier-Banner.jpg", "Сокол и Зимний Солдат \n2021\u2022 Сериал"));
        slideList.add(new Slide("https://i2.wp.com/thehappening.com/wp-content/uploads/2021/06/luca.jpeg?fit=1480%2C833&ssl=1", "Лука \n2021\u2022 Мульфильм"));

        slideList.add(new Slide("https://i2.wp.com/getreelmovies.com/wp-content/uploads/2021/05/GRM_mitchells_banner.jpeg?resize=1170%2C550&ssl=1", "Митчеллы против машин \n2021\u2022 Мультфильм"));
        slideList.add(new Slide("https://www.gameinformer.com/sites/default/files/2021/03/10/f93fcd91/mortal_kombat_movie_poster.png", "Мортал Комбат \n2021\u2022 Фильм"));
        slideList.add(new Slide("https://images-na.ssl-images-amazon.com/images/S/pv-target-images/26ea489b3ff81fcb986828be7a947bc88315c7a4d0e0615c241d441e57abbe30._RI_V_TTW_.jpg", "Райя и последний дракон \n2021\u2022 Мультфильм"));
        slideList.add(new Slide("https://images1.houstonpress.com/imager/u/745xauto/11549975/hou_art_20210331_gvk_header.jpeg", "Годзилла против Конга \n2021\u2022 Фильм"));
        slideList.add(new Slide("https://avatars.mds.yandex.net/get-ott/1672343/2a00000178a166d251170b6aab6f8c1d99c1/678x380", "Лига справедливости Зака Снайдера \n2021\u2022 Фильм"));

        SliderPageAdapter adapter = new SliderPageAdapter(getActivity(), slideList);
        setHasOptionsMenu(true);

        sliderPager.setAdapter(adapter);
        Timer timer = new Timer();
        SliderTimer sliderTimer = new SliderTimer();
        timer.scheduleAtFixedRate(sliderTimer, 8000, 12000);
        BottomNavigationView bottomNavigationView = v.findViewById(R.id.bottomnav);
        bottomNavigationView.setSelectedItemId(R.id.glavstr);
        new Handler(Looper.getMainLooper()).postDelayed(this::startTimer, 1000);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.glavstr:
                    return true;
                case R.id.menu_telek:
                    startActivity(new Intent(getActivity(), TeleChannelActivity.class));
                    getActivity().finish();
                    return true;
                case R.id.menu_profile:
                    startActivity(new Intent(getActivity(), ProfileActivity.class));
                    getActivity().finish();

                    return true;
            }
            return false;
        });
        indicator.setupWithViewPager(sliderPager, true);
        SpacingItemDecorator vertical = new SpacingItemDecorator(15,0);
        SpacingItemDecorator horizontal = new SpacingItemDecorator(0,15);
        if (getActivity() != null) {
            inView();

            inCartoons();
            MoviesRv.addItemDecoration(vertical);
            cartoonsRv.addItemDecoration(vertical);
            moviesRvPop.addItemDecoration(vertical);
            allRv.addItemDecoration(horizontal);

        }


        return v;
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        CountDownTimer countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (getActivity() != null) {

                    mTimeLeftInMillis = millisUntilFinished;


                }
            }


            @Override
            public void onFinish() {
                mTimeLeftInMillis = Start_Time_IN_MILLIS;

                trimCache(getContext());

                startTimer();
            }
        }.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences prefs = requireActivity().getSharedPreferences("prefs", MODE_PRIVATE);
        mTimeLeftInMillis = prefs.getLong(KEYMillisLeft, Start_Time_IN_MILLIS);
    }
    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }
    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences prefs = requireActivity().getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(KEYMillisLeft, mTimeLeftInMillis);
        String KEYTimeEndTime = "herb";
        editor.putLong(KEYTimeEndTime, mEndTime);
        editor.apply();


    }
    private void inView() {



        MoviesRv = v.findViewById(R.id.Rv_telik);
        moviesRvPop = v.findViewById(R.id.rv_movies_popular);
        cartoonsRv = v.findViewById(R.id.rv_cartoons);
        allRv=v.findViewById(R.id.AllRec);
        one = (TextView) v.findViewById(R.id.textView13);
        two = (TextView) v.findViewById(R.id.textView22);
        three = (TextView) v.findViewById(R.id.textView15);
        one.setOnClickListener(v1 -> startActivity(new Intent(getActivity(), PokazFirst.class)));
        two.setOnClickListener(v1 -> startActivity(new Intent(getActivity(), PokazSecond.class)));
        three.setOnClickListener(v1 -> startActivity(new Intent(getActivity(), PokazThird.class)));
    }


    private void inCartoons() {
        DataSource dataSource = new DataSource();
        popularAdapter = new MovieAdapter(getActivity(), dataSource.getSeries(), this);

        movieAdapter = new MovieAdapter(getActivity(), dataSource.getNewestMovie(), this);

        cartoonAdapter = new MovieAdapter(getActivity(), dataSource.getCartoons(), this);
        cartoonsRv.setAdapter(cartoonAdapter);
        cartoonsRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        moviesRvPop.setAdapter(popularAdapter);
        moviesRvPop.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        MoviesRv.setAdapter(movieAdapter);
        MoviesRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        allAdapter=new MovieAdapter(getActivity(),dataSource.getAll(),this);
        allRv.setAdapter(allAdapter);
        allRv.setLayoutManager(new GridLayoutManager(getActivity(), 3,RecyclerView.VERTICAL,false));
    }

    @Override
    public void OnMovieClick(Movie movie, ImageView movieImageView) {

        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);

        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("descript", movie.getDescription());
        intent.putExtra("url", movie.getStreamingLink());
        intent.putExtra("siteLink", movie.getSiteLink());

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), movieImageView, "sharedName");
        startActivity(intent, options.toBundle());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                cartoonAdapter.getFilter().filter(query);
                movieAdapter.getFilter().filter(query);
                popularAdapter.getFilter().filter(query);
                allAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cartoonAdapter.getFilter().filter(newText);
                movieAdapter.getFilter().filter(newText);
                popularAdapter.getFilter().filter(newText);
                allAdapter.getFilter().filter(newText);
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sliderPager.setVisibility(View.GONE);
                indicator.setVisibility(View.GONE);
                MoviesRv.setVisibility(View.GONE);
                moviesRvPop.setVisibility(View.GONE);
                cartoonsRv.setVisibility(View.GONE);
                one.setVisibility(View.GONE);
                two.setVisibility(View.GONE);
                three.setVisibility(View.GONE);
                tvFilms.setVisibility(View.GONE);
                tvSerials.setVisibility(View.GONE);
                tvCartoons.setVisibility(View.GONE);
                allRv.setVisibility(View.VISIBLE);


            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                sliderPager.setVisibility(View.VISIBLE);
                indicator.setVisibility(View.VISIBLE);
                MoviesRv.setVisibility(View.VISIBLE);
                moviesRvPop.setVisibility(View.VISIBLE);
                cartoonsRv.setVisibility(View.VISIBLE);
                one.setVisibility(View.VISIBLE);
                two.setVisibility(View.VISIBLE);
                three.setVisibility(View.VISIBLE);
                tvFilms.setVisibility(View.VISIBLE);
                tvSerials.setVisibility(View.VISIBLE);
                tvCartoons.setVisibility(View.VISIBLE);
                allRv.setVisibility(View.INVISIBLE);
                return false;
            }
        });


    }

    class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if (getActivity() != null) getActivity().runOnUiThread(() -> {
                if (sliderPager.getCurrentItem() < slideList.size() - 1) {
                    sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);

                } else {
                    sliderPager.setCurrentItem(0);
                }
            });
        }
    }
}
