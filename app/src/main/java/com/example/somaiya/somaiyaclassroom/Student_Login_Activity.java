package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URI;

public class Student_Login_Activity extends AppCompatActivity {
    //TextView HyperLink;
    Spanned Text;
    Button add_event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__login);
        /*HyperLink = (TextView)findViewById(R.id.textView1_stu);

        Text = Html.fromHtml("<a href='https://www.google.com/calendar'>Add Event</a>");

        HyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        HyperLink.setText(Text);
    }*/
        add_event = (Button) findViewById(R.id.textView1_stu);
        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalender(v);
            }
        });
        }
    public void openCalender(View v) {
        Uri uri = Uri.parse("https://www.google.com/calendar");
        Intent i = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(i);
    }
    public void easy (View v){
        Intent i = new Intent(this, esysol_act.class);
        startActivity(i);
    }

    public void SYL (View v){
        Intent i = new Intent(this, syl_act.class);
        startActivity(i);
    }

    public void lstppr (View v){
        Intent i = new Intent(this, lstppr_act.class);
        startActivity(i);
    }

    public void CRSE (View v){
        Intent i = new Intent(this, course_act.class);
        startActivity(i);
    }
}
