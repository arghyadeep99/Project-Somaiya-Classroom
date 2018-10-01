package com.example.somaiya.somaiyaclassroom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class calendar extends AppCompatActivity {

    private static final String TAG = "calendar";
    CompactCalendarView compactCalendar;
    TextView textView;
    EditText txtDate, txtTime;


    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        textView = (TextView) findViewById(R.id.current_month);
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);

        compactCalendar.setUseThreeLetterAbbreviation(true);
        compactCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        compactCalendar.getFirstDayOfCurrentMonth();
        final SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());




        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

/*
        Calendar cal  = Calendar.getInstance();
        cal.set(Globals.year, Globals.month, Globals.day, Globals.hour, Globals.min);
        final long d = cal.getTimeInMillis();
*/      final long d = Globals.mi;
        // Adding events
        final Event event1 = new Event(Color.GREEN, d, Globals.note);
        compactCalendar.addEvent(event1);

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                Context context = getApplicationContext();
                List<Event> events = compactCalendar.getEvents(dateClicked);
                Log.e(TAG, "Day was clicked: " + dateClicked + " with events " + events);
                Log.e(TAG, "Milli date: " + new Date(d).toString());
                Date date1 = new Date(d);
            if(dateClicked.getDay()== date1.getDay() && dateClicked.getMonth()== date1.getMonth() && dateClicked.getYear() == date1.getYear())
                Toast.makeText(context, "Events for today: " + event1.getData(), Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "No events planned.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                textView.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                Log.e(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });


    }
}
