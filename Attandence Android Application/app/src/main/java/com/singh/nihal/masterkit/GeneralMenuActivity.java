package com.singh.nihal.masterkit;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class GeneralMenuActivity extends AppCompatActivity {


   private FragmentTransaction fragmentTransaction;
   private FragmentManager fragmentManager;
   private LinearLayout developerProfile;
   private ScrollView termProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_menu);
        MyController.getInstance().setActivity4(this);

        developerProfile=findViewById(R.id.aboutdeveloper);
        developerProfile.setVisibility(View.GONE);
        termProfile=findViewById(R.id.termcondition);
        termProfile.setVisibility(View.GONE);

        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        createFragment(title);



    }

    @Override
    public void onBackPressed() {
        MyController.getInstance().closeAllAcitvity();
        super.onBackPressed();
    }

    private void createFragment(String title)
    {
        switch(title)
        {
            case "userInformation":
                createProfileFragment();
                actionBarTitle(title);
                break;

            case "AboutDeveloper":
                developerProfile.setVisibility(View.VISIBLE);
                actionBarTitle(title);
                break;

            case "Term Condition":
                termProfile.setVisibility(View.VISIBLE);
                actionBarTitle(title);
                break;

            default:
                Toast.makeText(this, "something gone error", Toast.LENGTH_SHORT).show();
        }
    }

    private void createProfileFragment()
    {
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout,new ProfileFragment());
        fragmentTransaction.commit();
    }

    private void actionBarTitle(String title)
    {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(title);
    }
}
