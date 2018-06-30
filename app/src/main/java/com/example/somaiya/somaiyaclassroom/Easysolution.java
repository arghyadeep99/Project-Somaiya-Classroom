package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Easysolution extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easysolution);
    }
    public void open_upload(View view){
        Intent upload = new Intent(Easysolution.this, UploadFile.class);
        upload.putExtra("buttonTracker",4);

        startActivity(upload);
    }
}
