package com.codebusters.medlife;

/**
 * Created by AYUSH on 2/11/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by User on 11/16/2016.
 */

public class Dbhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Contacts.db";
    public static final String TABLE_CONTACTS="Contacts";
    public static final String STUDENT_NAME1="FNAME";
    public static final String STUDENT_NAME2="LNAME";
    public static final String STUDENT_CONTACT="CONTACT";
    public static final String STUDENT_MAIL="MAIL";
    public static final int Database_version=1;

    public static final String tablename="UserMed";
    public static final String Col_Medname="name";
    public static final String Col_Dose="dose";
    public static final String Col_Date_Added="dateadded";
    public static final String Col_Medtime="time";
    public static final String Col_Date_Stop="datestoped";
    public static final String Interval="interval";
    public Dbhelper(Context context) {
        super(context, DATABASE_NAME, null, Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists Student"+"(DOSE TEXT,DATEADDED DATE,TIME TIME,DATESTOP DATE,INTERVAL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insrtdata(String dose, String start, String stop, String intervel){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(Col_Dose,dose);
        values.put(Col_Date_Added,start);
        values.put(Col_Date_Stop,stop);
        values.put(Interval,intervel);
        db.insert(TABLE_CONTACTS,null,values);

    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(Col_Dose)));
            res.moveToNext();
        }
        return array_list;
    }
}
