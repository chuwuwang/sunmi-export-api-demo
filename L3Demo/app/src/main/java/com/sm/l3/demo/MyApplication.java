package com.sm.l3.demo;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
    }



}
