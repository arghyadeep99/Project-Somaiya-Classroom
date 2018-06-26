package com.example.somaiya.somaiyaclassroom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main_Sign_In_Student extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private SignInButton SignIn;
    private GoogleApiClient gac;
    private static final int REQ_CODE=9001;
    private Switch isEnlarged;
    public static Boolean isZoom;
    private float zoomFactor = 1.25f;
    Magnify mag = new Magnify();
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__sign__in__student);
        SignIn = (SignInButton) findViewById(R.id.signin);
        GoogleSignInOptions googleSignInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        gac= new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();
        SignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SignIn();
            }
        });
        isEnlarged = findViewById(R.id.switch1);
        isEnlarged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mag.enlarge(isEnlarged.isChecked(),findViewById(android.R.id.content),zoomFactor);
            }
        });
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            startActivity(new Intent(this,Student_Login_Activity.class));
        }
    }
    private void SignIn(){
        Intent intent=Auth.GoogleSignInApi.getSignInIntent(gac);
        startActivityForResult(intent, REQ_CODE);

    }
    private void handleResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);
            startActivity(new Intent(this,Student_Login_Activity.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQ_CODE){
            GoogleSignInResult result =Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }
}