package com.singh.nihal.masterkit;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class GetAllDataFragment extends Fragment {

   ListView listView;
   ArrayList al;
   private final String batch;


   @SuppressLint("ValidFragment")
   public GetAllDataFragment(String batch)
   {
       this.batch=batch;
   }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_get_all_data, container, false);

       listView=view.findViewById(R.id.getAllData);

       if(batch.equals("CSE"))
           getAllDataFromCSE();
       else if(batch.equals("ME"))
           getAllDataFromME();
       else if(batch.equals("ECE"))
           getAllDataFromECE();
       else if(batch.equals("EEE"))
           getAllDataFromEEE();


       return view;
    }

    private void getAllDataFromCSE() {
        CseClassDatabase cseClassDatabase = new CseClassDatabase(getContext());
        Cursor cursor = cseClassDatabase.getAllData();

        if (cursor.getCount() == 0)
            Toast.makeText(getContext(), "nothing to show", Toast.LENGTH_SHORT).show();
        else {

            al = new ArrayList();
            while (cursor.moveToNext()) {
                String[] values = new String[3];
                values[0] = cursor.getString(0);
                values[1] = cursor.getString(1);
                values[2] = cursor.getString(2);
                al.add(values);
            }
            MyAdapter myAdapter = new MyAdapter(getContext(), R.layout.listlayout, al);
            listView.setAdapter(myAdapter);
        }
    }

    private void getAllDataFromME()
    {
        MeClassDatabase meClassDatabase=new MeClassDatabase(getContext());
        Cursor cursor =meClassDatabase.getAllData();

        if(cursor.getCount()==0)
            Toast.makeText(getContext(), "nothing to show", Toast.LENGTH_SHORT).show();
        else
        {
            al=new ArrayList();
            while (cursor.moveToNext())
            {
                String[] values=new String[3];
                values[0]=cursor.getString(0);
                values[1]=cursor.getString(1);
                values[2]=cursor.getString(2);
                al.add(values);
            }
            MyAdapter myAdapter=new MyAdapter(getContext(),R.layout.listlayout,al);
            listView.setAdapter(myAdapter);
        }
    }


    private void getAllDataFromECE()
    {
        EceClassDatabase eceClassDatabase=new EceClassDatabase(getContext());
        Cursor cursor=eceClassDatabase.getAllData();

        if(cursor.getCount()==0)
            Toast.makeText(getContext(), "nothing to show", Toast.LENGTH_SHORT).show();
        else
        {
            al=new ArrayList();
            while(cursor.moveToNext())
            {
                String[] values=new String[3];
                values[0]=cursor.getString(0);
                values[1]=cursor.getString(1);
                values[2]=cursor.getString(2);
                al.add(values);
            }
            MyAdapter myAdapter=new MyAdapter(getContext(),R.layout.listlayout,al);
            listView.setAdapter(myAdapter);
        }
    }


    private void getAllDataFromEEE()
    {
        EeeClassDatabase eeeClassDatabase=new EeeClassDatabase(getContext());
        Cursor cursor=eeeClassDatabase.getAllData();

        if(cursor.getCount()==0)
            Toast.makeText(getContext(), "nothing to show", Toast.LENGTH_SHORT).show();
        else
        {
            al=new ArrayList();
            while(cursor.moveToNext())
            {
                String[] values=new String[3];
                values[0]=cursor.getString(0);
                values[1]=cursor.getString(1);
                values[2]=cursor.getString(2);
                al.add(values);
            }
            MyAdapter myAdapter=new MyAdapter(getContext(),R.layout.listlayout,al);
            listView.setAdapter(myAdapter);
        }
    }
}
