package com.example.somaiya.somaiyaclassroom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class Main_Sign_In extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private SignInButton SignIn;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int REQ_CODE=9001;
    private static final String TAG = "GoogleActivity";
    private FirebaseAuth mAuth;
    private static final String password="Professor@123";
    private EditText prof_pass;
    private EditText tname;
    private EditText temail;
    private String name;
    private String email;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__sign__in);
        SignIn = (SignInButton) findViewById(R.id.SignIn);
        prof_pass=(EditText) findViewById(R.id.password_prof);

        //tname = (EditText) findViewById(R.id.teacher_name);
        //temail = (EditText) findViewById(R.id.teacher_email);

        Bundle bundle = getIntent().getExtras();
        //final String _name = bundle.getString("NAME");
        //final String _email = bundle.getString("EMAIL");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Professors");

        SignIn.setEnabled(false);
        GoogleSignInOptions googleSignInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        SignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FirebaseUser user = mAuth.getCurrentUser();
                SignIn();
                //name = tname.getText().toString().trim();
                //email = temail.getText().toString().trim();
   //       String name = user.getDisplayName();
  //         String email = user.getEmail();
    //            String photoUrl = user.getPhotoUrl().toString();
              
            }
        });
        mAuth = FirebaseAuth.getInstance();
        prof_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(password)){
                    SignIn.setEnabled(true);
                }
                else{
                    SignIn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Globals.tea = true;
            Globals.stu = false;
        }
            openProfActivity(currentUser);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void SignIn() {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, REQ_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == REQ_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                Globals.stu = false;
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Globals.stu = true;
                Globals.tea = true;
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(Main_Sign_In.this,"Google Sign In Failed",Toast.LENGTH_SHORT).show();
                Globals.stu = true;
                Globals.tea = true;
                // [START_EXCLUDE]
                openProfActivity(null);
                // [END_EXCLUDE]
            }
        }
    }
    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        // [START_EXCLUDE silent]
        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(Main_Sign_In.this,"Logged In Successfully",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            openProfActivity(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Snackbar.make(findViewById(R.id.teacher_login), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            Toast.makeText(Main_Sign_In.this,"Log In Failed",Toast.LENGTH_SHORT).show();
                            openProfActivity(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    private void openProfActivity(FirebaseUser user) {
        // hideProgressDialog();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            String photoUrl = user.getPhotoUrl().toString();

            HashMap<String, String > dataMap = new HashMap<String, String>();
            dataMap.put("Name", name);
            dataMap.put("Email", email);
           
            mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                        Toast.makeText(Main_Sign_In.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Main_Sign_In.this, "Registration unsuccessful. Please try again.", Toast.LENGTH_SHORT).show();
                }
            });

            if(Globals.tea) {
                startActivity(new Intent(this, Teacher_Login_Activity.class)
                        .putExtra("NAME", name)
                        .putExtra("EMAIL", email)
                        .putExtra("PhotoURL", photoUrl));
            }
                finish();
        }
    }
    public void showProgressDialog(){
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Logging in...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }
}