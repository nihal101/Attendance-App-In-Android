package com.singh.nihal.masterkit;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment
 {
     private EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7;
     TextView textView1,textView2;
     ArrayList al;
     CircleImageView circleImageView;
     String name,username,email,mobileNo,barnch,position,collegeName;

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         View view= inflater.inflate(R.layout.fragment_profile, container, false);
         findId(view);

         final DatabaseClass databaseClass=new DatabaseClass(getContext());
         Cursor cursor=databaseClass.getAllData();
         al=new ArrayList();
         while(cursor.moveToNext())
         {
             al.add(cursor.getString(0));
             al.add(cursor.getString(1));
             al.add(cursor.getString(2));
             al.add(cursor.getString(3));
             al.add(cursor.getString(4));
             al.add(cursor.getString(5));
             al.add(cursor.getString(6));
             al.add(cursor.getString(7));
         }
         setTextToEditText();

         circleImageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
              findTextFromEditText();
              boolean result=databaseClass.updataData(username,name,barnch,email,mobileNo,position,collegeName);
               if(result)
                   Toast.makeText(getContext(), "updated", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(getContext(), "some error", Toast.LENGTH_SHORT).show();
             }
         });


         return view;


     }

     private void findTextFromEditText() {
         name=editText1.getText().toString();
         username=editText2.getText().toString();
         email=editText3.getText().toString();
         mobileNo=editText4.getText().toString();
         barnch=editText5.getText().toString();
         position=editText6.getText().toString();
         collegeName=editText7.getText().toString();
     }

     private void setTextToEditText() {

         editText1.setText(al.get(1).toString());
         editText2.setText(al.get(0).toString());
         editText3.setText(al.get(4).toString());
         editText4.setText(al.get(5).toString());
         editText5.setText(al.get(2).toString());
         editText6.setText(al.get(6).toString());
         editText7.setText(al.get(7).toString());
         textView1.setText(textView1.getText().toString()+al.get(0));
         textView2.setText(textView2.getText().toString()+al.get(7));
     }

     private void findId(View view) {

         editText1=view.findViewById(R.id.name);
         editText2=view.findViewById(R.id.username);
         editText3=view.findViewById(R.id.email);
         editText4= view.findViewById(R.id.mobileNo);
         editText5=view.findViewById(R.id.branch);
         editText6=view.findViewById(R.id.position);
         editText7=view.findViewById(R.id.collegename);
         textView1=view.findViewById(R.id.name1);
         textView2=view.findViewById(R.id.collegename1);
         circleImageView=view.findViewById(R.id.circleImage);
     }
 }
