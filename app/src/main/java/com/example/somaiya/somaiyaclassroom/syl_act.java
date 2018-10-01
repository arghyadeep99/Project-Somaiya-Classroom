package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class syl_act extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syl_act);
        Magnify mag = new Magnify();
        float zoomFactor = 1.25f;
        if(Magnify.getInstance().getData())
            mag.enlarge(true,findViewById(android.R.id.content),zoomFactor);
    }
    public void openDownload(View view)
    {
        Intent i = new Intent(syl_act.this,DownloadFile.class);
        i.putExtra("buttonTracker",1);
        startActivity(i);
    }
}
