package com.singh.nihal.masterkit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class StudentAddFragment extends android.support.v4.app.Fragment
{
    EditText editText1,editText2,editText3;
    Button button;
    private final String batch;

    @SuppressLint("ValidFragment")
    public StudentAddFragment(String batch)
    {
       this.batch=batch;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view =inflater.inflate(R.layout.fragment_cse_student_add, container, false);

        editText1= view.findViewById(R.id.rollnumber);
        editText2=view.findViewById(R.id.name);
        editText3=view.findViewById(R.id.fathername);
        button=view.findViewById(R.id.addStudent);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(batch.equals("CSE"))
               {
                   CseClassDatabase cseClassDatabase=new CseClassDatabase(getContext());
                   boolean result=cseClassDatabase.insertStudent(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString());
                   if(result)
                       Toast.makeText(getContext(), "successfully insert", Toast.LENGTH_SHORT).show();
                   else
                       Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();

                   editText1.setText("");
                   editText2.setText("");
                   editText3.setText("");


               }else if(batch.equals("ME"))
               {
                   MeClassDatabase meClassDatabase=new MeClassDatabase(getContext());
                   boolean result=meClassDatabase.insertData(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString());

                   if(result)
                       Toast.makeText(getContext(), "successfully inserted", Toast.LENGTH_SHORT).show();
                   else
                       Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                   editText1.setText("");
                   editText2.setText("");
                   editText3.setText("");


               }else if(batch.equals("ECE"))
               {

                   EceClassDatabase eceClassDatabase=new EceClassDatabase(getContext());
                   boolean result=eceClassDatabase.insertData(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString());

                   if(result)
                       Toast.makeText(getContext(), "successfully inserted", Toast.LENGTH_SHORT).show();
                   else
                       Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();

                   editText1.setText("");
                   editText2.setText("");
                   editText3.setText("");


               }else if(batch.equals("EEE"))
               {
                   EeeClassDatabase eeeClassDatabase=new EeeClassDatabase(getContext());
                   boolean result=eeeClassDatabase.insertData(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString());

                   if(result)
                       Toast.makeText(getContext(), "successfully inserted", Toast.LENGTH_SHORT).show();
                   else
                       Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();

                   editText1.setText("");
                   editText2.setText("");
                   editText3.setText("");

               }
            }
        });
      return view;
    }


}
