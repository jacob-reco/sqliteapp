package com.recomedical.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(healthcare_id TEXT primary key, firstname TEXT, lastname TEXT, healthcare_email TEXT, role TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata (String healthcare_id, String firstname, String lastname, String healthcare_email, String role) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("healthcare_id", healthcare_id);
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("healthcare_email", healthcare_email);
        contentValues.put("role", role);
        long result=DB.insert("Userdetails", null, contentValues);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean updateuserdata (String healthcare_id, String firstname, String lastname, String healthcare_email, String role) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("healthcare_email", healthcare_email);
        contentValues.put("role", role);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where healthcare_id = ?", new String[]{healthcare_id});
        if (cursor.getCount() > 0) {

            long result = DB.update("Userdetails", contentValues, "healthcare_id=?", new String[]{healthcare_id});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
            } else {
            return false;
        }
    }

    public Boolean deletedata (String healthcare_id) {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Userdetails where healthcare_id = ?", new String[]{healthcare_id});
        if (cursor.getCount() > 0) {

            long result = DB.delete ("Userdetails", "healthcare_id=?", new String[]{healthcare_id});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata () {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
}
