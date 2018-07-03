package com.example.somaiya.somaiyaclassroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DownloadFile extends AppCompatActivity {
    RecyclerView recyclerView;
    String fileName,url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_view);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                fileName = dataSnapshot.getKey();
                url = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(DownloadFile.this));
        MyAdapter myAdapter = new MyAdapter(DownloadFile.this,recyclerView,new ArrayList<String>(),new ArrayList<String>());
        recyclerView.setAdapter(myAdapter);
        ((MyAdapter)recyclerView.getAdapter()).update(fileName,url);
    }
    public String decodeName(String num)
    {
        String ans = "";
        String s;
        num = num.substring(2,num.length());
        int length = num.length();
        for(int i=0; i<length;)
        {
            s = num.substring(i,i+=2);
            ans += (char)Integer.parseInt(s,16);
        }
        return ans;
    }
}
