package com.example.player2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.player2.main_windows.TeleKanalActivity;

public class TvWatch extends AppCompatActivity {
 public String videoUrl;
private ProgressDialog pd;
VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_watch);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Intent intent=getIntent();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        videoUrl=intent.getStringExtra("key");
        videoView=findViewById(R.id.videoView);
        Context context;
        pd=new ProgressDialog(this);
        pd.setMessage("Buffering");
        pd.setCancelable(true);
        playVideo();
    }

    private void playVideo() {
        try{
getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController=new MediaController(this);
            mediaController.setAnchorView(videoView);
            Uri videoUri=Uri.parse(videoUrl);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(videoUri);
            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    pd.dismiss();
                    videoView.start();
                }
            });
        }
        catch (Exception e){
            pd.dismiss();
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}