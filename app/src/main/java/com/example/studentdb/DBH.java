package com.example.studentdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBH extends SQLiteOpenHelper {
    String tblname="studentdata";
    String colname="Name";
    String colroll="RollNumber";
    String coldob="DOB";
    String colphone="PhoneNumber";
    String coldept="Department";
    String res="";
    String qry="create table "+tblname+"("+colname+" text,"+colroll+" text,"+coldob+" text,"+colphone+" text,"+coldept+" text)";
    SQLiteDatabase sq;
    public DBH(Context context) {
        super(context, "student.db", null, 1);
        sq=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void savedat(String stname, String stroll, String stdob, String stphone, String stdept) {
        ContentValues cv=new ContentValues();
        cv.put(colname,stname);
        cv.put(colroll,stroll);
        cv.put(coldob,stdob);
        cv.put(colphone,stphone);
        cv.put(coldept,stdept);
        sq.insert(tblname,null,cv);

    }

    public String getdat(String stdept) {
        Cursor c;
        c=sq.query(tblname,null,coldept+"=?",new String[]{stdept},null,null,null);
        c.moveToFirst();
        if(c.getCount()<1)
        {
            return "No data";
        }
        do {
            res += "\n" + c.getString(c.getColumnIndex(colname)) + "\n" +
                    c.getString(c.getColumnIndex(colroll)) + "\n" +
                    c.getString(c.getColumnIndex(coldob)) + "\n" +
                    c.getString(c.getColumnIndex(colphone)) + "\n" ;
        }while(c.moveToNext());
        return res;
    }
}
