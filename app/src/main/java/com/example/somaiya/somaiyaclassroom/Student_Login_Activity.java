package com.example.somaiya.somaiyaclassroom;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mahfa.dnswitch.DayNightSwitch;
import com.mahfa.dnswitch.DayNightSwitchListener;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.somaiya.somaiyaclassroom.R.id.profile_image;

//import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.annotation.GlideModule;
//import com.bumptech.glide.module.AppGlideModule;

public class Student_Login_Activity extends AppCompatActivity {

    MyAdapter.ViewHolder holder;
    public View background_view;
    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoggle;
    private Toolbar mToolbar;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    DayNightSwitch dayNightSwitch;
    DrawerLayout.DrawerListener mDrawerListMDrawerL;
    public FirebaseUser user;
    GoogleApiClient mGoogleApiClient;

    private Bitmap bitmap;
    String photoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__login);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("NAME");
        String email = bundle.getString("EMAIL");
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view_student);
        View header=mNavigationView.getHeaderView(0);

        TextView username=(TextView)header.findViewById(R.id.username);
        username.setText(name);
        TextView emailid=(TextView)header.findViewById(R.id.email);
        emailid.setText(email);

       String photoURL = bundle.getString("PhotoURL");

        CircleImageView image=header.findViewById(profile_image);

        Glide.with(Student_Login_Activity.this).load(photoURL).into(image);

            GoogleSignInOptions googleSignInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        mAuth = FirebaseAuth.getInstance();
        mToolbar = (Toolbar) findViewById(R.id.nav_action);


        mToolbar.setTitle("Welcome to Student Portal");
        setSupportActionBar(mToolbar);
        /**LayoutInflater night= LayoutInflater.from(getApplicationContext());
         Glide.with(this).load(image_url).apply(options).into(imageView);
         View toggle=night.inflate(R.layout.night_toggle,mdrawerlayout,true);**/
        if(dayNightSwitch!=null) {
            dayNightSwitch = (DayNightSwitch) findViewById(R.id.night);
            //background_view= findViewById(R.id.background_view);

            dayNightSwitch.setDuration(450);
            dayNightSwitch.setListener(new DayNightSwitchListener() {
                @Override
                public void onSwitch(boolean isNight) {
                    if (isNight) {
                        Toast.makeText(Student_Login_Activity.this, "Night Mode On", Toast.LENGTH_SHORT).show();
                        //background_view.setAlpha(1f);
                    } else {
                        Toast.makeText(Student_Login_Activity.this, "Night Mode Off", Toast.LENGTH_SHORT).show();
                        //background_view.setAlpha(0f);
                    }
                }
            });
        }

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

     //   Intent i = new Intent(Student_Login_Activity.this, StudentCalendar.class);
    //    startActivity(i);
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
    /**public void report_bug(MenuItem item){
        // save logcat in file
        File outputFile = new File(Environment.getExternalStorageDirectory(),
                "logcat.txt");
        try {
            Runtime.getRuntime().exec(
                    "logcat -f " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //send file using email
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // Set type to "email"
        emailIntent.setType("vnd.android.cursor.dir/email");
        String to[] = {"developer.somaiyaclassroom@gmail.com"};
        emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
        // the attachment
        emailIntent .putExtra(Intent.EXTRA_STREAM, outputFile.getAbsolutePath());
        // the mail subject
        emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Bug Report");
        startActivity(Intent.createChooser(emailIntent , "Send email..."));
    }**/

    private void logout() {


        finish();
        mAuth.signOut();
        Globals.stu = true;
        Globals.tea = true;
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

    @Override
    public void onBackPressed() {
        Globals.tea = false;
        Globals.stu = true;
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Are you sure you want to go back to Home Screen?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Globals.stu = true;
                //Globals.tea = false;
                finish();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}
