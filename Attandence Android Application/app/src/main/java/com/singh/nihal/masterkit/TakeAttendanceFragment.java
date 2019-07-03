package com.singh.nihal.masterkit;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class TakeAttendanceFragment extends android.support.v4.app.Fragment
{
     ArrayList al;
     String className;
     ListView listView;
     Button save;
     private MyAdapter1 myAdapter1;

     @SuppressLint("ValidFragment")
     public TakeAttendanceFragment(String className)
     {
         this.className=className;
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_take_attendance, container, false);
        listView=view.findViewById(R.id.listView);
        clickOnListView();
        save=view.findViewById(R.id.save);

        if(className.equals("CSE"))
            takeAttendanceForCSE();
        else if(className.equals("ECE"))
            takeAttendanceForECE();
        else if(className.equals("ME"))
            takeAttendanceForME();
        else if(className.equals("EEE"))
            takeAttendanceForEEE();

        clickOnsave(className,view);

        return view;
    }

    private void clickOnsave(final String className, View view) {

         save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 AlertDialog.Builder al=new AlertDialog.Builder(getContext());
                 al.setTitle("Warning...");
                 al.setCancelable(false);
                 al.setMessage("After click on save button your prevoius attendance data will be automatically remove");
                 al.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {

                     }
                 });
                 al.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         putDataFromListToDatabase(className);
                         Toast.makeText(getContext(), "suuccess save", Toast.LENGTH_SHORT).show();
                         save.setClickable(false);
                     }
                 });
                 al.show();
             }

         });

    }

    private void putDataFromListToDatabase(String className) {
         TextView rollNumber;
         TextView status;

       if(className.equals("CSE"))
       {
           CseClassDatabase cseClassDatabase=new CseClassDatabase(getContext());
           for(int i=0;i<listView.getAdapter().getCount();i++) {
                View view = listView.getAdapter().getView(i, null, listView);
                rollNumber = view.findViewById(R.id.rollnumber);
                status = view.findViewById(R.id.status);
                cseClassDatabase.insertIntoCustomTable(rollNumber.getText().toString(), status.getText().toString());
            }
       }else if(className.equals("ME"))
       {
           MeClassDatabase meClassDatabase=new MeClassDatabase(getContext());
           for(int i=0;i<listView.getAdapter().getCount();i++) {
               View view = listView.getAdapter().getView(i, null, listView);
               rollNumber = view.findViewById(R.id.rollnumber);
               status = view.findViewById(R.id.status);
               meClassDatabase.insertIntoCustomTable(rollNumber.getText().toString(), status.getText().toString());
           }

       }else if(className.equals("ECE"))
       {
           EceClassDatabase eceClassDatabase=new EceClassDatabase(getContext());
           for(int i=0;i<listView.getAdapter().getCount();i++) {
               View view = listView.getAdapter().getView(i, null, listView);
               rollNumber = view.findViewById(R.id.rollnumber);
               status = view.findViewById(R.id.status);
               eceClassDatabase.insertIntoCustomTable(rollNumber.getText().toString(), status.getText().toString());
           }

       }else if(className.equals("EEE"))
       {
           EeeClassDatabase eeeClassDatabase=new EeeClassDatabase(getContext());
           for(int i=0;i<listView.getAdapter().getCount();i++) {
               View view = listView.getAdapter().getView(i, null, listView);
               rollNumber = view.findViewById(R.id.rollnumber);
               status = view.findViewById(R.id.status);
               eeeClassDatabase.insertIntoCustomTable(rollNumber.getText().toString(), status.getText().toString());
           }
       }

     }


    private void takeAttendanceForCSE()
    {

        CseClassDatabase cseClassDatabase=new CseClassDatabase(getContext());
        Cursor cursor=cseClassDatabase.getAllData();
        al=new ArrayList();

        while(cursor.moveToNext())
        {
        String[] str=new String[4];
        str[0]=cursor.getString(0);
        str[1]=cursor.getString(1);
        str[2]=cursor.getString(2);
        str[3]="absent";
        al.add(str);
        }
        myAdapter1=new MyAdapter1(getContext(),R.layout.list_layout_for_attendance,al);
        listView.setAdapter(myAdapter1);
    }

    private void takeAttendanceForME()
    {
        MeClassDatabase meClassDatabase=new MeClassDatabase(getContext());
        Cursor cursor=meClassDatabase.getAllData();
        al=new ArrayList();

        while(cursor.moveToNext())
        {
        String[] str=new String[4];
        str[0]=cursor.getString(0);
        str[1]=cursor.getString(1);
        str[2]=cursor.getString(2);
        str[3]="absent";
        al.add(str);
        }
        myAdapter1=new MyAdapter1(getContext(),R.layout.list_layout_for_attendance,al);
        listView.setAdapter(myAdapter1);
    }

       private void takeAttendanceForECE()
       {
        EceClassDatabase eceClassDatabase=new EceClassDatabase(getContext());
        Cursor cursor=eceClassDatabase.getAllData();
        al=new ArrayList();

        while(cursor.moveToNext())
        {
        String[] str=new String[4];
        str[0]=cursor.getString(0);
        str[1]=cursor.getString(1);
        str[2]=cursor.getString(2);
        str[3]="absent";
        al.add(str);
        }
        myAdapter1=new MyAdapter1(getContext(),R.layout.list_layout_for_attendance,al);
        listView.setAdapter(myAdapter1);
       }

     private void takeAttendanceForEEE()
     {
        EeeClassDatabase eeeClassDatabase=new EeeClassDatabase(getContext());
        Cursor cursor=eeeClassDatabase.getAllData();
        al=new ArrayList();

        while(cursor.moveToNext())
        {
        String[] str=new String[4];
        str[0]=cursor.getString(0);
        str[1]=cursor.getString(1);
        str[2]=cursor.getString(2);
        str[3]="absent";
        al.add(str);
        }
        myAdapter1=new MyAdapter1(getContext(),R.layout.list_layout_for_attendance,al);
        listView.setAdapter(myAdapter1);
     }

     private void clickOnListView()
     {
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 myAdapter1.changeValue(i);
             }
         });
     }
}
