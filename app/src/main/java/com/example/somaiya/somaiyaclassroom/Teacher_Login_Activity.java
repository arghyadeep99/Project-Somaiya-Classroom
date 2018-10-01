package com.example.somaiya.somaiyaclassroom;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Intent.ACTION_EDIT;
import static com.example.somaiya.somaiyaclassroom.R.id.profile_image_tch;


public class Teacher_Login_Activity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private CardView mCourse;
    private CardView mSyllabus;
    private CardView mPrevYears;
    private CardView mEasySol;
    private CardView Calendar;
    private CardView faqs;
    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoggle;
    private Toolbar mToolbar;
    public TextView display_username;
    public TextView display_mail;
    public ImageView display_pic;
    private DatabaseReference mDatabase;
    String list_emails [] = {};
    StringBuilder emails_string = new StringBuilder();
    ArrayList<String> emails = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__login);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("NAME");


        String email = bundle.getString("EMAIL");
        NavigationView NavigationView = (NavigationView) findViewById(R.id.nav_view_teacher);
        View headertch=NavigationView.getHeaderView(0);

        TextView username=(TextView)headertch.findViewById(R.id.username_tch);
        username.setText(name);
        TextView emailid=(TextView)headertch.findViewById(R.id.email_tch);
        emailid.setText(email);

        String photoURL = bundle.getString("PhotoURL");

        CircleImageView image=headertch.findViewById(profile_image_tch);

        Glide.with(Teacher_Login_Activity.this).load(photoURL).into(image);




        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id))
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().requestProfile().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        mAuth = FirebaseAuth.getInstance();
        mCourse = (CardView) findViewById(R.id.coursematerial);
        mSyllabus = (CardView) findViewById(R.id.syllabus_tch);
        mPrevYears = (CardView) findViewById(R.id.prevYears_tch);
        mEasySol = (CardView) findViewById(R.id.easySol_tch);
        faqs = (CardView) findViewById(R.id.faqs);
        mToolbar = (Toolbar) findViewById(R.id.nav_action_tch);
        Calendar = (CardView) findViewById(R.id.Calendar);
        Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                String aEmailList[] = list_emails;
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Notification: Somaiya Classroom");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Please, write the note over here...");
                startActivity(emailIntent);
                */
//             emails_string = list_emails[0];

            for(String temp : emails){
                emails_string.append(temp);
                emails_string.append(",");
            }
            String e = emails_string.toString().trim();
         //   e = e.substring(0, e.length()-1);

             Intent intent = new Intent(ACTION_EDIT)
                     .setType("vnd.android.cursor.item/event")
                     .putExtra(CalendarContract.Events.TITLE, "Enter Title...")
                     .putExtra(CalendarContract.Events.DESCRIPTION, "Enter Description...")
                     .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                     .putExtra(Intent.EXTRA_EMAIL, e);
                startActivity(intent);
                Log.e("Email List: ", emails.toString());
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Students");

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                collectEmail((Map<String,Object>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Like I care about any errors
            }
        });


        setSupportActionBar(mToolbar);
        /**LayoutInflater inflater= LayoutInflater.from(getApplicationContext());
        View header=inflater.inflate(R.layout.viewprofile,mdrawerlayout,true);
        display_username=(TextView) header.findViewById(R.id.username);
        display_mail=(TextView) header.findViewById(R.id.email);
**/
        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mtoggle = new ActionBarDrawerToggle(this, mdrawerlayout, R.string.Open, R.string.Close);
        mdrawerlayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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

        faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQs(v);
            }
        });
      /*  Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar(v);
            }
        }); */
    }

    private void collectEmail(Map<String,Object> users) {



        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){
            Map singleUser = (Map) entry.getValue();
            emails.add((String) singleUser.get("Email"));
        }

        /*int i = 0;
        for(String temp : emails){
            list_emails[i] = temp;
            i++;
        }*/

        list_emails = emails.toArray(new String[0]);
        Log.e("Emails: ", emails.toString());
      //  Log.e("Emails_strings: ", String.valueOf(list_emails));
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        if (mtoggle.onOptionsItemSelected(item)) {
            return true;

        }
        return super.onOptionsItemSelected(item);

    }

    public void FAQs(View v) {
        startActivity(new Intent(this, faq_tch.class));
    }


  /* public void openCalendar(View v) {

        Intent open_calendar = new Intent(Teacher_Login_Activity.this, send_email_teacher.class);
        startActivity(open_calendar);
    }
    */

    public void openActivitycourseMaterial() {
        Intent main_intent = new Intent(Teacher_Login_Activity.this, courseMaterial.class);
        this.startActivity(main_intent);
    }

    public void openActivitySyllabus() {
        Intent main_intent = new Intent(Teacher_Login_Activity.this, Syllabus.class);
        startActivity(main_intent);
    }

    public void openActivityprevYear() {
        Intent main_intent = new Intent(Teacher_Login_Activity.this, PreviousPapers.class);
        startActivity(main_intent);
    }

    public void openActivityeasySol() {
        Intent main_intent = new Intent(Teacher_Login_Activity.this, Easysolution.class);
        startActivity(main_intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public void openHome(FirebaseUser user) {
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

    private void logout() {


        finish();
        mAuth.signOut();
        Globals.tea = true;
        Globals.stu = true;
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        openHome(null);

                    }
                });

    }

    public void about(MenuItem item) {
        startActivity(new Intent(this, about.class));
    }
    @Override
    public void onBackPressed() {
        Globals.tea = true;
        Globals.stu = false;
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Are you sure you want to go back to Home Screen?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Globals.stu = false;
                //Globals.tea = true;
                finish();

            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}

