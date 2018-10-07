package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FAQ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
    }
    public void query(View v)

    {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("mailto:"));
        i.setType("plain/text");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"developer.somaiyaclassroom@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Query regarding Somaiya Classroom");
        startActivity(i);
    }
}
