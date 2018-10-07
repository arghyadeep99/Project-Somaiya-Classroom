package com.example.somaiya.somaiyaclassroom;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadFile extends AppCompatActivity {
    Button selectFile, upload;
    TextView notification;
    Uri pdfUri;
    ProgressDialog progressDialog;
    FirebaseStorage storage;
    FirebaseDatabase database;
    StorageReference pathToUpload;
    int buttonTracker;
    String name,url,location;//fileName,fileNameWithExtension;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);
        Bundle bundle = getIntent().getExtras();
        buttonTracker = bundle.getInt("buttonTracker",1);
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        selectFile = findViewById(R.id.select_1);
        upload = findViewById(R.id.upload_1);
        notification = findViewById(R.id.show_text);


        selectFile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(ContextCompat.checkSelfPermission(UploadFile.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                    selectPdf();
                else
                    ActivityCompat.requestPermissions(UploadFile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pdfUri!=null)
                    uploadFile(pdfUri);
                else
                    Toast.makeText(UploadFile.this, "Select a File", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void uploadFile(final Uri pdfUri){

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();
        //final String fileName = System.currentTimeMillis()+"";
        //if(name.indexOf('/')!=-1)
        //  fileName = name.substring(name.lastIndexOf("/")+1);
        //if(fileName.indexOf('.')!=-1)
        //  fileName = fileName.substring(0,fileName.lastIndexOf("."));
        final String fileName = encodeName(getFileName(pdfUri));
        final String fileNameWithExtension = encodeName(fileName)+getFileName(pdfUri).substring(getFileName(pdfUri).lastIndexOf('.'))+"";
        final StorageReference storageReference=storage.getReference();
        switch (buttonTracker){
            case 1:
                pathToUpload=storageReference.child("Syllabus/syllabus.pdf");
                location="Syllabus";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                pathToUpload=storageReference.child("Course Materials/Chap "+(buttonTracker-9)).child(fileNameWithExtension);
                location="Course Materials/Chap "+(buttonTracker-9);
                break;
            /*case 11:
                pathToUpload=storageReference.child("Course Materials/Chap 2").child(fileNameWithExtension);
                location="Course Materials/Chap 2";
                break;
            case 12:
                pathToUpload=storageReference.child("Course Materials/Chap 3").child(fileNameWithExtension);
                location="Course Materials/Chap 3";
                break;
            case 13:
                pathToUpload=storageReference.child("Course Materials/Chap 4").child(fileNameWithExtension);
                location="Course Materials/Chap 4";
                break;
            case 14:
                pathToUpload=storageReference.child("Course Materials/Chap 5").child(fileNameWithExtension);
                location="Course Materials/Chap 5";
                break;
            case 15:
                pathToUpload=storageReference.child("Course Materials/Chap 6").child(fileNameWithExtension);
                location="Course Materials/Chap 6";
                break;*/
            case 4:
                pathToUpload=storageReference.child("Easy Solutions").child(fileNameWithExtension);
                location="Easy Solutions";
                break;
            case 5:
                pathToUpload=storageReference.child("Previous Years UT Papers").child(fileNameWithExtension);
                location="Previous Years UT Papers";
                break;
            case 6:
                pathToUpload=storageReference.child("Previous Years ESE Papers").child(fileNameWithExtension);
                location="Previous Years ESE Papers";
                break;

        }
        pathToUpload.putFile(pdfUri)
                .addOnSuccessListener(this ,new OnSuccessListener<UploadTask.TaskSnapshot>(){
                    @Override
                    public  void onSuccess(final UploadTask.TaskSnapshot taskSnapshot){

                        //String url = taskSnapshot.getStorage().getDownloadUrl().toString();
                        final DatabaseReference reference;
                        reference=database.getReference().child(location);
                        if(buttonTracker==1)
                        {
                            reference.setValue(null);
                        }
                        pathToUpload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri temp = uri;
                                url = temp.toString();
                                reference.child(fileName).setValue(url);
                            }
                        });
                        reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                        /*
                                if (task.isSuccessful())
                                    Toast.makeText(UploadFile.this, "File successfully uploaded!", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(UploadFile.this, "File not successfully uploaded!", Toast.LENGTH_SHORT).show();
                        */
                            }
                        });



                    }
                }).addOnFailureListener(new OnFailureListener(){
            @Override
            public  void onFailure(@NonNull Exception e){

                Toast.makeText(UploadFile.this, "File not uploaded. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int currentProgress= (int)( 100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
                if(currentProgress==100)
                {
                    progressDialog.cancel();
                    Toast.makeText(UploadFile.this, "File successfully uploaded!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if (requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            selectPdf();
        }
        else
            Toast.makeText(UploadFile.this, "Please provide required permission(s).", Toast.LENGTH_SHORT).show();
    }

    private void selectPdf(){

        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==86 && resultCode==RESULT_OK && data!=null){
            pdfUri = data.getData();
            notification.setText("A file is selected: "+ data.getData().getLastPathSegment());
            name = data.getData().getLastPathSegment();
        }
        else{
            Toast.makeText(UploadFile.this, "Please select a file", Toast.LENGTH_SHORT).show();
        }
    }
    public String encodeName(String name)
    {
        String num = "0x";
        int length = name.length();
        for(int i=0; i<length;i++)
        {
            num += Integer.toHexString(name.codePointAt(i));
        }
        return num;
    }
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}