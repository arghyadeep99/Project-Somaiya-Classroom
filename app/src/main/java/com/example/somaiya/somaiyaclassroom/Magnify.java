package com.example.somaiya.somaiyaclassroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
public class Magnify{
    public static boolean data;
    Magnify() {}
    public boolean getData() {return data;}
    public void setData(boolean data) {this.data = data;}

    private static final Magnify holder = new Magnify();
    public static Magnify getInstance() {return holder;}
    public void enlarge(boolean x, View v, float zoomFactor){
        //float zoomFactor = 1.25f;
        //View v=findViewById(android.R.id.content);
        if(!x) {
            v.setScaleX(1);
            v.setScaleY(1);
        }
        else {
            v.setScaleX(zoomFactor);
            v.setScaleY(zoomFactor);
        }
    }
}