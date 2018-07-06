package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class course_act extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_act);
        Magnify mag = new Magnify();
        float zoomFactor = 1.25f;
        if(Magnify.getInstance().getData())
            mag.enlarge(true,findViewById(android.R.id.content),zoomFactor);
    }
    public void open_download(View view) {
        Intent upload = new Intent(course_act.this, DownloadFile.class);
        upload.putExtra("buttonTracker",2);
        startActivity(upload);
    }
}
