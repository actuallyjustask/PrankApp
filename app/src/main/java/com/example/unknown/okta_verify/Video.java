package com.example.unknown.okta_verify;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    //AudioManager audioManager;
    VideoView player;
    String videoPath;
    int time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_video);
        int id = getResources().getIdentifier("video", "raw", getPackageName());
        videoPath = "android.resource://" + getPackageName() + "/" + id;
        //audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        //audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_PLAY_SOUND);
        //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)/2, AudioManager.FLAG_PLAY_SOUND);
    }


    @Override
    protected void onStart() {

        // Stop background audio service
        stopService(new Intent(this, Audio.class));


        player = findViewById(R.id.videoView);
        player.setVideoURI(Uri.parse(videoPath));
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                player.start();
            }
        });
        player.seekTo(time);
        player.start();
        super.onStart();
    }


    @Override
    protected void onPause() {
        super.onPause();
        time = player.getCurrentPosition();
    }


    @Override
    protected void onStop() {
        super.onStop();

        // Start background audio service
        


        // Prevent app from quiting
        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);
        Snackbar.make(findViewById(R.id.relativeLayout), "lol, not going to work",
                Snackbar.LENGTH_LONG).show();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("videoPath", videoPath);
        outState.putInt("time", time);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            videoPath = savedInstanceState.getString("videoPath");
            time = savedInstanceState.getInt("time", 0);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP
                || keyCode == KeyEvent.KEYCODE_VOLUME_MUTE || keyCode == KeyEvent.KEYCODE_VOICE_ASSIST) {
            Snackbar.make(findViewById(R.id.relativeLayout), "Nope, No volume control for you",
                    Snackbar.LENGTH_LONG).setAction("Rage Quit", quit).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Snackbar.make(findViewById(R.id.relativeLayout), "lol, not going to work",
                Snackbar.LENGTH_LONG).show();
    }


//    View.OnClickListener maxVolume = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_PLAY_SOUND);
//            Snackbar.make(findViewById(R.id.relativeLayout), "LOLOLOLOLOLOLOL", Snackbar.LENGTH_LONG).show();
//            maxVolume = null;
//        }
//    };


    View.OnClickListener quit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
