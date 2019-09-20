package com.sm.l3.demo.socket;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.sm.l3.demo.MyApplication;
import com.sm.l3.demo.ResultActivity;
import com.sm.l3.demo.util.LogUtil;
import com.sunmi.device.comm.aidl.DeviceCommCallbackV2;

public class UsbDeviceService extends Service {

    private static final String TAG = "UsbDeviceService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        restartConnect();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            boolean isEnabled = isCommEnabled();
            if (isEnabled) {
                LogUtil.e(TAG, "已连接");
                mHandler.removeCallbacksAndMessages(null);
            } else {
                initUsbDevice();
                mHandler.removeCallbacksAndMessages(null);
                mHandler.sendEmptyMessageDelayed(0, 2000);
            }
        }

    };

    private void restartConnect() {
        boolean isEnabled = isCommEnabled();
        if (isEnabled == false) {
            initUsbDevice();
        }
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    private boolean isCommEnabled() {
        boolean isEnabled = false;
        try {
            isEnabled = MyApplication.mDeviceCommOptV2.isCommEnabled(0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return isEnabled;
    }

    private void initUsbDevice() {
        LogUtil.e(TAG, "initUsbDevice");
        try {
            Bundle config = new Bundle();
            MyApplication.mDeviceCommOptV2.openComm(0, config, deviceCommCallbackV2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DeviceCommCallbackV2 deviceCommCallbackV2 = new DeviceCommCallbackV2.Stub() {

        @Override
        public void onCommOpen(int commType, int code, String msg) throws RemoteException {
            LogUtil.e(TAG, "onCommOpen commType:" + commType + ",code:" + code + ",msg:" + msg);
        }

        @Override
        public void onSendData(int commType, int code, String msg) throws RemoteException {
            LogUtil.e(TAG, "onSendData commType:" + commType + ",code:" + code + ",msg:" + msg);
        }

        @Override
        public void onReceiveData(int commType, int code, byte[] data) throws RemoteException {
            if (data != null && data.length > 0) {
                String message = new String(data);
                LogUtil.e(TAG, "onReceiveData commType:" + commType + ",code:" + code + ",data: " + message);

                TransferExtra bean = new Gson().fromJson(message, TransferExtra.class);

                Intent intent = new Intent(MyApplication.sContext, ResultActivity.class);

                intent.putExtra("model", bean.model);
                intent.putExtra("version", bean.version);
                intent.putExtra("transType", bean.transType);
                intent.putExtra("packageName", bean.packageName);

                intent.putExtra("terminalId", bean.terminalId);
                intent.putExtra("merchantId", bean.merchantId);
                intent.putExtra("merchantName", bean.merchantName);

                intent.putExtra("transDate", bean.transDate);
                intent.putExtra("transTime", bean.transTime);
                intent.putExtra("operatorId", bean.operatorId);
                intent.putExtra("paymentType", bean.paymentType);

                intent.putExtra("errorMsg", bean.errorMsg);
                intent.putExtra("errorCode", bean.errorCode);
                intent.putExtra("resultCode", bean.resultCode);

                intent.putExtra("amount", bean.amount);

                intent.putExtra("authNo", bean.authNo);
                intent.putExtra("batchNo", bean.batchNo);
                intent.putExtra("transId", bean.transId);
                intent.putExtra("voucherNo", bean.voucherNo);
                intent.putExtra("voucherNo", bean.voucherNo);
                intent.putExtra("qrOrderNo", bean.qrOrderNo);
                intent.putExtra("referenceNo", bean.referenceNo);

                intent.putExtra("issue", bean.issue);
                intent.putExtra("cardNo", bean.cardNo);
                intent.putExtra("acquire", bean.acquire);
                intent.putExtra("cardType", bean.cardType);
                intent.putExtra("accountType", bean.accountType);

                intent.putExtra("answerCode", bean.answerCode);
                intent.putExtra("transactionType", bean.transactionType);
                intent.putExtra("transactionPlatform", bean.transactionPlatform);

                intent.putExtra("qrCodeScanModel", bean.qrCodeScanModel);
                intent.putExtra("qrCodeTransactionState", bean.qrCodeTransactionState);

                intent.putExtra("merchantNameEn", bean.merchantNameEn);

                intent.putExtra("balance", bean.balance);

                intent.putExtra("transNum", bean.transNum);
                intent.putExtra("totalAmount", bean.totalAmount);

                intent.putExtra("settleJson", bean.settleJson);

                intent.putExtra("bean", bean);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.sContext.startActivity(intent);
            } else {
                LogUtil.e(TAG, "onReceiveData commType:" + commType + ",code:" + code);
            }
        }

        @Override
        public void onCommClosed(int commType, int code, String msg) throws RemoteException {
            LogUtil.e(TAG, "onCommClosed commType:" + commType + ",code:" + code + ",msg:" + msg);
            restartConnect();
        }

    };

    public static void send(String data) {
        try {
            byte[] bytes = data.getBytes();
            MyApplication.mDeviceCommOptV2.sendData(0, bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startService(Context context) {
        Intent intent = new Intent(context, UsbDeviceService.class);
        context.startService(intent);
    }


}
