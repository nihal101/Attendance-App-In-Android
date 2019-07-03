package com.singh.nihal.masterkit;

import android.app.Activity;

public class MyController {

    private static MyController instance;
    private Activity activity1, activity2,activity3,activity4;

    MyController()
    {}

    public static synchronized MyController getInstance()
    {
        if(instance==null)
            instance=new MyController();
        return instance;
    }

    public void setActivity1(Activity activity1Object){activity1=activity1Object;}
    public void setActivity2(Activity activity2Object){activity2=activity2Object;}
    public void setActivity3(Activity activity3Object){activity3=activity3Object;}
    public void setActivity4(Activity activity4Object){activity4=activity4Object;}

    public void closeAllAcitvity()
    {
        if(activity1!=null)
          activity1.finish();
        //if(activity2!=null)
           // activity2.finish();
        if(activity3!=null)
            activity3.finish();
        if(activity4!=null)
            activity4.finish();
    }
    public void closeAllActivity1()
    {
        if(activity1!=null)
            activity1.finish();
        if(activity2!=null)
           activity2.finish();
        if(activity3!=null)
            activity3.finish();
        if(activity4!=null)
            activity4.finish();
    }
}
