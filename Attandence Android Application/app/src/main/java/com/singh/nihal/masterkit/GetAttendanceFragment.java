package com.singh.nihal.masterkit;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class GetAttendanceFragment extends Fragment {

   ListView listView;
   String className;

   @SuppressLint("ValidFragment")
   GetAttendanceFragment(String className)
   {
       this.className=className;
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_get_attendance, container, false);

        listView=view.findViewById(R.id.listView);

        if(className.equals("CSE"))
            getAttendanceFromCSE();
        else if(className.equals("ME"))
            getAttendanceFromMe();
        else if (className.equals("ECE"))
            getAttendanceFromECE();
        else if(className.equals("EEE"))
            getAttendanceFromEEE();

        return view;
    }

    private void getAttendanceFromCSE() {
        ArrayList al=new ArrayList();
        CseClassDatabase cseClassDatabase=new CseClassDatabase(getContext());
        Cursor cursor=cseClassDatabase.getAllData();
        Cursor cursor1=cseClassDatabase.getDataFromCustomTable();
        while(cursor.moveToNext())
        {
            cursor1.moveToNext();
            String[] str=new String[3];
            str[0]=cursor.getString(0);
            str[1]=cursor.getString(1);
            str[2]=cursor1.getString(1);
            al.add(str);
        }
        MyAdapter myAdapter=new MyAdapter(getContext(),R.layout.listlayoutattendance,al);
        listView.setAdapter(myAdapter);
    }

    private void getAttendanceFromMe()
    {
        ArrayList al=new ArrayList();
        MeClassDatabase meClassDatabase=new MeClassDatabase(getContext());
        Cursor cursor=meClassDatabase.getAllData();
        Cursor cursor1=meClassDatabase.getDataFromCustomTable();
        while(cursor.moveToNext())
        {
            cursor1.moveToNext();
            String[] str=new String[3];
            str[0]=cursor.getString(0);
            str[1]=cursor.getString(1);
            str[2]=cursor1.getString(1);
            al.add(str);
        }
        MyAdapter myAdapter=new MyAdapter(getContext(),R.layout.listlayoutattendance,al);
        listView.setAdapter(myAdapter);
    }

    private void getAttendanceFromECE()
    {
        ArrayList al=new ArrayList();
        EceClassDatabase eceClassDatabase=new EceClassDatabase(getContext());
        Cursor cursor=eceClassDatabase.getAllData();
        Cursor cursor1=eceClassDatabase.getDataFromCustomTable();
        while(cursor.moveToNext())
        {
            cursor1.moveToNext();
            String[] str=new String[3];
            str[0]=cursor.getString(0);
            str[1]=cursor.getString(1);
            str[2]=cursor1.getString(1);
            al.add(str);
        }
        MyAdapter myAdapter=new MyAdapter(getContext(),R.layout.listlayoutattendance,al);
        listView.setAdapter(myAdapter);
    }

    private void getAttendanceFromEEE()
    {

        ArrayList al=new ArrayList();
        EeeClassDatabase eeeClassDatabase=new EeeClassDatabase(getContext());
        Cursor cursor=eeeClassDatabase.getAllData();
        Cursor cursor1=eeeClassDatabase.getDataFromCustomTable();
        while(cursor.moveToNext())
        {
            cursor1.moveToNext();
            String[] str=new String[3];
            str[0]=cursor.getString(0);
            str[1]=cursor.getString(1);
            str[2]=cursor1.getString(1);
            al.add(str);
        }
        MyAdapter myAdapter=new MyAdapter(getContext(),R.layout.listlayoutattendance,al);
        listView.setAdapter(myAdapter);
    }

}
