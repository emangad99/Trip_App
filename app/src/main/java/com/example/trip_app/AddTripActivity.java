package com.example.trip_app;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddTripActivity extends AppCompatActivity {
ImageView imagcalender;
TextView txtcalender;
ImageView imagalarm;
TextView txtalarm;
int t1Hour,t1Minute;

private Spinner spinnertxt1;
private Spinner spinnertxt2;

ImageButton addtrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        setContentView(R.layout.activity_add_trip);
        getSupportActionBar().hide();

        spinnertxt1=findViewById(R.id.spinner_txt1);
        spinnertxt2=findViewById(R.id.spinner_txt2);

        imagcalender=findViewById(R.id.imag_calender);
        txtcalender=findViewById(R.id.txt_calender);
        imagalarm=findViewById(R.id.imag_alarm);
        txtalarm=findViewById(R.id.txt_alarm);
        addtrip=findViewById(R.id.add_trip);

        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);


        imagcalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddTripActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                         month=month+1;
                         String date=day + "/"+month+"/"+year;
                         txtcalender.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });

        imagalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AddTripActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t1Minute=minute;
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0,0,0,t1Hour,t1Minute);
                                txtalarm.setText(DateFormat.format("hh:mm aa",calendar));

                            }
                        },12,0,false
                );
                timePickerDialog.updateTime(t1Hour,t1Minute);
                timePickerDialog.show();
            }
        });

        String[] txtrepeat = getResources().getStringArray(R.array.Repeat);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,txtrepeat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertxt1.setAdapter(adapter);

        String[] txttrip = getResources().getStringArray(R.array.trip);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,txttrip);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertxt2.setAdapter(adapter2);

        addtrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addintent = new Intent (AddTripActivity.this,List_view.class);

                startActivity(addintent);
            }
        });
    }
}