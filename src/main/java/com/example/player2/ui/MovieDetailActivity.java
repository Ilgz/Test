package com.example.player2.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import com.bumptech.glide.Glide;
import com.example.player2.R;
import com.monstertechno.adblocker.AdBlockerWebView;
import com.monstertechno.adblocker.util.AdBlocker;

import org.adblockplus.libadblockplus.android.webview.AdblockWebView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovieDetailActivity extends AppCompatActivity {
    private TextView tv_title;
    private AdblockWebView webView;
    private ProgressBar progressBar;
    private CustomWebChromeClient customWebChromeClient;
    private String SiteLink;
    private TextView textView;
    private String year;
    private TextView tv_movie_desc;
    private String Description;
    private String link;
    private TextView reviews;
    private String Rating;
    private TextView Country;
    private String countryS;
    private static int sessionDepth = 0;
    Context context;
    @Override
    @SuppressWarnings("DEPRECATION")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
       Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        context=getApplicationContext();


        iniViews();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {

            try {
                String url = SiteLink;
              Document  document = Jsoup.connect(url).get();
                year = document.getElementsByClass("rl-2").tagName("a").get(1).text();
                countryS = document.getElementsByClass("rl-2").tagName("a").get(2).text();
                Description = document.getElementsByTag("p").get(0).text().replace("© ГидОнлайн", "");
                Rating = document.getElementsByClass("ratings-score").text();
                link = document.getElementsByTag("iframe").attr("src");
            } catch (IOException e) {
                e.printStackTrace();
            }



            handler.post(() -> {
                String fur=getString(R.string.Год) + year;
                textView.setText(fur);
                tv_movie_desc.setText(Description);
                webView.loadUrl(link);
                reviews.setText( Rating);
                String count=getString(R.string.Страна) +countryS;
                Country.setText(count);
            });
        });

        progressBar = findViewById(R.id.progressBar2);
        Objects.requireNonNull(getSupportActionBar()).setTitle(tv_title.getText().toString());
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            WebSettingsCompat.setForceDark(webView.getSettings(), WebSettingsCompat.FORCE_DARK_ON);
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    void iniViews() {
        String movieTitle = getIntent().getExtras().getString("title");
        String imageResouceId = getIntent().getExtras().getString("imgURL");
        String ort = getIntent().getStringExtra("test");
        String tnt = getIntent().getStringExtra("tnt");
        String MatchTv = getIntent().getStringExtra("mat");
        String RenTv = getIntent().getStringExtra("ren");
        String ros = getIntent().getStringExtra("ros");
        String sts = getIntent().getStringExtra("sts");
        SiteLink = getIntent().getStringExtra("siteLink");

        textView = findViewById(R.id.textView12);
        ImageView movieThumbnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResouceId).into(movieThumbnailImg);
        AdBlocker.init(this);
        webView = findViewById(R.id.webView);
        customWebChromeClient = new CustomWebChromeClient();
        webView.setWebChromeClient(customWebChromeClient);


        new AdBlockerWebView.init(this).initializeWebView(webView);
        webView.setWebViewClient(new Browser_home());


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        tv_title = findViewById(R.id.detail_movie_title);
        Country = findViewById(R.id.textView16);
        tv_title.setText(movieTitle);
        reviews = findViewById(R.id.textView18);
        String a = tv_title.getText().toString();
        tv_movie_desc = findViewById(R.id.detail_movie_desc);
        if (a.contains("Тнт")) {
            tv_movie_desc.setText(tnt);

        } else if (a.contains("МатчТв")) {
            tv_movie_desc.setText(MatchTv);
        } else if (a.contains("РенТв")) {
            tv_movie_desc.setText(RenTv);
        } else if (a.contains("Россия24")) {
            tv_movie_desc.setText(ros);
        } else if (a.contains("Стс")) {
            tv_movie_desc.setText(sts);
        } else {
            tv_movie_desc.setText(ort);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        webView.stopLoading();
        if (sessionDepth > 0)
            sessionDepth--;
        if (sessionDepth == 0) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        sessionDepth++;
        if(sessionDepth == 1){
            //app came to foreground;
        }
    }

    private static class Browser_home extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return true;
        }




        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);


        }
    }

    public class CustomWebChromeClient extends WebChromeClient {
        View view;
        Dialog dialog;

        public void onProgressChanged(WebView view, int progress) {
            progressBar.setProgress(progress);
            if (progress == 100) {
                webView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                //your url is loaded successfully
                if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                    WebSettingsCompat.setForceDark(webView.getSettings(), WebSettingsCompat.FORCE_DARK_OFF);
                }
            }
        }

        @Override
        public void onShowCustomView(View view, final CustomViewCallback callback) {
            this.view = view;
            Context context = view.getContext();

            dialog = new Dialog(MovieDetailActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            view.setBackgroundColor(ContextCompat.getColor( context,R.color.black));
            dialog.setContentView(view);
            dialog.setOnDismissListener(dialog -> {
                callback.onCustomViewHidden();
                customWebChromeClient.onHideCustomView();
            });
            dialog.show();

        }

        @Override
        public void onHideCustomView() {
            dialog.dismiss();
            super.onHideCustomView();
        }
    }


}
