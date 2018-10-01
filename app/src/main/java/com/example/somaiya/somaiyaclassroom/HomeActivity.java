package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {

    private CardView tch_button;
    private CardView std_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tch_button = (CardView) findViewById(R.id.tch_btn);
        std_button = (CardView) findViewById(R.id.stu_btn);


        std_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Globals.stu) {
                    //Globals.tea = false;
                    startActivity(new Intent(HomeActivity.this, Main_Sign_In_Student.class));
                }
                else
                    Toast.makeText(HomeActivity.this, "You are logged in as Professor.", Toast.LENGTH_SHORT).show();

            }
        });
        tch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Globals.tea) {
                    //Globals.stu = false;
                    startActivity(new Intent(HomeActivity.this, Main_Sign_In.class));
                }
                else
                    Toast.makeText(HomeActivity.this, "You are logged in as Student.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

