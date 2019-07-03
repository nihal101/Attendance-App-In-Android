package com.singh.nihal.masterkit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Newuser extends AppCompatActivity {

    EditText name,username,passward,repassward,branch;
    Button create;
    private String Name,Username,Branch ,Passward,RePassward,email,postion,collegeName,mobileNo;
    private boolean isSame,isAnyEmpty;
    DatabaseClass databaseClass;
    public static boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser);
        databaseClass=new DatabaseClass(this);

        findId();
        clickOnCreate();

         branch.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View view, MotionEvent motionEvent) {

                 findString();
                 isAnyEmpty=isAnyEmpty();
                 isSame=isSame();

                 if(isAnyEmpty)
                     createAlertDialod("Error...","All field should be complete...");
                 else if(!isSame)
                     createAlertDialod("Error...","Passward and Confirm-passward should  be same...");
                 return false;
             }
         });
    }

    public void findId()
    {
        name=findViewById(R.id.name);
        username=findViewById(R.id.username);
        branch=findViewById(R.id.branch);
        passward=findViewById(R.id.passward);
        repassward=findViewById(R.id.reenterpassward);
        create=findViewById(R.id.create);
    }


    public void clickOnCreate()
    {
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findString();
                //isAnyEmpty =isAnyEmpty();
                if(true)
                {
                    result=databaseClass.insertData(Username,Name,Branch, Passward,email,mobileNo,postion,collegeName);
                    if (result) {
                        Toast.makeText(Newuser.this, "Successfully created", Toast.LENGTH_SHORT).show();
                        createSharedPrefernce();
                        startActivity(new Intent(Newuser.this,MainActivity.class));
                    }else
                        Toast.makeText(Newuser.this, "Error", Toast.LENGTH_SHORT).show();
                }
               else{

                    createAlertDialod("Error...","All field should be complete");
                }

            }
        });
    }

    private void createSharedPrefernce() {
        SharedPreferences sharedPreferences=getBaseContext().getSharedPreferences("CreateUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("User",1);
        editor.commit();

    }


    public boolean isAnyEmpty()
    {
        if(Name.isEmpty()||Username.isEmpty()||Passward.isEmpty())
            return true;
        else
            return false;
    }

    private boolean isSame()
    {
        if(passward.getText().toString().equals(repassward.getText().toString()))
            return true;
        else
            return false;
    }

    private void findString()
    {
        Name=name.getText().toString();
        Username=username.getText().toString();
        Passward=passward.getText().toString();
        Branch=branch.getText().toString();
        RePassward=repassward.getText().toString();
        email="null";
        mobileNo="null";
        postion="null";
        collegeName="null";
    }

    private void createAlertDialod(String title,String sms)
    {
        AlertDialog.Builder al=new AlertDialog.Builder(Newuser.this);
        al.setCancelable(true);
        al.setTitle(title);
        al.setMessage(sms);
        al.show();
    }
}
