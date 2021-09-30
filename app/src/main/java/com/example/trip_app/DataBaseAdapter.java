
package com.example.trip_app;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class DataBaseAdapter {

    static Helper dbHelper;


    public DataBaseAdapter(Context context)
    {
        if(dbHelper == null) {
            dbHelper = new Helper(context);
        }
    }

    public long insertData(Date data) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Helper.TRIP_NAME,data.getTRIPNAME());
        contentValues.put(Helper.START_POINT,data.getSTARTPOINT());
        contentValues.put(Helper.END_POINT,data.getENDPOINT());
        contentValues.put(Helper.TIME,data.getTIME());
        contentValues.put(Helper.DATE,data.getDATE());
        long id = db.insert(Helper.TABLE_NAME,null ,contentValues);
        db.close();
        return id;

    }
    public ArrayList<Date> getAlldata()
    {

        Cursor c;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns ={Helper.TRIP_NAME,Helper.START_POINT,Helper.END_POINT,Helper.DATE,Helper.TIME};
        c = db.query(Helper.TABLE_NAME,columns, null,null,null,null,null);
        ArrayList<Date> dataList = new ArrayList<>();
        while (c.moveToNext())
        {
            dataList.add(new Date(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));

        }
        c.close();
        db.close();
        return dataList;

    }


}
