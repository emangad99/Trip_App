
package com.example.trip_app;


import static com.example.trip_app.Helper.KEY_ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


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
        contentValues.put(Helper.DATE,data.getDATE());
        contentValues.put(Helper.TIME,data.getTIME());

      //  contentValues.put(Helper.TRIP_REMENDIER,data.getTRIPREMENDIER());
       // contentValues.put(Helper.TRIP_TYPE,data.getTRIPTYPE());
        long ID = db.insert(Helper.TABLE_NAME,null ,contentValues);
        Log.d("Inserted","ID->"+ID);
        return ID;

    }
    public  Date getdate(long id)
    {
       SQLiteDatabase db= dbHelper.getReadableDatabase();
       Cursor c=db.query(Helper.TABLE_NAME,new String[]{Helper.TRIP_NAME,Helper.START_POINT,Helper.END_POINT,Helper.DATE,Helper.TIME},KEY_ID+"=?"
       ,new  String[]{String.valueOf(id)},null,null,null);
       if(c!= null)
           c.moveToFirst();
       Date date=new Date(c.getLong(0),c.getString(1),c.getString(2), c.getString(3),c.getString(4) ,c.getString(5));
       return date;


    }


    public List<Date> getAlldata()
    {

     SQLiteDatabase db =dbHelper.getReadableDatabase();
     List<Date> dates=new ArrayList<>();
     String query=" SELECT*FROM "+Helper.TABLE_NAME;
     Cursor c =db.rawQuery(query,null);
     if (c.moveToFirst())
     {
         do {
             Date date =new Date();
             date.setID(c.getLong(0));
             date.setTRIPNAME(c.getString(1));
             date.setSTARTPOINT(c.getString(2));
             date.setENDPOINT(c.getString(3));
             date.setDATE(c.getString(4));
             date.setTIME(c.getString(5));

             dates.add(date);

         }while (c.moveToNext());
     }
return dates;
    }
}
