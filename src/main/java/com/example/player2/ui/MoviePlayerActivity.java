package com.example.player2.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Movie;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.player2.R;
import com.example.player2.models.Puser;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource.Factory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

public class MoviePlayerActivity extends AppCompatActivity {
private PlayerView videoPlayer;
ProgressBar progressBar;
ImageView btFullScreen;
SimpleExoPlayer simpleExoPlayer;
String jika;
int i =0;
int j=0;
Long a;
Long b;
String url_single="https://rubidium.stream.voidboost.cc/6/9/5/2/3/7d6c579d3df288213eb2d530155c75c6:2021050221:dWR2QkQxc0YvR2NRaFZ2WXVDbkZCMmhrUXdtOFVHdWVjTTBUUnY4dmpJMS9rU3FLT1BLcjJacXRFYy9NMU5Kd016TnVkYkdCSkZoVmUxZGhsRGNwR2E0ODdqKzFjM3FVYzVjcHFPdDVnTUk9/bjpci.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_player);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        videoPlayer =findViewById(R.id.player_view);
        getin();

        initPlayer();
        Button button=findViewById(R.id.button10);
        OrientationEventListener mOrientationListener = new OrientationEventListener(
                getApplicationContext()) {
            @Override
            public void onOrientationChanged(int orientation) {
                
                if (orientation == 0 || orientation == 180) {
                    if(simpleExoPlayer.getCurrentPosition()<10000){
                        a=simpleExoPlayer.getCurrentPosition();

                    }
                } else if (orientation == 90 || orientation == 270) {
                        simpleExoPlayer.seekTo(a);
                    System.out.println(a.toString());

                }
            }
        };

        if (mOrientationListener.canDetectOrientation()) {
            mOrientationListener.enable();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                Handler handler = new Handler();
                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        i=0;
                    }
                };
                if(i%2!=0){
                    handler.postDelayed(runnable,400);

                }
                else if(i%2==0){
                    if(simpleExoPlayer.getCurrentPosition()<10000){
                           simpleExoPlayer.seekTo(0);
                    }
                    else {
                        simpleExoPlayer.seekTo(simpleExoPlayer.getCurrentPosition()-10000);

                    }

                }

            }
        });
        Button button1=findViewById(R.id.button13);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j++;
                Handler handler = new Handler();
                Runnable run=new Runnable() {
                    @Override
                    public void run() {
                      j=0;
                    }
                };
                if(j%2!=0){
                    handler.postDelayed(run,400);

                }
                else if(j%2==0) {
                    simpleExoPlayer.seekTo(simpleExoPlayer.getCurrentPosition() + 10000);
                }
            }
        });

    }


    private void getin() {
         jika=getIntent().getStringExtra("Newurl");
    }
    private void initPlayer() {
        simpleExoPlayer=new SimpleExoPlayer.Builder(this).build();

        videoPlayer.setPlayer(simpleExoPlayer);


        MovieDetailActivity movieDetailActivity=new MovieDetailActivity();
        MediaItem mediaItem=MediaItem.fromUri(Uri.parse(jika));
        simpleExoPlayer.addMediaItem(mediaItem);
        simpleExoPlayer.prepare();
        simpleExoPlayer.setPlayWhenReady(true);




    }

    protected void onDestroy() {
        //other codes
        super.onDestroy();
        simpleExoPlayer.stop();
    }
    @Override
    protected void onStop() {
        if(simpleExoPlayer.isPlaying()){
            simpleExoPlayer.pause();

        }
        super.onStop();
    }
}


