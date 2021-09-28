package com.example.trip_app;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class notes extends AppCompatActivity implements View.OnClickListener {
LinearLayout layout;
Button btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        setContentView(R.layout.activity_notes);
        getSupportActionBar().hide();

        layout=findViewById(R.id.layout_list_addnotes);
        btnadd=findViewById(R.id.btn_add);

        btnadd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        addView();
    }

    private void addView() {
        View clickView = getLayoutInflater().inflate(R.layout.row_add_linear,null,false);
        EditText editText = (EditText)clickView.findViewById(R.id.edit_add_notes);
        ImageView imageclose=(ImageView)clickView.findViewById(R.id.image_remove);

        imageclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(clickView);
            }
        });
        layout.addView(clickView);
    }
    private void removeView(View view){
        layout.removeView(view);

    }
}