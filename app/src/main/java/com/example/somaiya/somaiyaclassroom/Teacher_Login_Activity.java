package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Teacher_Login_Activity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private Button mCourse;
    private Button mSyllabus;
    private Button mPrevYears;
    private Button mEasySol;
    private Button add_event;
    private Button Logout;
    private GoogleApiClient googleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__login);
        mCourse = (Button) findViewById(R.id.courseContent);
        mSyllabus = (Button) findViewById(R.id.syllabus);
        mPrevYears = (Button) findViewById(R.id.prevYears);
        mEasySol = (Button) findViewById(R.id.easySol);
        Logout = (Button) findViewById(R.id.logout);
        GoogleSignInOptions googleSignInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id))
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        googleApiClient= new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();

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
        add_event = (Button) findViewById(R.id.textView1_stu);
        add_event.setOnClickListener(new View.OnClickListener() {
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
        Intent i = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(i);
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
    public void openActivityeasySol() {
        Intent main_intent = new Intent(Teacher_Login_Activity.this,Easysolution.class);
        startActivity(main_intent);
    }
    public void LogOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                MainSignIn();
            }
        });
    }
    public void MainSignIn(){
        finish();
        startActivity(new Intent(this, Main_Sign_In.class));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
