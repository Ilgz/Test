 package com.example.player2.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.player2.R;
import com.monstertechno.adblocker.AdBlockerWebView;
import com.monstertechno.adblocker.util.AdBlocker;

import org.adblockplus.libadblockplus.android.webview.AdblockWebView;

import java.util.HashMap;
import java.util.Map;

 public class MovieDetailActivity extends AppCompatActivity {
private ImageView MovieThumbnailImg;
private  TextView tv_title,tv_descrtiption;
private String link,function,movieTitle;
private AdblockWebView webView;
private      Boolean failedLoading = false;
private      ProgressBar progressBar;
private      CustomWebChromeClient customWebChromeClient;
private       String imageResouceId;
private      ImageButton fav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // code for portrait mode
            getSupportActionBar().hide();
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar=findViewById(R.id.progressBar2);
        iniViews();
fav=findViewById(R.id.imageButton);
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
         private Map<String, Boolean> loadedUrls=new HashMap<>();

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
             failedLoading = true;
         }

         @Override
         public void onPageFinished(WebView view, String url) {
             super.onPageFinished(view, url);


         }
     }


     void iniViews(){
          movieTitle = getIntent().getExtras().getString("title");
          String year=getIntent().getExtras().getString("year");
         imageResouceId=getIntent().getExtras().getString("imgURL");
       String tv_decrtiption=getIntent().getExtras().getString("descript");
         link=getIntent().getStringExtra("url");
          String ort=getIntent().getStringExtra("test");
          String tnt=getIntent().getStringExtra("tnt");
          String matchtv=getIntent().getStringExtra("mat");
          String rentv=getIntent().getStringExtra("ren");
          String ros=getIntent().getStringExtra("ros");
          String sts=getIntent().getStringExtra("sts");
         function=getIntent().getStringExtra("function");

         TextView textView=findViewById(R.id.textView12);
          textView.setText(year);
         MovieThumbnailImg=findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResouceId).into(MovieThumbnailImg);
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
        tv_descrtiption=findViewById(R.id.detail_movie_desc);
        if(!a.contains("Стс")&&!a.contains("Тнт")&&!a.contains("Орт")&&!a.contains("МатчТв")&&!a.contains("Россия24")&&!a.contains("РенТв")){
            tv_descrtiption.setText("Сюжет:\n"+tv_decrtiption);

        }

        else if(a.contains("Тнт")){
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
              dialog = new Dialog(MovieDetailActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
             view.setBackgroundColor(getResources().getColor(R.color.black));
             dialog.setContentView(view);
             dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                 @Override
                 public void onDismiss(DialogInterface dialog) {
                     callback.onCustomViewHidden();
                     customWebChromeClient.onHideCustomView();
                 }
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
