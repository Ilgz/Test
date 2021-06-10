    package com.example.player2.main_windows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.player2.R;
import com.example.player2.TvWatch;
import com.example.player2.adapters.MovieAdapter;
import com.example.player2.adapters.MovieItemClickList;
import com.example.player2.models.Movie;
import com.example.player2.ui.GlavStranitsa;
import com.example.player2.ui.MovieDetailActivity;
import com.example.player2.utils.DataSource;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TeleKanalActivity extends AppCompatActivity implements MovieItemClickList {
    ImageButton home, channels, profile, search;
    TextView Thome, Tprofile, Tsearch, Tchannels;
    float x1, x2, y1, y2;
    Runnable runnable, runnable2, runnable3, runnable4, runnable5, runnable6;
    Thread secThread, Thread3, Thread4, Thread5, Thread6, Thread7;
    Document document, tnt, Match, RenTv, Rossia24, Sts;
    MovieAdapter channelsAdapter = new MovieAdapter(this, DataSource.getChannels(), this);
    SharedPreferences sharedPreferences;
    public static final String Shared_pref_Name = "mypref";
    public static final String Key_Name = "name";
    String thewhole, matchtvS, RenTvS, Rossia24S, StsS;
    String tntS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_kanal);
        getSupportActionBar().setTitle("Телеканалы");
        ini();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intest();
        sharedPreferences = getSharedPreferences(Shared_pref_Name, MODE_PRIVATE);
        String name = sharedPreferences.getString(Key_Name, null);
        init();
        init2();
        init3();
        init4();
        init5();
        init6();
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeleKanalActivity.this, ProfileActivity.class);
                intent.putExtra("newjourney", name);

                startActivity(intent);
                Animatoo.animateSlideLeft(TeleKanalActivity.this);

                finish();
            }
        });
    }

    private void init6() {
        runnable6 = new Runnable() {
            @Override
            public void run() {
                getweb6();
            }
        };
        Runnable target;
        Thread7=new Thread(runnable6);
        Thread7.start();
    }

    private void getweb6() {
        try {
            Sts = Jsoup.connect("https://tv.mail.ru/moskva/channel/1112/").get();
            Elements time = Sts.getElementsByClass("p-programms__item__time-value");
            Elements name = Sts.getElementsByClass("p-programms__item__name-link");
            String firstT = time.get(0).text();
            String firstN = name.get(0).text();
            String secondT = time.get(1).text();
            String secondN = name.get(1).text();
            String thirdT = time.get(2).text();
            String thirdN = name.get(2).text();
            String fourthT = time.get(3).text();
            String fourthN = name.get(3).text();
            String fifthT = time.get(4).text();
            String fifthN = name.get(4).text();
            String sixT = time.get(5).text();
            String sixN = name.get(5).text();
            String seventhT = time.get(6).text();
            String seventhN = name.get(6).text();
            String eightT = time.get(7).text();
            String eightN = name.get(7).text();
            String ninthT = time.get(8).text();
            String ninthN = name.get(8).text();
            String tenT = time.get(9).text();
            String tenN = name.get(9).text();
            String elevenT = time.get(10).text();
            String elevenN = name.get(10).text();
            String twelveT = time.get(11).text();
            String twelveN = name.get(11).text();
            String thirteenthT = time.get(12).text();
            String thirteenthN = name.get(12).text();
            String fourteenT = time.get(13).text();
            String fourteenN = name.get(13).text();
            String fifteenT = time.get(14).text();
            String fifteenN = name.get(14).text();
            String sixteenT = time.get(15).text();
            String sixteenN = name.get(15).text();
            StsS = firstT + "|" + firstN + "\n" + secondT + "|" + secondN + "\n" + thirdT + "|" + thirdN + "\n" + fourthT + "|" + fourthN + "\n" + fifthT + "|" + fifthN + "\n" + sixT + "|" + sixN + "\n" + seventhT + "|" + seventhN + "\n" + eightT + "|" + eightN + "\n" + ninthT + "|" + ninthN + "\n" + tenT + "|" + tenN + "\n" + elevenT + "|" + elevenN + "\n" + twelveT+ "|" + twelveN + "\n" + thirteenthT + "|" + thirteenthN + "\n" + fourteenT+ "|" + fourteenN + "\n" + fifteenT+ "|" + fifteenN ;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init5() {
        runnable5 = new Runnable() {
            @Override
            public void run() {
                getweb5();
            }
        };
        Runnable target;
        Thread6 = new Thread(runnable5);
        Thread6.start();
    }

    private void getweb5() {
        try {
            tnt = Jsoup.connect("https://tv.mail.ru/bishkek/channel/2595/").get();
            Elements time = tnt.getElementsByClass("p-programms__item__time-value");
            Elements name = tnt.getElementsByClass("p-programms__item__name-link");
            String firstT = time.get(0).text();
            String firstN = name.get(0).text();
            String secondT = time.get(1).text();
            String secondN = name.get(1).text();
            String thirdT = time.get(2).text();
            String thirdN = name.get(2).text();
            String fourthT = time.get(3).text();
            String fourthN = name.get(3).text();
            String fifthT = time.get(4).text();
            String fifthN = name.get(4).text();
            String sixT = time.get(5).text();
            String sixN = name.get(5).text();
            String seventhT = time.get(6).text();
            String seventhN = name.get(6).text();
            String eightT = time.get(7).text();
            String eightN = name.get(7).text();
            String ninthT = time.get(8).text();
            String ninthN = name.get(8).text();
            String tenT = time.get(9).text();
            String tenN = name.get(9).text();
            String elevenT = time.get(10).text();
            String elevenN = name.get(10).text();
            String twelveT = time.get(11).text();
            String twelveN = name.get(11).text();
            String thirteenthT = time.get(12).text();
            String thirteenthN = name.get(12).text();
            String fourteenT = time.get(13).text();
            String fourteenN = name.get(13).text();
            String fifteenT = time.get(14).text();
            String fifteenN = name.get(14).text();
            tntS = firstT + "|" + firstN + "\n" + secondT + "|" + secondN + "\n" + thirdT + "|" + thirdN + "\n" + fourthT + "|" + fourthN + "\n" + fifthT + "|" + fifthN + "\n" + sixT + "|" + sixN + "\n" + seventhT + "|" + seventhN + "\n" + eightT + "|" + eightN + "\n" + ninthT + "|" + ninthN + "\n" + tenT + "|" + tenN + "\n" + elevenT + "|" + elevenN + "\n" + twelveT+ "|" + twelveN + "\n" + thirteenthT + "|" + thirteenthN + "\n" + fourteenT+ "|" + fourteenN + "\n" + fifteenT+ "|" + fifteenN ;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init4() {
        runnable4 = new Runnable() {
            @Override
            public void run() {
                getweb4();
            }
        };
        Runnable target;
        Thread5 = new Thread(runnable4);
        Thread5.start();
    }

    private void getweb4() {
        try {
            Rossia24 = Jsoup.connect("https://tv.mail.ru/bishkek/channel/2519/").get();
            Elements time = Rossia24.getElementsByClass("p-programms__item__time-value");
            Elements name = Rossia24.getElementsByClass("p-programms__item__name-link");
            String firstT = time.get(0).text();
            String firstN = name.get(0).text();
            String secondT = time.get(1).text();
            String secondN = name.get(1).text();
            String thirdT = time.get(2).text();
            String thirdN = name.get(2).text();
            String fourthT = time.get(3).text();
            String fourthN = name.get(3).text();
            String fifthT = time.get(4).text();
            String fifthN = name.get(4).text();
            String sixT = time.get(5).text();
            String sixN = name.get(5).text();
            String seventhT = time.get(6).text();
            String seventhN = name.get(6).text();
            String eightT = time.get(7).text();
            String eightN = name.get(7).text();
            String ninthT = time.get(8).text();
            String ninthN = name.get(8).text();
            String tenT = time.get(9).text();
            String tenN = name.get(9).text();
            String elevenT = time.get(10).text();
            String elevenN = name.get(10).text();
            String twelveT = time.get(11).text();
            String twelveN = name.get(11).text();
            String thirteenthT = time.get(12).text();
            String thirteenthN = name.get(12).text();
            String fourteenT = time.get(13).text();
            String fourteenN = name.get(13).text();
            String fifteenT = time.get(14).text();
            String fifteenN = name.get(14).text();
            String sixteenT = time.get(15).text();
            String sixteenN = name.get(15).text();
            Rossia24S = firstT + "|" + firstN + "\n" + secondT + "|" + secondN + "\n" + thirdT + "|" + thirdN + "\n" + fourthT + "|" + fourthN + "\n" + fifthT + "|" + fifthN + "\n" + sixT + "|" + sixN + "\n" + seventhT + "|" + seventhN + "\n" + eightT + "|" + eightN + "\n" + ninthT + "|" + ninthN + "\n" + tenT + "|" + tenN + "\n" + elevenT + "|" + elevenN + "\n" + twelveT+ "|" + twelveN + "\n" + thirteenthT + "|" + thirteenthN + "\n" + fourteenT+ "|" + fourteenN + "\n" + fifteenT+ "|" + fifteenN ;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init3() {
        runnable3 = new Runnable() {
            @Override
            public void run() {
                getWeb3();
            }
        };
        Runnable target;
        Thread4 = new Thread(runnable3);
        Thread4.start();
    }

    private void getWeb3() {
        try {
            RenTv = Jsoup.connect("https://tv.mail.ru/bishkek/channel/2598/").get();
            Elements time = RenTv.getElementsByClass("p-programms__item__time-value");
            Elements name = RenTv.getElementsByClass("p-programms__item__name-link");
            String firstT = time.get(0).text();
            String firstN = name.get(0).text();
            String secondT = time.get(1).text();
            String secondN = name.get(1).text();
            String thirdT = time.get(2).text();
            String thirdN = name.get(2).text();
            String fourthT = time.get(3).text();
            String fourthN = name.get(3).text();
            String fifthT = time.get(4).text();
            String fifthN = name.get(4).text();
            String sixT = time.get(5).text();
            String sixN = name.get(5).text();
            String seventhT = time.get(6).text();
            String seventhN = name.get(6).text();
            String eightT = time.get(7).text();
            String eightN = name.get(7).text();
            String ninthT = time.get(8).text();
            String ninthN = name.get(8).text();
            String tenT = time.get(9).text();
            String tenN = name.get(9).text();
            RenTvS = firstT + "|" + firstN + "\n" + secondT + "|" + secondN + "\n" + thirdT + "|" + thirdN + "\n" + fourthT + "|" + fourthN + "\n" + fifthT + "|" + fifthN + "\n" + sixT + "|" + sixN + "\n" + seventhT + "|" + seventhN + "\n" + eightT + "|" + eightN + "\n" + ninthT + "|" + ninthN + "\n" + tenT + "|" + tenN  ;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init2() {
        runnable2 = new Runnable() {
            @Override
            public void run() {
                getWeb2();
            }
        };
        Thread3 = new Thread(runnable2);
        Thread3.start();
    }

    private void getWeb2() {
        try {
            Match = Jsoup.connect("https://tv.mail.ru/bishkek/channel/2060/").get();
            Elements time = Match.getElementsByClass("p-programms__item__time-value");
            Elements name = Match.getElementsByClass("p-programms__item__name-link");
            String firstT = time.get(0).text();
            String firstN = name.get(0).text();
            String secondT = time.get(1).text();
            String secondN = name.get(1).text();
            String thirdT = time.get(2).text();
            String thirdN = name.get(2).text();
            String fourthT = time.get(3).text();
            String fourthN = name.get(3).text();
            String fifthT = time.get(4).text();
            String fifthN = name.get(4).text();
            String sixT = time.get(5).text();
            String sixN = name.get(5).text();
            String seventhT = time.get(6).text();
            String seventhN = name.get(6).text();
            String eightT = time.get(7).text();
            String eightN = name.get(7).text();
            String ninthT = time.get(8).text();
            String ninthN = name.get(8).text();
            String tenT = time.get(9).text();
            String tenN = name.get(9).text();
            String elevenT = time.get(10).text();
            String elevenN = name.get(10).text();
            String twelveT = time.get(11).text();
            String twelveN = name.get(11).text();
            String thirteenthT = time.get(12).text();
            String thirteenthN = name.get(12).text();
            String fourteenT = time.get(13).text();
            String fourteenN = name.get(13).text();
            String fifteenT = time.get(14).text();
            String fifteenN = name.get(14).text();
            matchtvS = firstT + "|" + firstN + "\n" + secondT + "|" + secondN + "\n" + thirdT + "|" + thirdN + "\n" + fourthT + "|" + fourthN + "\n" + fifthT + "|" + fifthN + "\n" + sixT + "|" + sixN + "\n" + seventhT + "|" + seventhN + "\n" + eightT + "|" + eightN + "\n" + ninthT + "|" + ninthN + "\n" + tenT + "|" + tenN + "\n" + elevenT + "|" + elevenN + "\n" + twelveT+ "|" + twelveN + "\n" + thirteenthT + "|" + thirteenthN + "\n" + fourteenT+ "|" + fourteenN + "\n" + fifteenT+ "|" + fifteenN ;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void init() {
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        Runnable target;
        secThread = new Thread(runnable);
        secThread.start();

    }

    private void getWeb() {
        try {
            document = Jsoup.connect("https://tv.mail.ru/moskva/channel/850/").get();

            Elements time = document.getElementsByClass("p-programms__item__time-value");
            Elements name = document.getElementsByClass("p-programms__item__name-link");
            String firstT = time.get(0).text();
            String firstN = name.get(0).text();
            String secondT = time.get(1).text();
            String secondN = name.get(1).text();
            String thirdT = time.get(2).text();
            String thirdN = name.get(2).text();
            String fourthT = time.get(3).text();
            String fourthN = name.get(3).text();
            String fifthT = time.get(4).text();
            String fifthN = name.get(4).text();
            String sixT = time.get(5).text();
            String sixN = name.get(5).text();
            String seventhT = time.get(6).text();
            String seventhN = name.get(6).text();
            String eightT = time.get(7).text();
            String eightN = name.get(7).text();
            String ninthT = time.get(8).text();
            String ninthN = name.get(8).text();
            String tenT = time.get(9).text();
            String tenN = name.get(9).text();
            String elevenT = time.get(10).text();
            String elevenN = name.get(10).text();
            String twelveT = time.get(11).text();
            String twelveN = name.get(11).text();
            String thirteenthT = time.get(12).text();
            String thirteenthN = name.get(12).text();
            String fourteenT = time.get(13).text();
            String fourteenN = name.get(13).text();
            String fifteenT = time.get(14).text();
            String fifteenN = name.get(14).text();
            String sixteenT = time.get(15).text();
            String sixteenN = name.get(15).text();
            String seventeenT = time.get(16).text();
            String seventeenN = name.get(16).text();
            String eighteenT = time.get(17).text();
            String eighteenN = name.get(17).text();
//            String twentyT = time.get(19).text();
//            String twentyN = name.get(19).text();
//            String twentyoneT = time.get(20).text();
//            String twentyoneN = name.get(20).text();
            thewhole = firstT + "|" + firstN + "\n" + secondT + "|" + secondN + "\n" + thirdT + "|" + thirdN + "\n" + fourthT + "|" + fourthN + "\n" + fifthT + "|" + fifthN + "\n" + sixT + "|" + sixN + "\n" + seventhT + "|" + seventhN + "\n" + eightT + "|" + eightN + "\n" + ninthT + "|" + ninthN + "\n" + tenT + "|" + tenN + "\n" + elevenT + "|" + elevenN + "\n" + twelveT+ "|" + twelveN + "\n" + thirteenthT + "|" + thirteenthN + "\n" + fourteenT+ "|" + fourteenN + "\n" + fifteenT+ "|" + fifteenN ;

        } catch (IOException e) {
            e.printStackTrace();
        }

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
                    Intent intent = new Intent(TeleKanalActivity.this, GlavStranitsa.class);
                    startActivity(intent);
                    Animatoo.animateSlideRight(TeleKanalActivity.this);
                    finish();
                } else if (x1 > x2) {
                    Intent intent = new Intent(TeleKanalActivity.this, ProfileActivity.class);
                    sharedPreferences = getSharedPreferences(Shared_pref_Name, MODE_PRIVATE);
                    String name = sharedPreferences.getString(Key_Name, null);
                    intent.putExtra("newjourney", name);

                    startActivity(intent);
                    Animatoo.animateSlideLeft(TeleKanalActivity.this);
                    finish();
                }

                break;
        }
        return true;
    }

    void ini() {
        home = findViewById(R.id.homemain);
        channels = findViewById(R.id.telekanal);
        profile = findViewById(R.id.profile);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeleKanalActivity.this, GlavStranitsa.class));
                Animatoo.animateSlideRight(TeleKanalActivity.this);

                finish();
            }
        });
        channels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TeleKanalActivity.this, "Вы уже находитесь во вкладке телеканалы", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void OnMovieClick(Movie movie, ImageView movieImageView) {

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("imgCover", movie.getCoverPhotot());
        intent.putExtra("descript", movie.getDescription());
        intent.putExtra("url", movie.getStreamingLink());
        intent.putExtra("test", thewhole);
        intent.putExtra("tnt", tntS);
        intent.putExtra("mat", matchtvS);
        intent.putExtra("ren", RenTvS);
        intent.putExtra("ros", Rossia24S);
        intent.putExtra("sts",StsS);
        Activity activity;
        View view;
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(TeleKanalActivity.this, movieImageView, "sharedName");
        Toast.makeText(this, "item clicked: " + movie.getTitle(), Toast.LENGTH_LONG);
        startActivity(intent, options.toBundle());
    }

    private void Intest() {
        RecyclerView recyclerView = findViewById(R.id.Rv_telik);

        recyclerView.setAdapter(channelsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }
}