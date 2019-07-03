package com.singh.nihal.masterkit;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;

public class MainPage extends AppCompatActivity {

    Button button1,button2,button3,button4,button5,button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        MyController.getInstance().setActivity2(this);

        getID();

        clickOnAddStudent();
        clickOnTakeAttendance();
        clickOnGetList();
        clickOnSpecificStudent();
        clickOnRemoveStudent();
        clickOnCloseApp();
    }

    @Override
    public void onBackPressed() {
        MyController.getInstance().closeAllAcitvity();
        super.onBackPressed();
    }

    private void clickOnCloseApp() {

       button6.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             MyController.getInstance().closeAllActivity1();
           }
       });
    }

    private void clickOnRemoveStudent() {
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //createAlertDialog(5);
                Intent intent=new Intent(MainPage.this,MainPageNext.class);
                intent.putExtra("btnKey",5);
                startActivity(intent);

            }
        });
    }

    private void clickOnSpecificStudent() {
       button4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainPage.this,MainPageNext.class);
               intent.putExtra("btnKey",4);
               startActivity(intent);

           }
       });
    }

    private void clickOnGetList() {
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //createAlertDialog(4);
                Intent intent=new Intent(MainPage.this,MainPageNext.class);
                intent.putExtra("btnKey",3);
                startActivity(intent);

            }
        });
    }

    private void clickOnTakeAttendance() {
       button2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //createAlertDialog(2);
               Intent intent=new Intent(MainPage.this,MainPageNext.class);
               intent.putExtra("btnKey",2);
               startActivity(intent);

           }
       });
    }

    private void clickOnAddStudent() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //createAlertDialog(1);
                Intent intent=new Intent(MainPage.this,MainPageNext.class);
                intent.putExtra("btnKey",1);
                startActivity(intent);
            }
        });

    }

    private void getID() {

        button1=findViewById(R.id.addStudent);
        button2=findViewById(R.id.takeAttendance);
        button3=findViewById(R.id.getList);
        button4=findViewById(R.id.specificStudent);
        button5=findViewById(R.id.removeStudent);
        button6=findViewById(R.id.closeApp);
    }

}
