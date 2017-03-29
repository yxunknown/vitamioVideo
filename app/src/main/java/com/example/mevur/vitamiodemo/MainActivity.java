package com.example.mevur.vitamiodemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);
        if (!LibsChecker.checkVitamioLibs(this)) {
            return;
        }
        videoView = (VideoView) findViewById(R.id.video_player);
    }
    public void play(View view) {
        EditText url = (EditText) findViewById(R.id.edt_url);
        String path = url.getText().toString();
        if ("".equals(path)) {
            Toast.makeText(this, "path 为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i("path", path);
        videoView.setVideoPath(path);
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.setOnPreparedListener(this);

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.i("video", "prepared");
        mp.setPlaybackSpeed(1.0F);
    }
}
