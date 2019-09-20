package com.sm.l3.demo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.sm.l3.demo.socket.UsbDeviceService;
import com.sunmi.device.comm.aidl.DeviceCommOptV2;
import com.sunmi.peripheral.printer.InnerPrinterCallback;
import com.sunmi.peripheral.printer.InnerPrinterException;
import com.sunmi.peripheral.printer.InnerPrinterManager;
import com.sunmi.peripheral.printer.SunmiPrinterService;

import sunmi.paylib.SunmiPayKernel;

public class MyApplication extends Application {

    public static Context sContext;

    public static MyApplication sInstance;

    private Handler mainHandler;

    private SunmiPayKernel sunmiPayKernel;

    public static DeviceCommOptV2 mDeviceCommOptV2;
    public static SunmiPrinterService sunmiPrinterService;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        sContext = getApplicationContext();

        bindPrintService();

        connectPayService();

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

    private void connectPayService() {
        sunmiPayKernel = SunmiPayKernel.getInstance();
        sunmiPayKernel.initPaySDK(this, mConnectCallback);
    }

    private SunmiPayKernel.ConnectCallback mConnectCallback = new SunmiPayKernel.ConnectCallback() {

        @Override
        public void onConnectPaySDK() {
            try {
                MyApplication.mDeviceCommOptV2 = sunmiPayKernel.mDeviceCommOptV2;

                UsbDeviceService.startService(sContext);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onDisconnectPaySDK() {

        }

    };

    private void bindPrintService() {
        try {
            InnerPrinterManager.getInstance().bindService(this,
                    new InnerPrinterCallback() {

                        @Override
                        protected void onConnected(SunmiPrinterService service) {
                            MyApplication.sunmiPrinterService = service;
                        }

                        @Override
                        protected void onDisconnected() {
                            MyApplication.sunmiPrinterService = null;
                        }

                    }
            );
        } catch (InnerPrinterException e) {
            e.printStackTrace();
        }
    }

    public static MyApplication getInstance() {
        return sInstance;
    }


}
