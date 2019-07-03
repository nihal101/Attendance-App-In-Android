package com.singh.nihal.masterkit;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainPageNext extends AppCompatActivity {

    private String className="";
    private StudentAddFragment studentAddFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_next);
        MyController.getInstance().setActivity1(this);

        Intent intent=getIntent();
        int btnKey=intent.getIntExtra("btnKey",0);

        createAlertDialog(btnKey);


    }

    @Override
    public void onBackPressed() {
        MyController.getInstance().closeAllAcitvity();
        super.onBackPressed();
    }

    private void createAlertDialog(final int btn)
    {
        final String [] items={"CSE","ME","EEE","ECE"};
        AlertDialog.Builder al=new AlertDialog.Builder(this);
        al.setTitle("Choose Class");
        al.setCancelable(false);

        al.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                className=items[i];
                createFragment(btn,className);

            }
        });

        al.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                startActivity(new Intent(MainPageNext.this,MainPage.class));
            }
        });

        al.show();
    }


    private void createFragment(int btn,String className)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if(btn==1)
        {
            studentAddFragment=new StudentAddFragment(className);
            fragmentTransaction.add(R.id.framelayout,studentAddFragment);
            fragmentTransaction.commit();
        }else if(btn==3)
        {
            fragmentTransaction.add(R.id.framelayout,new GetAllDataFragment(className));
            fragmentTransaction.commit();
        }else if(btn==2)
        {
           fragmentTransaction.add(R.id.framelayout,new TakeAttendanceFragment(className));
           fragmentTransaction.commit();
        }else if(btn==4)
        {
            fragmentTransaction.add(R.id.framelayout,new GetAttendanceFragment(className));
            fragmentTransaction.commit();
        }
    }
}
