package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Student_Login_Activity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private Button mCourse;
    private Button mSyllabus;
    private Button mPrevYears;
    private Button mEasySol;
    private Button view_events;
    private Button Logout;
    private static int backpress=1;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__login);
        mCourse = (Button) findViewById(R.id.courseContent);
        mSyllabus = (Button) findViewById(R.id.syllabus);
        mPrevYears = (Button) findViewById(R.id.prevYears);
        mEasySol = (Button) findViewById(R.id.easySol);
        Logout = (Button) findViewById(R.id.logout);
        GoogleSignInOptions googleSignInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        mAuth = FirebaseAuth.getInstance();

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
        //Firebase SignOut
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        openMainSignInStudent(null);
                    }
                });
    }
    public void openMainSignInStudent(FirebaseUser user){
        finish();
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onBackPressed() {

        backpress = (backpress + 1);
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();

        if (backpress>1) {
            finish();
}
    }
}
