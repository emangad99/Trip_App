package com.example.trip_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    ImageButton btnlogin;
    ImageView btngoogle,btnfacebook,btntwitter;
    TextView txtregister;
    EditText name, password;
    CheckBox checkBox;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fluent-cosine-299116-default-rtdb.firebaseio.com/");
    public String PREFS_NAME = "prefs";
    public String PREFS_USERNAME = "prefsUsername";
    public String PREFS_PASSWORD = "prefsPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.txtEmailLog);
        password = findViewById(R.id.txtPassLog);
        txtregister = findViewById(R.id.txt_register);
        btnlogin = findViewById(R.id.btn_login);
        checkBox = findViewById(R.id.checkBox);
        btngoogle=findViewById(R.id.btnGoogleLog);
        btnfacebook=findViewById(R.id.btnFaceLog);
        btntwitter=findViewById(R.id.btnTwitterLog);
        SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        String userName = pref.getString(PREFS_USERNAME, "");
        String passWord = pref.getString(PREFS_PASSWORD, "");



        txtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);

                startActivity(registerIntent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (name.getText().toString().isEmpty()) {
                    name.setError("Enter your E-mail");
                    name.requestFocus();
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("Enter your Password");
                    password.requestFocus();
                } else {

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(name.getText().toString())) {
                                String getPss = snapshot.child(name.getText().toString()).child("password").getValue(String.class);
                                if (getPss.equals(password.getText().toString())) {

                                    if (checkBox.isChecked()) {

                                        getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                                                .edit()
                                                .putString(PREFS_USERNAME, name.getText().toString())
                                                .putString(PREFS_PASSWORD, password.getText().toString())
                                                .commit();
                                    }

                                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                } else {
                                    Toast.makeText(MainActivity.this, "Wrong password", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Wrong username", Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });

        //login Google

        btngoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentgoogle = new Intent(MainActivity.this,GoogleAuth.class);
                intentgoogle.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentgoogle);
            }
        });

        //login Facebook

        btnfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentface = new Intent(MainActivity.this,FacebookAuth.class);
                intentface.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentface);

            }
        });

        //login twitter
        btntwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenttwitter = new Intent(MainActivity.this,TwitterActivity.class);
                intenttwitter.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intenttwitter);
            }
        });




    }

}


