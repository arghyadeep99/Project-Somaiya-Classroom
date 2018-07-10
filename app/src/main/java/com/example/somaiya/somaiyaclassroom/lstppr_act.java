package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class lstppr_act extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lstppr_act);
        Magnify mag = new Magnify();
        float zoomFactor = 1.25f;
        if(Magnify.getInstance().getData())
            mag.enlarge(true,findViewById(android.R.id.content),zoomFactor);
    }
    public void open_download(View view) {
        Intent upload = new Intent(lstppr_act.this, DownloadFile.class);
        upload.putExtra("buttonTracker",5);
        startActivity(upload);
    }
    public void open_download2(View view) {
        Intent upload = new Intent(lstppr_act.this, DownloadFile.class);
        upload.putExtra("buttonTracker",6);
        startActivity(upload);
    }
}
