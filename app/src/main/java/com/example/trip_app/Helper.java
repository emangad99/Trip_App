package com.example.trip_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "data";
    public static final int DATABASE_VERSION = 1;
    public static final String KEY_ID = "key_id";
    public static final String TABLE_NAME = "data_trip";
    public static final String TRIP_NAME = "trip_name";
    public static final String START_POINT = "start_point";
    public static final String END_POINT = "END_point";
    public static final String TIME = "time";
    public static final String DATE = "date";

    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+KEY_ID+" INT PRIMARY KEY AUTOINCREMENT,"+
            TRIP_NAME+" TEXT NOT NULL,"+
            START_POINT+" TEXT NOT NULL,"+
            END_POINT+" TEXT NOT NULL,"+
            TIME+" time NOT NULL,"+
            DATE+" date NOT NULL)";


    public Helper( Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
