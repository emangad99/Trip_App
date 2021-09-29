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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddTripActivity extends AppCompatActivity {
ImageView imagcalender;
TextView txtcalender;
ImageView imagalarm;
TextView txtalarm;
int t1Hour,t1Minute;
private Spinner spinnertxt1;
private Spinner spinnertxt2;
EditText textTripName;
ImageButton btnAddTrip;
EditText startPoint;
EditText endPoint;
 String apiKey="AIzaSyBtUmi_yFMSLHXfA5WmkdtUbvpkHwsZwcI";


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

        initComponnent();


    Places.initialize(getApplicationContext(),apiKey);
    PlacesClient placesClient=Places.createClient(AddTripActivity.this);
    final List<Place.Field> fieldList= Arrays.asList(Place.Field.ADDRESS,Place.Field.LAT_LNG,Place.Field.NAME);
    startPoint.setFocusable(false);
    startPoint.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent=new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,fieldList).build(AddTripActivity.this);
            startActivityForResult(intent,100);

        }
    });
    endPoint.setFocusable(false);
    endPoint.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent=new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,fieldList).build(AddTripActivity.this);
            startActivityForResult(intent,100);

        }
    });

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

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&& resultCode==RESULT_OK)
        {
            Place place=Autocomplete.getPlaceFromIntent(data);
            startPoint.setText(place.getAddress());
            endPoint.setText(place.getAddress());
        }
        else if(resultCode== AutocompleteActivity.RESULT_ERROR)
        {
            Status status= Autocomplete.getStatusFromIntent(data);
            Toast.makeText(getApplicationContext(),status.getStatusMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    private void initComponnent()
    {
        spinnertxt1=findViewById(R.id.spinner_txt1);
        spinnertxt2=findViewById(R.id.spinner_txt2);
        imagcalender=findViewById(R.id.imag_calender);
        txtcalender=findViewById(R.id.txt_calender);
        imagalarm=findViewById(R.id.imag_alarm);
        txtalarm=findViewById(R.id.txt_alarm);
        textTripName=findViewById(R.id.edit_trip_name);
        btnAddTrip=findViewById(R.id.btn_add_trip);
        startPoint=findViewById(R.id.search_start_point);
        endPoint=findViewById(R.id.search_end_point);
    }
}