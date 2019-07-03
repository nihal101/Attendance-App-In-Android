package com.singh.nihal.masterkit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EceClassDatabase extends SQLiteOpenHelper {

    public static final String DATABASENAME="ECE";
    public static final String TABLENAME="ECECLASS";
    public static final String ROLLNUMBER="ROLLNUMBER";
    public static final String STUDENTNAME="STUDENTNAME";
    public static final String FATHERNAME="FATHERNAME";
    private static int i=0;

    public EceClassDatabase(Context context) {
        super(context, DATABASENAME, null, 1);i=0;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + TABLENAME + "(ROLlNUMBER TEXT  , STUDENTNAME TEXT , FATHERNAME TEXT)");
        sqLiteDatabase.execSQL("create table "+"ECEATTENDANCE"+ "(ROLLNUMBER TEXT  , STATUS TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists "+TABLENAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String rollnumber,String name,String fathername)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ROLLNUMBER,rollnumber);
        contentValues.put(STUDENTNAME,name);
        contentValues.put(FATHERNAME,fathername);

        long result=sqLiteDatabase.insert(TABLENAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLENAME,null);
        return cursor;
    }

    public boolean insertIntoCustomTable(String rollNumber,String status)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        if(i==0)
        {
            sqLiteDatabase.execSQL("delete from "+"ECEATTENDANCE");
            i++;
        }
        ContentValues contentValues=new ContentValues();
        contentValues.put(ROLLNUMBER,rollNumber);
        contentValues.put("STATUS",status);

        long result=sqLiteDatabase.insert("ECEATTENDANCE",null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor getDataFromCustomTable()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+"ECEATTENDANCE",null);
        return cursor;
    }
}
