package com.example.somaiya.somaiyaclassroom;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;



import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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



public class Student_Login_Activity extends AppCompatActivity {


    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoggle;
    private Toolbar mToolbar;
    TextView HyperLink;
    Spanned Text;
    private Button Logout;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    NavigationView navigation;
    Adapter AdapterView;
    DrawerLayout.DrawerListener mDrawerListMDrawerL;
    public FirebaseUser user;
    GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__login);
        Logout = (Button) findViewById(R.id.nav_logout);
        GoogleSignInOptions googleSignInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        mAuth = FirebaseAuth.getInstance();

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);


        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mtoggle = new ActionBarDrawerToggle(this, mdrawerlayout, R.string.Open, R.string.Close);
        mdrawerlayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (mtoggle.onOptionsItemSelected(item)) {
            return true;

        }
        return super.onOptionsItemSelected(item);

    }


    public void syllabus(View v) {
        Intent i = new Intent(this, syl_act.class);
        startActivity(i);
    }

    public void CourseMaterials(View v) {
        Intent i = new Intent(Student_Login_Activity.this, course_act.class);
        startActivity(i);
    }

    public void EasySolutions(View v) {
        Intent i = new Intent(this, esysol_act.class);
        startActivity(i);
    }

    public void PreviousPapers(View v) {
        Intent i = new Intent(this, lstppr_act.class);
        startActivity(i);
    }

    public void ViewEvents(View v) {
        Uri uri = Uri.parse("https://www.google.com/calendar");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }
    public void FAQs(View v) {
        startActivity(new Intent(this,FAQ.class));
    }

    public void openMainSignInStudent(FirebaseUser user){
        finish();
        startActivity(new Intent(this, HomeActivity.class));
    }


    public void SIGNOUT(MenuItem item) {
        logout();
    }

    public void notifications(MenuItem item) {
        Uri uri = Uri.parse("https://www.google.com/calendar");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }

    public void chat(MenuItem item) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        String aEmailList[] = {"a.chachra@somaiya.edu"};
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Query in FCP");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Dear Ma'am,\n\t\tI have a doubt.");
        startActivity(emailIntent);
    }

    private void logout() {
        finish();
        mAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        openMainSignInStudent(null);
                    }
                });
    }
    public void about(MenuItem item){
        startActivity(new Intent(this, about.class));
    }

    /**@Override public void onBackPressed() {

    backpress = (backpress + 1);
    Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();

    if (backpress>1) {
    finish();
    }
    }**/
}
