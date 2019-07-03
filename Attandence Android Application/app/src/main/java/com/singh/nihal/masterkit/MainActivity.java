package com.singh.nihal.masterkit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView creteuser,forgotPassward,user,branch;
    EditText username,passward;
    private String userNameFromLogin,passwardFromLogin,userNameFromDatabase,passwardFromDatabase;
    Button logIn;
    ImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyController.getInstance().setActivity3(this);

        ActionBar ac=getSupportActionBar();
        ac.setTitle("Login...");

        username=findViewById(R.id.username);
        passward=findViewById(R.id.passowrd);
        logIn=findViewById(R.id.login);
        forgotPassward=findViewById(R.id.forgotPasswod);
        userImage=findViewById(R.id.userImage);
        user=findViewById(R.id.user);
        branch=findViewById(R.id.branch);

       SharedPreferences sharedPreferences=getSharedPreferences("CreateUser",Context.MODE_PRIVATE);
       int user1=sharedPreferences.getInt("User",0);
       if(user1!=0)
       {
           String name=getDataUserName();
           if(name!=null)
              user.setText(name);
           String branch1=getDataUserBranch();
           if(branch1!=null)
               branch.setText(branch1);

       }

        creteuser = findViewById(R.id.createnewaccount);
        clickOnTextView();
        clickOnLogIn();
        clickOnImage();
        clickOnForgotPassword();
    }

    private void clickOnForgotPassword() {
        forgotPassward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SharedPreferences sharedPreferences=getSharedPreferences("CreateUser",Context.MODE_PRIVATE);
               int user=sharedPreferences.getInt("User",0);
               if(user==0)
                   createAlertDialog("Error...","First create user");
               else
               {
                   DatabaseClass databaseClass=new DatabaseClass(getBaseContext());
                   Cursor cursor=databaseClass.getAllData();
                   cursor.moveToNext();
                   String pass=cursor.getString(3);
                   Toast.makeText(MainActivity.this, "Your Password : "+pass, Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    private void clickOnImage()
    {
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent=   new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        Uri uri=data.getData();
        Bitmap bitmap;
        try{
            bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
            BitmapDrawable bitmapDrawable=new BitmapDrawable(getResources(),bitmap);
            userImage.setImageDrawable(bitmapDrawable);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        MyController.getInstance().closeAllAcitvity();
        super.onBackPressed();
    }

    private void clickOnTextView() {

        creteuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences=getBaseContext().getSharedPreferences("CreateUser",Context.MODE_PRIVATE);
                int user=sharedPreferences.getInt("User",0);

                if(user==0) {
                    Intent intent = new Intent(MainActivity.this, Newuser.class);
                    startActivity(intent);
                }else
                {
                    createAlertDialog("Error...","Maximum one user can be created");
                }

            }
        });
    }

    private void createAlertDialog(String title,String sms) {

        AlertDialog.Builder al=new AlertDialog.Builder(this);
        al.setTitle(title);
        al.setCancelable(false);
        al.setMessage(sms);
        al.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        al.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.personinformation,menu);
        return true;
    }

    private void getDataFromLogin()
    {
        userNameFromLogin=username.getText().toString();
        passwardFromLogin=passward.getText().toString();
    }

    private void getDataFromDatabase()
    {
        DatabaseClass databaseClass=new DatabaseClass(getApplicationContext());
        Cursor cursor=databaseClass.getAllData();
        if(cursor.moveToNext())
        {
            userNameFromDatabase=cursor.getString(0).toString();
            passwardFromDatabase=cursor.getString(3).toString();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.person:
               SharedPreferences sharedPreferences=getBaseContext().getSharedPreferences("CreateUser",Context.MODE_PRIVATE);
               int user=sharedPreferences.getInt("User",0);
               if(user==1)
                   createNewAcivity((String)item.getTitle());
               else
                   createAlertDialog("Error...","No user created...");
                return  true;

            case R.id.aboutdeveloper:
                createNewAcivity((String)item.getTitle());
                return true;

            case R.id.termcondition:
                createNewAcivity((String)item.getTitle());
                return true;

            default:
                return onOptionsItemSelected(item);
        }
    }

    private void createNewAcivity(String title)
    {
        Intent intent=new Intent(this,GeneralMenuActivity.class);
        intent.putExtra("title",title);
        startActivity(intent);
    }

    private void clickOnLogIn()
    {

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SharedPreferences sharedPreferences=getBaseContext().getSharedPreferences("CreateUser",Context.MODE_PRIVATE);
               int user=sharedPreferences.getInt("User",0);

               if(user==1)
               {
                   getDataFromLogin();
                   getDataFromDatabase();
                   if(userNameFromLogin.equals(userNameFromDatabase)&&passwardFromLogin.equals(passwardFromDatabase))
                   {
                       startActivity(new Intent(MainActivity.this, MainPage.class));
                   }else
                   {
                       createAlertDialog("Error...","username and passward must be correct");
                   }
               }else
               {
                   createAlertDialog("Error...","Fisrt create user");
               }
            }
        });
    }

     private String getDataUserName()
     {
         DatabaseClass databaseClass=new DatabaseClass(getBaseContext());
         Cursor cursor=databaseClass.getAllData();
         cursor.moveToNext();
         String name=cursor.getString(1);
         return  name;
     }

     private String getDataUserBranch()
     {
         DatabaseClass databaseClass=new DatabaseClass(getBaseContext());
         Cursor cursor=databaseClass.getAllData();
         cursor.moveToNext();
         String branch=cursor.getString(2);
         return  branch;
     }

}
