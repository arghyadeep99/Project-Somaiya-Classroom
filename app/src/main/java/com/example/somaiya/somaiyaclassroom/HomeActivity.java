package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        }
        public void student(View v){
            startActivity(new Intent(this,Main_Sign_In_Student.class));
        }
        public void teacher(View v){

            startActivity(new Intent(this, Main_Sign_In.class));
        }
}

