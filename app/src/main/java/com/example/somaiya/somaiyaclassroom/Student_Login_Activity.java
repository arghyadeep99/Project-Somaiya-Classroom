package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Student_Login_Activity extends AppCompatActivity {
    TextView HyperLink;
    Spanned Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__login);
        HyperLink = (TextView)findViewById(R.id.textView1_stu);

        Text = Html.fromHtml("<a href='https://www.google.com/calendar'>CALENDAR</a>");

        HyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        HyperLink.setText(Text);
    }

    public void easy(View v) {
        Intent i = new Intent(this, esysol_act.class);
        startActivity(i);
    }

    public void SYL(View v) {
        Intent i = new Intent(this, syl_act.class);
        startActivity(i);
    }

    public void lstppr(View v) {
        Intent i = new Intent(this, lstppr_act.class);
        startActivity(i);
    }

    public void CRSE(View v) {
        Intent i = new Intent(this, course_act.class);
        startActivity(i);
    }


}