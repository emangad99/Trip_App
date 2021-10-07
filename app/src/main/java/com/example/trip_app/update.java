package com.example.trip_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class update extends AppCompatActivity {

    ImageView imagcalender;
    TextView txtcalender;
    ImageView imagalarm;
    TextView txtalarm;
    int t1Hour,t1Minute;

    EditText textTripName;
    Button btnupdate;
    EditText startPoint;
    EditText endPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        setContentView(R.layout.activity_update);

        getSupportActionBar().hide();

        initComponnent();

        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        imagcalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        update.this, new DatePickerDialog.OnDateSetListener() {
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
                        update.this,
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

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textTripName.getText().toString();
                String start = startPoint.getText().toString();
                String end = endPoint.getText().toString();
                String data = txtcalender.getText().toString();
                String time = txtalarm.getText().toString();
                if (!name.isEmpty()) {


                    if (start.isEmpty()) {
                        Toast.makeText(update.this, "enter start point", Toast.LENGTH_SHORT).show();
                    }
                    if (end.isEmpty()) {
                        Toast.makeText(update.this, "enter end point", Toast.LENGTH_SHORT).show();
                    }
                    if (data.isEmpty()) {
                        Toast.makeText(update.this, "enter data", Toast.LENGTH_SHORT).show();
                    }
                    if (time.isEmpty()) {
                        Toast.makeText(update.this, "enter time", Toast.LENGTH_SHORT).show();
                    } else {
                        Date date = new Date(textTripName.getText().toString(), startPoint.getText().toString(), endPoint.getText().toString(), txtcalender.getText().toString(), txtalarm.getText().toString());
                        DataBaseAdapter db = new DataBaseAdapter(update.this);
                        db.updateData(date);
                        Toast.makeText(update.this, "update", Toast.LENGTH_SHORT).show();
                        goToMain();

                    }
                }
            }
        });

    }

    private void goToMain() {
        Intent i=new Intent(update.this,HomeActivity.class);
        startActivity(i);
    }

    private void initComponnent() {

        imagcalender=findViewById(R.id.imag_calender);
        txtcalender=findViewById(R.id.txt_calender);
        imagalarm=findViewById(R.id.imag_alarm);
        txtalarm=findViewById(R.id.txt_alarm);
        textTripName=findViewById(R.id.edit_trip_name);
        btnupdate=findViewById(R.id.update);
        startPoint=findViewById(R.id.search_start_point);
        endPoint=findViewById(R.id.search_end_point);
    }
}