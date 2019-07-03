package com.singh.nihal.masterkit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.DuplicateFormatFlagsException;

public class DatabaseClass extends SQLiteOpenHelper {

    public static final String DATABASENAME="MASTERKIT";
    public static final String TABLENAME="TEACHERINFORMATION";
    public static final String NAME="NAME";
    public static final String USERNAME="USERNAME";
    public static final String BRANCH="BRANCH";
    public static final String PASSWARD="PASSWARD";
    public static final String EMAIL="EMAIL";
    public static final String MOBILENO="MOBILENO";
    public static final String POSTION="POSITION";
    public static final String COLLEGENAME="COLLEGENAME";

    public DatabaseClass(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table "+TABLENAME +"(USERNAME TEXT PRIMARY KEY , NAME TEXT , BRANCH TEXT,PASSWARD TEXT , EMAIL TEXT , MOBILENO TEXT , POSITION TEXT , COLLEGENAME TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String username,String name,String branch,String passward,String email,String mobileNo,String postion,String collegeName)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(USERNAME,username);
        contentValues.put(NAME,name);
        contentValues.put(BRANCH,branch);
        contentValues.put(PASSWARD,passward);
        contentValues.put(EMAIL,email);
        contentValues.put(MOBILENO,mobileNo);
        contentValues.put(POSTION,postion);
        contentValues.put(COLLEGENAME,collegeName);

        long   result=sqLiteDatabase.insert(TABLENAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;
    }

    public  Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+ TABLENAME,null);
        return cursor;
    }

    public boolean updataData(String username,String name,String branch,String email,String mobileNo,String postion,String collegename)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(USERNAME,username);
        contentValues.put(NAME,name);
        contentValues.put(BRANCH,branch);
        contentValues.put(USERNAME,username);
        contentValues.put(EMAIL,email);
        contentValues.put(MOBILENO,mobileNo);
        contentValues.put(POSTION,postion);
        contentValues.put(COLLEGENAME,collegename);

        int res=sqLiteDatabase.update(TABLENAME,contentValues,"USERNAME = ?",new String[] {username});

       if(res==0)
           return false;
       else
           return true;
    }
}
