package com.example.trip_app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class notes extends AppCompatActivity implements View.OnClickListener {
LinearLayout layout;
Button btnadd,button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        layout=findViewById(R.id.layout_list_addnotes);
        btnadd=findViewById(R.id.btn_add);
        button = findViewById(R.id.button);
        btnadd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        addView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText =findViewById(R.id.edit_add_notes);
                String note1 = editText.getText().toString();



                if (note1.isEmpty()) {
                    Toast.makeText(notes.this, "enter your notes", Toast.LENGTH_SHORT).show();
                }
                else{

                    Toast.makeText(notes.this, "saved", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(notes.this,HomeActivity.class);
                    startActivity(i);
                }

            }
        });
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