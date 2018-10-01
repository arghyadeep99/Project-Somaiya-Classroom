package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class course_act extends AppCompatActivity {
    String[] listviewFontChange_Value;
    ListView listView;
    List<String> convertString;
    int buttonTracker = 10;
    ArrayAdapter<String> arrayadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_act);
        Magnify mag = new Magnify();
        float zoomFactor = 1.25f;
        if(Magnify.getInstance().getData())
            mag.enlarge(true,findViewById(android.R.id.content),zoomFactor);
        listView =  findViewById(R.id.listview);
        listviewFontChange_Value = this.getResources().getStringArray(R.array.list);
        convertString = new ArrayList<String>(Arrays.asList(listviewFontChange_Value));

        arrayadapter = new ArrayAdapter<String>(course_act.this, android.R.layout.simple_list_item_1, convertString){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                View view = super.getView(position, convertView, parent);

                TextView textview = (TextView) view.findViewById(android.R.id.text1);
                textview.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
                Typeface typeface = ResourcesCompat.getFont(getApplicationContext(),R.font.roboto_slab);
                textview.setTypeface(typeface);
                return view;
            }
        };


        listView.setAdapter(arrayadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                buttonTracker=i+10;
                Log.e("buttonTracker",Integer.toString(buttonTracker));
                Intent upload = new Intent(course_act.this, DownloadFile.class);
                upload.putExtra("buttonTracker",buttonTracker);
                startActivity(upload);
            }
        });
    }
    /*public void open_download(View view) {
        Intent upload = new Intent(course_act.this, DownloadFile.class);
        upload.putExtra("buttonTracker",2);
        startActivity(upload);
    }*/
}
