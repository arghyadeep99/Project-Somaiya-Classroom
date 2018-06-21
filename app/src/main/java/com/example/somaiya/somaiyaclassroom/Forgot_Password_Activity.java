package com.example.somaiya.somaiyaclassroom;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class Forgot_Password_Activity extends AppCompatActivity {
    private Button play_video;
    VideoView view_video;
    MediaController media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        view_video= (VideoView) findViewById(R.id.videoView);
        media= new MediaController(this);
        String video_path="android.resource://com.example.somaiya.somaiyaclassroom/"+ R.raw.reset;
        Uri uri=Uri.parse(video_path);
        view_video.setVideoURI(uri);
        view_video.setMediaController(media);
        media.setAnchorView(view_video);
        view_video.start();
    }
}
