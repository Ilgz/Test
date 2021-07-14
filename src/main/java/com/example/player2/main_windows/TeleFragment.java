package com.example.player2.main_windows;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.player2.R;
import com.example.player2.adapters.MovieItemClickList;
import com.example.player2.adapters.TeleAdapter;
import com.example.player2.models.Movie;
import com.example.player2.ui.MediaActivity;
import com.example.player2.ui.TeleDetailActivity;
import com.example.player2.utils.DataSource;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class TeleFragment extends Fragment implements MovieItemClickList {
    private  String whole, matchTvS, RenTvS, Russia24S, StsS;
    private  String tntS;
    private View v;



    public TeleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tele, container, false);

            ini();
            TextView textView = v.findViewById(R.id.textView4);
            textView.setOnClickListener(v -> numb());
            if(getActivity()!=null) {
                Intest();
            }
            init();
            init2();
            init3();
            init4();
            init5();
            init6();
        BottomNavigationView bottomNavigationView = v.findViewById(R.id.bottomnav);
        bottomNavigationView.setSelectedItemId(R.id.menu_telek);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.glavstr) {
                startActivity(new Intent(getActivity(), MediaActivity.class));
                getActivity().finish();
                return true;
            } else if (item.getItemId() == R.id.menu_profile) {
                startActivity(new Intent(getActivity(), ProfileActivity.class));
                getActivity().finish();

                return true;
            } else return item.getItemId() == R.id.menu_telek;
        });
        return v;

    }
    private void init6() {
        Runnable runnable6 = this::getWeb6;
        Thread thread7 = new Thread(runnable6);
        thread7.start();
    }

    private void getWeb6() {
        try {
            Document sts = Jsoup.connect("https://tv.mail.ru/moskva/channel/1112/").get();
            Elements time = sts.getElementsByClass("p-programms__item__time-value");
            Elements name = sts.getElementsByClass("p-programms__item__name-link");
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
            StsS = firstT + "|" + firstN + "\n" + secondT + "|" + secondN + "\n" + thirdT + "|" + thirdN + "\n" + fourthT + "|" + fourthN + "\n" + fifthT + "|" + fifthN + "\n" + sixT + "|" + sixN + "\n" + seventhT + "|" + seventhN + "\n" + eightT + "|" + eightN + "\n" + ninthT + "|" + ninthN + "\n" + tenT + "|" + tenN + "\n" + elevenT + "|" + elevenN + "\n" + twelveT+ "|" + twelveN + "\n" + thirteenthT + "|" + thirteenthN + "\n" + fourteenT+ "|" + fourteenN + "\n" + fifteenT+ "|" + fifteenN ;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init5() {
        Runnable runnable5 = this::getWeb5;
        Thread thread6 = new Thread(runnable5);
        thread6.start();
    }

    private void getWeb5() {
        try {
            Document tnt = Jsoup.connect("https://tv.mail.ru/bishkek/channel/2595/").get();
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
        Runnable runnable4 = this::getweb4;
        Thread thread5 = new Thread(runnable4);
        thread5.start();
    }

    private void getweb4() {
        try {
            Document russia24 = Jsoup.connect("https://tv.mail.ru/bishkek/channel/2519/").get();
            Elements time = russia24.getElementsByClass("p-programms__item__time-value");
            Elements name = russia24.getElementsByClass("p-programms__item__name-link");
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
            Russia24S = firstT + "|" + firstN + "\n" + secondT + "|" + secondN + "\n" + thirdT + "|" + thirdN + "\n" + fourthT + "|" + fourthN + "\n" + fifthT + "|" + fifthN + "\n" + sixT + "|" + sixN + "\n" + seventhT + "|" + seventhN + "\n" + eightT + "|" + eightN + "\n" + ninthT + "|" + ninthN + "\n" + tenT + "|" + tenN + "\n" + elevenT + "|" + elevenN + "\n" + twelveT+ "|" + twelveN + "\n" + thirteenthT + "|" + thirteenthN + "\n" + fourteenT+ "|" + fourteenN + "\n" + fifteenT+ "|" + fifteenN ;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init3() {
        Runnable runnable3 = this::getWeb3;
        Thread thread4 = new Thread(runnable3);
        thread4.start();
    }

    private void getWeb3() {
        try {
            Document renTv = Jsoup.connect("https://tv.mail.ru/bishkek/channel/2598/").get();
            Elements time = renTv.getElementsByClass("p-programms__item__time-value");
            Elements name = renTv.getElementsByClass("p-programms__item__name-link");
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
        Runnable runnable2 = this::getWeb2;
        Thread thread3 = new Thread(runnable2);
        thread3.start();
    }

    private void getWeb2() {
        try {
            Document match = Jsoup.connect("https://tv.mail.ru/bishkek/channel/2060/").get();
            Elements time = match.getElementsByClass("p-programms__item__time-value");
            Elements name = match.getElementsByClass("p-programms__item__name-link");
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
            matchTvS = firstT + "|" + firstN + "\n" + secondT + "|" + secondN + "\n" + thirdT + "|" + thirdN + "\n" + fourthT + "|" + fourthN + "\n" + fifthT + "|" + fifthN + "\n" + sixT + "|" + sixN + "\n" + seventhT + "|" + seventhN + "\n" + eightT + "|" + eightN + "\n" + ninthT + "|" + ninthN + "\n" + tenT + "|" + tenN + "\n" + elevenT + "|" + elevenN + "\n" + twelveT+ "|" + twelveN + "\n" + thirteenthT + "|" + thirteenthN + "\n" + fourteenT+ "|" + fourteenN + "\n" + fifteenT+ "|" + fifteenN ;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void init() {
        Runnable runnable = this::getWeb;
        Thread secThread = new Thread(runnable);
        secThread.start();

    }

    private void getWeb() {
        try {
            Document document = Jsoup.connect("https://tv.mail.ru/moskva/channel/850/").get();

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


            whole = firstT + "|" + firstN + "\n" + secondT + "|" + secondN + "\n" + thirdT + "|" + thirdN + "\n" + fourthT + "|" + fourthN + "\n" + fifthT + "|" + fifthN + "\n" + sixT + "|" + sixN + "\n" + seventhT + "|" + seventhN + "\n" + eightT + "|" + eightN + "\n" + ninthT + "|" + ninthN + "\n" + tenT + "|" + tenN + "\n" + elevenT + "|" + elevenN + "\n" + twelveT+ "|" + twelveN + "\n" + thirteenthT + "|" + thirteenthN + "\n" + fourteenT+ "|" + fourteenN + "\n" + fifteenT+ "|" + fifteenN ;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    void ini() {


    }


    @Override
    public void OnMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(getActivity(), TeleDetailActivity.class);
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("descript", movie.getDescription());
        intent.putExtra("url", movie.getStreamingLink());
        intent.putExtra("test", whole);
        intent.putExtra("tnt", tntS);
        intent.putExtra("mat", matchTvS);
        intent.putExtra("ren", RenTvS);
        intent.putExtra("ros", Russia24S);
        intent.putExtra("sts",StsS);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), movieImageView, "sharedName");
        startActivity(intent, options.toBundle());
    }

    private void Intest() {
         TeleAdapter channelsAdapter = new TeleAdapter(getActivity(), DataSource.getChannels(), this);

        RecyclerView recyclerView = v.findViewById(R.id.Rv_telik);

        recyclerView.setAdapter(channelsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        RecyclerView recyclerView1=v.findViewById(R.id.Rv_sport);
        recyclerView1.setAdapter(channelsAdapter);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
    }

    public void numb() {
        startActivity(new Intent(getActivity(), Support.class));
    }
}