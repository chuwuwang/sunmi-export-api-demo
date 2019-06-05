package com.sm.l3.demo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class MyApplication extends Application {

    public static Context sContext;
    public static MyApplication sInstance;

    private Handler mainHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        sContext = getApplicationContext();

        Looper mainLooper = Looper.getMainLooper();
        mainHandler = new Handler(mainLooper);
    }

    public void post(Runnable run) {
        if (mainHandler == null) return;
        mainHandler.post(run);
    }

    public Handler getMainHandler() {
        if (mainHandler == null) {
            Looper mainLooper = Looper.getMainLooper();
            mainHandler = new Handler(mainLooper);
        }
        return mainHandler;
    }


}
