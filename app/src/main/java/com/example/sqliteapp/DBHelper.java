package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "UserDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Students(name TEXT, roll TEXT primary key, sabaq TEXT, sabaqi TEXT, manzil TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Students");
    }

    public Boolean insertUserData(String name, String roll, String sabaq, String sabaqi, String manzil){
        SQLiteDatabase DB= this.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("roll",roll);
        values.put("sabaq",sabaq);
        values.put("sabaqi",sabaqi);
        values.put("manzil",manzil);

        long results= DB.insert("Students",null,values);
        if(results==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean UpdateUserData(String name, String roll, String sabaq, String sabaqi, String manzil){
        SQLiteDatabase DB= this.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);

        values.put("sabaq",sabaq);
        values.put("sabaqi",sabaqi);
        values.put("manzil",manzil);
        //Cursor selects rows So here it will select row with roll number equals to our variable
        //So cursor holds the data basically
        Cursor cursor= DB.rawQuery("Select * from Students where roll=?",new String[]{roll});

        if(cursor.getCount()>0) {
            long results = DB.update("Students", values, "roll=?", new String[]{roll});
            if (results == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }


    public Boolean deleteUserData(String roll){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor= DB.rawQuery("Select * from Students where roll=?",new String[]{roll});

        if(cursor.getCount()>0) {
            long results = DB.delete("Students", "roll=?", new String[]{roll});
            if (results == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
    //Records of Datag
    public Cursor showUserData(){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor= DB.rawQuery("Select * from Students",null );

        return cursor;
    }
}
