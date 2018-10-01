package com.example.somaiya.somaiyaclassroom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
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


public class Main_Sign_In_Student extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private SignInButton SignIn;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int REQ_CODE=9001;
    private static final String TAG = "GoogleActivity";
    private FirebaseAuth mAuthStu;
    private Switch isEnlarged;
    public static Boolean isZoom;
    private float zoomFactor = 1.25f;
    Magnify mag = new Magnify();
    private EditText sname;
    private EditText semail;
    private String name;
    private String email;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__sign__in__student);
        SignIn = (SignInButton) findViewById(R.id.signin);
        GoogleSignInOptions googleSignInOptions= new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);



        Bundle bundle = getIntent().getExtras();
        //final String _name = bundle.getString("NAME");
        //final String _email = bundle.getString("EMAIL");

        //sname = (EditText) findViewById(R.id.student_name);
        //semail = (EditText) findViewById(R.id.student_email);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Students");

        SignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SignIn();

            }
        });
        mAuthStu = FirebaseAuth.getInstance();

        /**isEnlarged = findViewById(R.id.switch1);
        isEnlarged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mag.enlarge(isEnlarged.isChecked(),findViewById(android.R.id.content),zoomFactor);
            }
        });**/
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuthStu.getCurrentUser();
        if (currentUser != null && mDatabase.toString().equals("Students")) {
            Globals.tea = false;
            Globals.stu = true;
        }
            openStudActivity(currentUser);
    }

    private void SignIn(){
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
                Globals.tea = false;
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Globals.stu = true;
                Globals.tea = true;
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(Main_Sign_In_Student.this,"Google Sign In Failed",Toast.LENGTH_SHORT).show();
                Globals.stu = true;
                Globals.tea = true;
                // [START_EXCLUDE]
                openStudActivity(null);
                // [END_EXCLUDE]
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuthStu.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(Main_Sign_In_Student.this,"Logged In Successfully",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuthStu.getCurrentUser();
                            openStudActivity(user);
                         //updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(Main_Sign_In_Student.this,"Log In Failed",Toast.LENGTH_SHORT).show();
                            //Snackbar.make(findViewById(R.id.student_login), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            openStudActivity(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
private void updateUI(FirebaseUser user){
    GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
    if (acct != null) {
        String personName = acct.getDisplayName();
        String personGivenName = acct.getGivenName();
        String personFamilyName = acct.getFamilyName();
        String personEmail = acct.getEmail();
        String personId = acct.getId();
        Uri personPhoto = acct.getPhotoUrl();
        }
    }
    private void openStudActivity(FirebaseUser user) {
        // hideProgressDialog();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            String photoUrl = user.getPhotoUrl().toString();

            HashMap<String, String> dataMap = new HashMap<String, String>();
            dataMap.put("Name", name);
            dataMap.put("Email", email);

            
            mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                        Toast.makeText(Main_Sign_In_Student.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Main_Sign_In_Student.this, "Registration unsuccessful. Please try again.", Toast.LENGTH_SHORT).show();
                }
            });

            if (Globals.stu) {
                startActivity(new Intent(this, Student_Login_Activity.class)
                        .putExtra("NAME", name)
                        .putExtra("EMAIL", email)
                        .putExtra("PhotoURL", photoUrl));

                finish();
            }
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
