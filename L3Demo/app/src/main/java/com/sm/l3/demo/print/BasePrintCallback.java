package com.sm.l3.demo.print;

import android.os.RemoteException;

import com.sunmi.peripheral.printer.InnerResultCallbcak;

public class BasePrintCallback extends InnerResultCallbcak.Stub {

    @Override
    public void onRunResult(boolean isSuccess) throws RemoteException {

    }

    @Override
    public void onReturnString(String result) throws RemoteException {

    }

    @Override
    public void onRaiseException(int code, String msg) throws RemoteException {

    }

    @Override
    public void onPrintResult(int code, String msg) throws RemoteException {

    }

}
