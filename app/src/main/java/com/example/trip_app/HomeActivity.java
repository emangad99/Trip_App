package com.example.trip_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
ImageView imagadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        imagadd=findViewById(R.id.img_add);
        imagadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent loginIntent = new Intent (HomeActivity.this,MainActivity.class);

               // startActivity(loginIntent);
            }
        });
    }
}