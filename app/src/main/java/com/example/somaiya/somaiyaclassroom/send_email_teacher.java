package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import static com.paypal.android.sdk.cu.i;

public class send_email_teacher extends AppCompatActivity {

    private Button send_mail;
    private DatabaseReference mDatabase;
    String list_emails [] = {};
    ArrayList<String> emails = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email_teacher);

        send_mail = (Button) findViewById(R.id.send_mail);

        send_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                String aEmailList[] = list_emails;
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Notifications!!!");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Please, write the note over here...");
                startActivity(emailIntent);
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
        Log.e("Emails_strings: ", String.valueOf(list_emails));
    }


}
