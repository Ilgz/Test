package com.example.player2.ui;


import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import com.bumptech.glide.Glide;
import com.example.player2.R;
import com.monstertechno.adblocker.AdBlockerWebView;
import com.monstertechno.adblocker.util.AdBlocker;

import org.adblockplus.libadblockplus.android.webview.AdblockWebView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("ALL")
public class TeleDetailActivity extends AppCompatActivity {
    private  TextView tv_title;
    private String function;
    private AdblockWebView webView;
    private      ProgressBar progressBar;
    private      CustomWebChromeClient customWebChromeClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // code for portrait mode
            Objects.requireNonNull(getSupportActionBar()).hide();
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        progressBar=findViewById(R.id.progressBar2);
        iniViews();
        getSupportActionBar().setTitle(tv_title.getText().toString());
        if(WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)){
            WebSettingsCompat.setForceDark(webView.getSettings(),WebSettingsCompat.FORCE_DARK_ON);
        }

    }




    private  class Browser_home  extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url) {
            view.loadUrl(url);

            return true;
        }
        private final Map<String, Boolean> loadedUrls=new HashMap<>();

        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            boolean ad;
            if(!loadedUrls.containsKey(url)){
                ad=AdBlocker.isAd(url);
                loadedUrls.put(url,ad);

            }
            else{
                ad=loadedUrls.get(url);
            }
            return ad ? AdBlocker.createEmptyResource():
                    super.shouldInterceptRequest(view,url);
        }


        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            String javascript = function;
            webView.loadUrl(javascript);
            if(WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)){
                WebSettingsCompat.setForceDark(webView.getSettings(),WebSettingsCompat.FORCE_DARK_ON);
            }
        }
    }


    void iniViews(){
        String movieTitle = getIntent().getExtras().getString("title");
        String year=getIntent().getExtras().getString("year");
        String imageResouceId = getIntent().getExtras().getString("imgURL");
        String link = getIntent().getStringExtra("url");
        String ort=getIntent().getStringExtra("test");
        String tnt=getIntent().getStringExtra("tnt");
        String matchtv=getIntent().getStringExtra("mat");
        String rentv=getIntent().getStringExtra("ren");
        String ros=getIntent().getStringExtra("ros");
        String sts=getIntent().getStringExtra("sts");
        function=getIntent().getStringExtra("function");

        TextView textView=findViewById(R.id.textView12);
        textView.setText(year);
        ImageView movieThumbnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResouceId).into(movieThumbnailImg);
        AdBlocker.init(this);
        webView =findViewById(R.id.webView);
        customWebChromeClient=new CustomWebChromeClient();
        webView.setWebChromeClient(customWebChromeClient);

        webView.loadUrl(link);

        new AdBlockerWebView.init(this).initializeWebView(webView);
        webView.setWebViewClient(new Browser_home());


        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        tv_title=findViewById(R.id.detail_movie_title);

        tv_title.setText(movieTitle);

        String a=tv_title.getText().toString();
        TextView tv_descrtiption = findViewById(R.id.detail_movie_desc);


         if(a.contains("Тнт")){
            tv_descrtiption.setText(tnt);

        }
        else if(a.contains("МатчТв")){
            tv_descrtiption.setText(matchtv);
        }
        else if(a.contains("РенТв")){
            tv_descrtiption.setText(rentv);
        }
        else if(a.contains("Россия24")){
            tv_descrtiption.setText(ros);
        }
        else if(a.contains("Стс")){
            tv_descrtiption.setText(sts);
        }
        else  {
            tv_descrtiption.setText(ort);
        }



    }

    public class CustomWebChromeClient extends WebChromeClient {
        public void onProgressChanged(WebView view, int progress) {
            progressBar.setProgress(progress);
            if (progress == 100) {
                webView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                //your url is loaded successfully
                if(WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)){
                    WebSettingsCompat.setForceDark(webView.getSettings(),WebSettingsCompat.FORCE_DARK_OFF);
                }
            }
        }
        View view;
        Dialog dialog;
        @Override
        public void onShowCustomView(View view, final CustomViewCallback callback) {
            this.view=view;
            dialog = new Dialog(TeleDetailActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            view.setBackgroundColor(getResources().getColor(R.color.black));
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

}
