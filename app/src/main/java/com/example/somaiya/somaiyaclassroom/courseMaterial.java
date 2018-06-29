package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class courseMaterial extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_material);
    }

    public void open_upload(View view) {
            Intent upload = new Intent(courseMaterial.this, UploadFile.class);
            upload.putExtra("buttonTracker",2);
            startActivity(upload);
    }
    }


