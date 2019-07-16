package com.example.hellofirst;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private EditText medittext;
    private Button mbutton;
    private Button mbutton2;
    private VideoView videoView;
    private MediaController mediaController;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        //创建activity xml布局
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoview);
        mbutton = (Button) findViewById(R.id.button1);
        medittext = (EditText) findViewById(R.id.edittext1);
        mbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                medittext.setText("rim.mp4");
                init();
            }
        });
        mbutton2 = (Button)findViewById(R.id.button2);
        mbutton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                medittext.setText("");
                videoView.stopPlayback();
            }
        });
    }
    public void init() {

        videoView = (VideoView) findViewById(R.id.videoView1);
        mediaController = new MediaController(this);
        //本地连接地址
        String uri = "android.resource://" + getPackageName() + "/"+ R.raw.rim;
        //网络连接地址
        //String uri = "https://www.meipai.com/media/1127005992";
        videoView.setVideoURI(Uri.parse(uri));
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();
        videoView.start();
    }
}