package com.example.somaiya.somaiyaclassroom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class Main_Sign_In extends AppCompatActivity implements View.OnClickListener{
    private Button SignUp;
    private EditText email;
    private EditText password;
    private TextView SignIn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "Main_Sign_In";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__sign__in);
        progressDialog= new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(), Teacher_Login_Activity.class));
        }
        email=(EditText) findViewById(R.id.email_id_tch);
        password=(EditText) findViewById(R.id.pass_tch);
        SignUp= (Button) findViewById(R.id.register);
        SignIn=(TextView)findViewById(R.id.signin);
        SignIn.setPaintFlags(SignIn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        SignUp.setOnClickListener(this);
        SignIn.setOnClickListener(this);
    }
    private void registerUser(){
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
        progressDialog.setMessage("Registering User....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(mail_id,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    //User registered successfully and logged in
                        finish();
                        startActivity(new Intent(getApplicationContext(), Teacher_Login_Activity.class));
                }
                else if (!task.isSuccessful()) {
                    try {
                        throw task.getException();
                    }
                    // if user enters wrong email.
                    catch (FirebaseAuthWeakPasswordException weakPassword) {
                        //Log.d(TAG, "onComplete: weak_password");

                        Toast.makeText(Main_Sign_In.this, "Weak Password. Enter a strong Password.", Toast.LENGTH_LONG).show();
                    }
                    // if user enters wrong password.
                    catch (FirebaseAuthInvalidCredentialsException malformedEmail) {
                        //Log.d(TAG, "onComplete: malformed_email");
                        Toast.makeText(Main_Sign_In.this, "Invalid E-mail. Please try again.", Toast.LENGTH_LONG).show();
                    } catch (FirebaseAuthUserCollisionException existEmail) {
                        //Log.d(TAG, "onComplete: exist_email");

                        Toast.makeText(Main_Sign_In.this, "Email ID already exists. Please try signing in.", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        //Log.d(TAG, "onComplete: " + e.getMessage());
                        Toast.makeText(Main_Sign_In.this, "Registration Unsuccessful. Please try again.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    @Override
    public void onClick(View v){
        if(v==SignUp)
        {
            registerUser();
        }
        if(v==SignIn)
        {
            Intent tch_intent= new Intent(Main_Sign_In.this,AlreadyRegisteredLoginProf.class);
            startActivity(tch_intent);
        }
    }

    /**private void validate(String user, String pass) {
        if((user.equals("admin")) && (pass.equals("admin")))
        {
            Intent tch_intent= new Intent(Main_Sign_In.this,Teacher_Login_Activity.class);
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
    }**/
}
