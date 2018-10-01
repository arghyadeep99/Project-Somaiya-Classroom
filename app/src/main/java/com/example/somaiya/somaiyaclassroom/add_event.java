package com.example.somaiya.somaiyaclassroom;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class add_event extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime, txtText;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(add_event.this);
        btnTimePicker.setOnClickListener(add_event.this);


        txtText=(EditText)findViewById(R.id.in_text);
        txtText.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View view){
                Globals.note = txtText.getText().toString();
            }
        });
  /*      String date = txtDate.getText().toString();
        String time = txtTime.getText().toString();
        String [] l_date = date.split("-", 3);
        String [] l_time = time.split(":", 2);

        Globals.day = Integer.parseInt(l_date[0]);
        Globals.month = Integer.parseInt(l_date[1]);
        Globals.year = Integer.parseInt(l_date[2]);
        Globals.hour = Integer.parseInt(l_time[0]);
        Globals.min = Integer.parseInt(l_time[1]);

*/

        Button add = (Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {   Intent i = new Intent(add_event.this,calendar.class);
                startActivity(i);

            }   });



    }



    @Override
    public void onClick(View v) {
        final Calendar calendar = Calendar.getInstance();
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            calendar.set(year, monthOfYear, dayOfMonth);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            calendar.set(Calendar.MINUTE, minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        Globals.mi = calendar.getTimeInMillis();


    }


}
