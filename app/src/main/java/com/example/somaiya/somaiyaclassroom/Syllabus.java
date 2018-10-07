package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Syllabus extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
    }

    public void open_upload(View view){
        Intent upload = new Intent(Syllabus.this, UploadFile.class);
        upload.putExtra("buttonTracker",1);
        startActivity(upload);
    }
}
