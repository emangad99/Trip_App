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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    TextView txtlogin;
    ImageView btngoogle,btnfacebook,btntwitter;
    EditText txtName, txtEmail, txtPhone, txtPass;
    FirebaseAuth mAuth;
    String pww = "^.*(?=.{8,})(?=.*\\d)(?=.*[a-zA-Z])|(?=.{8,})(?=.*\\d)(?=.*[!@#$%^&])|(?=.{8,})(?=.*[a-zA-Z])(?=.*[!@#$%^&]).*$";
    ImageButton btnReg;
    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReferenceFromUrl("https://fluent-cosine-299116-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnReg = findViewById(R.id.btnRegister);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        txtPhone = findViewById(R.id.txtPhone);
        btngoogle=findViewById(R.id.btnGoogleLog);
        btnfacebook=findViewById(R.id.btnFaceLog);
        mAuth = FirebaseAuth.getInstance();
        txtlogin = findViewById(R.id.txt_login);
        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);

                startActivity(loginIntent);
            }
        });

        btngoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentgoogle = new Intent(RegisterActivity.this,GoogleAuth.class);
                intentgoogle.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentgoogle);
            }
        });

        //login Facebook

        btnfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentface = new Intent(RegisterActivity.this,FacebookAuth.class);
                intentface.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentface);

            }
        });


    }

    public void Reg(View view) {
        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String phone = txtPhone.getText().toString();
        String password = txtPass.getText().toString();

        if (name.isEmpty()) {
            txtName.setError("Enter your Name");
            txtName.requestFocus();

        }
        if (phone.isEmpty()) {
            txtPhone.setError("Enter phone");
            txtPhone.requestFocus();
        }
        if (email.isEmpty()) {
            txtEmail.setError("Enter E-mail");
            txtEmail.requestFocus();
        }
        if (!password.matches(pww)) {
            txtPass.setError("Password must contain at least 8 (Upper case, Lower case,Numbers and signs)");
            txtPass.requestFocus();
        }else {

            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(name)){
                        Toast.makeText(RegisterActivity.this,"User name already exist!!",Toast.LENGTH_LONG).show();
                    }else {
                        databaseReference.child("users").child(name).child("email").setValue(email);
                        databaseReference.child("users").child(name).child("phone").setValue(phone);
                        databaseReference.child("users").child(name).child("password").setValue(password);

                        AlertDialog.Builder b = new AlertDialog.Builder(RegisterActivity.this)
                                .setTitle("Register....")
                                .setMessage("Your account has been created")
                                .setIcon(R.drawable.logo)
                                .setPositiveButton("Thanks", null)
                                .setNegativeButton("Go to Login", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    }
                                });
                        b.create().show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

    }
    }









