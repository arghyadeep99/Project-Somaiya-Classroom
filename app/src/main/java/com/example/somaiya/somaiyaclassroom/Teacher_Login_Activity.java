package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Teacher_Login_Activity extends AppCompatActivity {
    private Button mCourse;
    private Button mSyllabus;
    private Button mPrevYears;
    private Button mEasySol;
    private Button mCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__login);
        mCourse = (Button) findViewById(R.id.courseContent);
        mSyllabus = (Button) findViewById(R.id.syllabus);
        mPrevYears = (Button) findViewById(R.id.prevYears);
        mEasySol = (Button) findViewById(R.id.easySol);
        mCalendar = (Button) findViewById(R.id.addEvent);
        mCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitycourseMaterial();
            }
        });
        mSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySyllabus();
            }
        });
        mPrevYears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityprevYear();
            }
        });
        mCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityaddEvent();
            }
        });
        mEasySol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityeasySol();
            }
        });
    }
    public void openActivitycourseMaterial() {
        Intent main_intent = new Intent(Teacher_Login_Activity.this,courseMaterial.class);
        startActivity(main_intent);
    }
    public void openActivitySyllabus() {
        Intent main_intent = new Intent(Teacher_Login_Activity.this,Syllabus.class);
        startActivity(main_intent);
    }
    public void openActivityprevYear() {
        Intent main_intent = new Intent(Teacher_Login_Activity.this,PreviousPapers.class);
        startActivity(main_intent);
    }
    public void openActivityaddEvent() {
        Intent main_intent = new Intent(Teacher_Login_Activity.this,Addevent.class);
        startActivity(main_intent);
    }
    public void openActivityeasySol() {
        Intent main_intent = new Intent(Teacher_Login_Activity.this,Easysolution.class);
        startActivity(main_intent);
    }
}
