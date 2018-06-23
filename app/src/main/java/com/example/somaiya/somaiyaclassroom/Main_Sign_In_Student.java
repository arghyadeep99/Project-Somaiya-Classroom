package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main_Sign_In_Student extends AppCompatActivity {
    private Button login;
    private EditText username;
    private EditText password;
    private TextView counter_info;
    private Button forgot_pass;
    private int count=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__sign__in__student);

        username=(EditText) findViewById(R.id.email_id_tch);
        password=(EditText) findViewById(R.id.pass_tch);
        counter_info= (TextView) findViewById(R.id.count_inv);
        counter_info.setText("");
        login= (Button) findViewById(R.id.log_in);
        forgot_pass=(Button) findViewById(R.id.forgot_password);
        forgot_pass.setPaintFlags(forgot_pass.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(),password.getText().toString());
            }
        });
        forgot_pass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                For_Pass_Act();
            }
        });

    }

    private void validate(String user, String pass) {
        if((user.equals("admin")) && (pass.equals("admin")))
        {
            Intent tch_intent= new Intent(Main_Sign_In_Student.this,Student_Login_Activity.class);
            startActivity(tch_intent);
        }
        else {
            count--;
            counter_info.setText("Wrong E-mail or Password!");
            if(count==0) {
                login.setEnabled(false);
                counter_info.setText("You have entered wrong email or password 3 consecutive times. Restart the app and try again.");
            }
        }
    }
    private void For_Pass_Act(){
        Intent forget_intent= new Intent(Main_Sign_In_Student.this,Forgot_Password_Activity.class);
        startActivity(forget_intent);
    }
}
