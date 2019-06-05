package com.sm.l3.demo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.sunmi.peripheral.printer.InnerPrinterCallback;
import com.sunmi.peripheral.printer.InnerPrinterException;
import com.sunmi.peripheral.printer.InnerPrinterManager;
import com.sunmi.peripheral.printer.SunmiPrinterService;

public class MyApplication extends Application {

    public static Context sContext;
    public static MyApplication sInstance;

    private Handler mainHandler;

    public static SunmiPrinterService sunmiPrinterService;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        sContext = getApplicationContext();
        bindPrintService();
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


    public static MyApplication getInstance() {
        return sInstance;
    }

    private void bindPrintService() {
        try {
            InnerPrinterManager.getInstance().bindService(this, new InnerPrinterCallback() {
                @Override
                protected void onConnected(SunmiPrinterService service) {
                    MyApplication.sunmiPrinterService = service;
                }

                @Override
                protected void onDisconnected() {
                    MyApplication.sunmiPrinterService = null;
                }
            });
        } catch (InnerPrinterException e) {
            e.printStackTrace();
        }
    }

}
