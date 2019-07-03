package com.singh.nihal.masterkit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {
    final Context context;
    final ArrayList arrayList;
    final int resource;

    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList arrayList) {
        super(context, resource,arrayList);
        this.context=context;
       this.arrayList=arrayList;
       this.resource=resource;

    }

    @Override

    public View getView(int position , View viewConvert, ViewGroup parent)
    {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(resource,parent,false);
        TextView textView1=rowView.findViewById(R.id.rollnumber);
        TextView textView2=rowView.findViewById(R.id.name);
        TextView textView3=rowView.findViewById(R.id.fathername);


            String[] values= (String[]) arrayList.get(position);
            textView1.setText(textView1.getText().toString()+values[0]);
            textView2.setText(textView2.getText().toString()+values[1]);
            textView3.setText(textView3.getText().toString()+values[2]);
        return rowView;
    }


}
