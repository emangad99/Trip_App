package com.example.trip_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Helper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "data2";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "data_trip2";
    public static final String KEY_ID = "key_id";
    public static final String TRIP_NAME = "trip_name";
    public static final String START_POINT = "start_point";
    public static final String END_POINT = "END_point";
    public static final String TIME = "time";
    public static final String DATE = "date";
  //  public  static final String  TRIP_REMENDIER="Trip_remeber";
  //  public static final  String TRIP_TYPE="Trip_type";




    public Helper( Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+KEY_ID+" INT PRIMARY KEY ,"+
                TRIP_NAME+" TEXT NOT NULL,"+
                START_POINT+" TEXT NOT NULL,"+
                END_POINT+" TEXT NOT NULL,"+
                TIME+" TEXT NOT NULL,"+
                DATE+" TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
if (oldVersion>=newVersion)
    return;
db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
onCreate(db);
    }
}
