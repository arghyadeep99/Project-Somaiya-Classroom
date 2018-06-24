package com.example.somaiya.somaiyaclassroom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class AlreadyRegisteredLoginProf extends AppCompatActivity implements View.OnClickListener{
    private Button SignIn;
    private EditText email;
    private EditText password;
    private Button forgot_pass;
    private TextView SignUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_registered_login_prof);
        email=(EditText) findViewById(R.id.email_id_tch);
        password=(EditText) findViewById(R.id.pass_tch);
        SignUp= (TextView) findViewById(R.id.signup);
        forgot_pass=(Button) findViewById(R.id.forgot_password);
        forgot_pass.setPaintFlags(forgot_pass.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        SignUp.setPaintFlags(SignUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        SignIn=(Button) findViewById(R.id.sign_in);
        progressDialog=new ProgressDialog(this);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(), Teacher_Login_Activity.class));
        }
        forgot_pass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                For_Pass_Act();
            }
        });
        SignUp.setOnClickListener(this);
        SignIn.setOnClickListener(this);
    }
    private void UserLogin(){
        String mail_id=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if(TextUtils.isEmpty(mail_id)){
            Toast.makeText(this, "Please enter an E-mail ID",Toast.LENGTH_LONG).show();
            //stops further execution
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Please enter a Password",Toast.LENGTH_LONG).show();
            //stops further execution
            return;
        }
        //If validation is fine, show progress dialogue.
        progressDialog.setMessage("Logging In....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(mail_id,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //start next activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), Teacher_Login_Activity.class));
                        }
                        else{
                            Toast.makeText(AlreadyRegisteredLoginProf.this, "Invalid Username or Password. Try Again.",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view==SignIn){
            UserLogin();
        }
        if(view==SignUp){
            finish();
            startActivity(new Intent(this,Main_Sign_In.class));
        }
    }
    private void For_Pass_Act(){
        Intent forget_intent= new Intent(this,Forgot_Password_Activity.class);
        startActivity(forget_intent);
    }
}
