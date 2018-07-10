package com.example.somaiya.somaiyaclassroom;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class calendar extends AppCompatActivity {

    CompactCalendarView compactCalendar;
    //private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());



    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //final ActionBar actionbar = getSupportActionBar();
        //assert actionbar != null;
        //actionbar.setDisplayHomeAsUpEnabled(false);
        //actionbar.setTitle(null);

        textView = (TextView) findViewById(R.id.current_month);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        compactCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        compactCalendar.getFirstDayOfCurrentMonth();
        final SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());

        // Setting an event for testing

        //Event  ev1 = new Event(Color.RED, 1531246438450L, "App Submission!");
        //compactCalendar.addEvent(ev1);

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                if(dateClicked.toString().compareTo("Tue Jul 23:45:00 IST 2018") == 0)
                    Toast.makeText(context, "App Submission!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, "No events Planned", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
              //  actionbar.setTitle((dateFormatMonth.format(firstDayOfNewMonth)));
                textView.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });


    }
}
