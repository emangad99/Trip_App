package com.example.trip_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    TextView txtlogin;
    EditText txtName, txtEmail, txtPhone, txtPass;
    FirebaseAuth mAuth;
    String pww = "^.*(?=.{8,})(?=.*\\d)(?=.*[a-zA-Z])|(?=.{8,})(?=.*\\d)(?=.*[!@#$%^&])|(?=.{8,})(?=.*[a-zA-Z])(?=.*[!@#$%^&]).*$";
    ImageButton btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnReg = findViewById(R.id.btnRegister);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        txtPhone = findViewById(R.id.txtPhone);
        mAuth = FirebaseAuth.getInstance();
        txtlogin = findViewById(R.id.txt_login);
        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);

                startActivity(loginIntent);
            }
        });


    }
}








