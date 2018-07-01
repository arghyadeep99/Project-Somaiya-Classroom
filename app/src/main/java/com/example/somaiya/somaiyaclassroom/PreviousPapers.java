package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PreviousPapers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_papers);
    }
    public void open_upload(View view){
        Intent upload = new Intent(PreviousPapers.this, UploadFile.class);
        upload.putExtra("buttonTracker",5);
        startActivity(upload);
    }
    public void open_upload2(View view){
        Intent upload = new Intent(PreviousPapers.this, UploadFile.class);
        upload.putExtra("buttonTracker",6);
        startActivity(upload);
    }
}
