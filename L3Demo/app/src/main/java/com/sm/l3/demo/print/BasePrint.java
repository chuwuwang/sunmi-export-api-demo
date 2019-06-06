package com.sm.l3.demo.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;

import com.sm.l3.demo.MyApplication;
import com.sm.l3.demo.R;
import com.sm.l3.demo.socket.TransferExtra;
import com.sunmi.peripheral.printer.SunmiPrinterService;

class BasePrint {

    private static final int DEFAULT_SIZE = 24;

    private Context mContext;
    SunmiPrinterService mPrinterService;

    BasePrint() {
        mContext = MyApplication.sInstance;
        mPrinterService = MyApplication.sunmiPrinterService;
    }

    void printLogo(int platform) throws Exception {
        setHeight(0X16);

        Bitmap bitmap = PrintUtil.getReceiptTitleIcon(platform);
        mPrinterService.setAlignment(1, null);
        mPrinterService.printBitmap(bitmap, null);
        mPrinterService.setAlignment(0, null);

        printLine(2);
    }

    void printMerchantOrUser(int pageNum, int size) throws Exception {
        String temp;
        if (pageNum == 2) {
            temp = getString(R.string.receipt_stub_card_holder);
        } else {
            temp = getString(R.string.receipt_stub_merchant);
        }
        temp = "****  " + temp + "  ****\n";
        mPrinterService.setAlignment(1, null);
        mPrinterService.printTextWithFont(temp, null, size, null);
        mPrinterService.setAlignment(0, null);
    }

    void printReprintText(boolean isAgain, int size) throws Exception {
        if (isAgain) {
            String temp = getString(R.string.receipt_again_print) + "\n";
            setBold(true);
            mPrinterService.setAlignment(1, null);
            mPrinterService.printTextWithFont(temp, null, size, null);
            mPrinterService.setAlignment(0, null);
            setBold(false);
        }
    }

    void printProtectInfo(int size) throws Exception {
        mPrinterService.setAlignment(1, null);
        String temp = getString(R.string.receipt_agree_transaction) + "\n";
        mPrinterService.printTextWithFont(temp, null, size, null);
        mPrinterService.setAlignment(0, null);

        printLine();
    }

    void printFooter(int size) throws Exception {
        String temp = "power by z h o u \n";
        mPrinterService.setAlignment(1, null);
        mPrinterService.printTextWithFont(temp, null, size, null);
        mPrinterService.setAlignment(0, null);
        mPrinterService.printerInit(null);
    }

    void printAddress(String merchantName, int size) throws Exception {
        String name = merchantName + "\n";
        setBold(true);
        mPrinterService.setAlignment(1, null);
        mPrinterService.printTextWithFont(name, null, size + 4, null);
        mPrinterService.setAlignment(0, null);
        setBold(false);
    }

    void printLine() throws Exception {
        mPrinterService.lineWrap(1, null);
    }

    void printLine(int line) throws Exception {
        mPrinterService.lineWrap(line, null);
    }

    void printSeparateLine() throws Exception {
        mPrinterService.setAlignment(1, null);
        mPrinterService.printTextWithFont("--------------------------------\n", "", DEFAULT_SIZE, null);
        mPrinterService.setAlignment(0, null);
    }

    void setBold(boolean isBold) throws RemoteException {
        byte[] returnText = new byte[3];
        returnText[0] = 0x1B;
        returnText[1] = 0x45;
        if (isBold) {
            returnText[2] = 0x01; // 表示加粗
        } else {
            returnText[2] = 0x00;
        }
        mPrinterService.sendRAWData(returnText, null);
    }

    void setHeight(int height) throws RemoteException {
        byte[] returnText = new byte[3];
        returnText[0] = 0x1B;
        returnText[1] = 0x33;
        returnText[2] = (byte) height;
        mPrinterService.sendRAWData(returnText, null);
    }

    String getTimeByPayDetail(TransferExtra.Bean payDetail) {
        String date = payDetail.transDate;
        String transDate = " ";
        if (date != null && date.length() >= 4) {
            transDate = date.substring(0, 2) + "/" + date.substring(2, 4);
        }
        String time = payDetail.transTime;
        String transTime = " ";
        if (time != null && time.length() >= 6) {
            transTime = payDetail.transTime.substring(0, 2) + ":" + payDetail.transTime.substring(2, 4) + ":" + payDetail.transTime.substring(4, 6);
        }
        return SystemDateTime.getYYYY() + "/" + transDate + " " + transTime;
    }

    String getString(int id) {
        return mContext.getString(id);
    }

    String getString(int id, Object... formatArgs) {
        return mContext.getString(id, formatArgs);
    }


}
