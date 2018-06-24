package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Student_Login_Activity extends AppCompatActivity{
    private Button mCourse;
    private Button mSyllabus;
    private Button mPrevYears;
    private Button mEasySol;
    private Button view_events;
    private Button Logout;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__login);
        mCourse = (Button) findViewById(R.id.courseContent);
        mSyllabus = (Button) findViewById(R.id.syllabus);
        mPrevYears = (Button) findViewById(R.id.prevYears);
        mEasySol = (Button) findViewById(R.id.easySol);
        Logout = (Button) findViewById(R.id.logout);
        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this, AlreadyRegisteredLoginStudent.class));
        }

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
        mEasySol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityeasySol();
            }
        });
        view_events = (Button) findViewById(R.id.textView1_stu);
        view_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar(v);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOut();
            }
        });
    }
    public void openCalendar(View v) {
        Uri uri = Uri.parse("https://www.google.com/calendar");
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
    public void openActivitycourseMaterial() {
        Intent main_intent = new Intent(Student_Login_Activity.this,course_act.class);
        startActivity(main_intent);
    }
    public void openActivitySyllabus() {
        Intent main_intent = new Intent(Student_Login_Activity.this,syl_act.class);
        startActivity(main_intent);
    }
    public void openActivityprevYear() {
        Intent main_intent = new Intent(Student_Login_Activity.this,lstppr_act.class);
        startActivity(main_intent);
    }
    public void openActivityeasySol() {
        Intent main_intent = new Intent(Student_Login_Activity.this,esysol_act.class);
        startActivity(main_intent);
    }
    public void LogOut() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this,AlreadyRegisteredLoginStudent.class));
    }
}
