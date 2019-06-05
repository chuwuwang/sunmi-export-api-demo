package com.sm.l3.demo.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.RemoteException;
import android.text.TextUtils;

import com.sm.l3.demo.R;
import com.sunmi.peripheral.printer.ICallback;
import com.sunmi.peripheral.printer.SunmiPrinterService;


public class BasePrint {

    public Context context;

    public SunmiPrinterService printerService;

    public BasePrint(Context context, SunmiPrinterService printerService) {
        this.context = context;
        this.printerService = printerService;
    }

    /**
     * 打印存根信息
     */
    public boolean printMerchantOrUser(int pageNum, boolean isPrintEnglish, int size) throws Exception {
        boolean isMerchant;
        String temp;
        switch (pageNum) {
            case 1: // 商户存根
                isMerchant = true;
                temp = isPrintEnglish ? getString(R.string.bussines_receipt_e) : getString(R.string.bussines_receipt);
                break;
            case 2: // 持卡人存根
                isMerchant = false;
                temp = isPrintEnglish ? getString(R.string.receipt_2_e) : getString(R.string.receipt_2);
                break;
            case 3: // 银行存根
                isMerchant = false;
                temp = isPrintEnglish ? getString(R.string.bank_receipt_e) : getString(R.string.bank_receipt);
                break;
            default: // 默认商户存根
                isMerchant = true;
                temp = isPrintEnglish ? getString(R.string.bussines_receipt_e) : getString(R.string.bussines_receipt);
                break;
        }
        setHeight(0x30);
        printerService.setFontSize(size, null);
        String[] colsTextArr = {
                temp,
                getString(R.string.receipt_keep_receipt)
        };
        int[] colsWidthArr = {35, 15};
        int[] colsAlign = {0, 2};
        printerService.printColumnsString(colsTextArr, colsWidthArr, colsAlign, null);
        setHeight(0x12);
        printSeparateLine();
        return isMerchant;
    }

    // 打印备注
    public void printRemark(boolean isPrintEnglish, int zhSize, int enSize) throws Exception {
        setHeight(0x1A);
        printSeparateLine();
        String temp = getString(R.string.receipt_17);
        printerService.printTextWithFont(temp, "", zhSize, null);
        if (isPrintEnglish) {
            printerService.printTextWithFont("(REFERENCE):\n", "", enSize, null);
        } else {
            printerService.printTextWithFont(":\n", "", enSize, null);
        }
    }

    // 打印原凭证号 or 原参考号
    public void printVoucherOrReferNo(boolean isPrintEnglish, PayDetail payDetail, int zhSize, int enSize) throws Exception {
        // 打印原凭证号 or 原参考号
        // 撤销、预授权完成交易  需打印原凭证号
        // 退货、预授权撤销、预授权完成撤销需打印原参考号信息
        if (payDetail.getTransactionType() == 2 || payDetail.getTransactionType() == 6) {
            String temp = isPrintEnglish ? getString(R.string.pay_voucher_e) : getString(R.string.pay_voucher);
            printerService.printTextWithFont(temp + payDetail.originalPOSNum + "\n", "", zhSize, null);
        } else if (payDetail.getTransactionType() == 3 || payDetail.getTransactionType() == 5 || payDetail.getTransactionType() == 7) {
            String temp = isPrintEnglish ? getString(R.string.original_refer_no_e) : getString(R.string.original_refer_no);
            printerService.printTextWithFont(temp + payDetail.originalReferNo + "\n", "", zhSize, null);
        }
    }

    // 打印重打印文字
    public void printAgainText(boolean isAgain, boolean isPrintEnglish, int size) throws Exception {
        if (isAgain) {
            setBold(true);
            printerService.setAlignment(1, null);
            String againPrint = isPrintEnglish ? getString(R.string.again_print_e) : getString(R.string.again_print);
            printerService.printTextWithFont(againPrint + "\n", "", size, null);
            setBold(false);
            printerService.setAlignment(0, null);
        }
    }

    /**
     * 打印持卡人签名
     */
    public void printCardHolderSign(boolean isPrintEnglish, boolean isMerchant, String jBigHex, String freePWDData, int zhSize, int enSize) throws Exception {
        printSeparateLine();
        if (isMerchant) {
            // 打印签名栏
            String temp = getString(R.string.receipt_18);
            printerService.printTextWithFont(temp, "", zhSize, null);
            if (isPrintEnglish) {
                printerService.printTextWithFont("(CARDHOLDER SIGNATURE):\n", "", enSize, null);
            } else {
                printerService.printTextWithFont(":\n", "", enSize, null);
            }
            if (jBigHex != null && jBigHex.length() > 0) {
//                JbigCodec jbigCodec = JbigCodecFactory.getJbigCodec(JbigCodecFactory.CODEC.JNI_CODEC);
//                byte[] bytes = Utils.hexStr2Bytes(jBigHex);
//                Bitmap[] bitmaps = jbigCodec.decode(bytes);
//                Bitmap sign = bitmaps[0];
//                sign = BitmapUtils.replaceBitmapColor(sign, Color.TRANSPARENT, Color.WHITE);
//                printerService.setAlignment(1, null);
//                printerService.printBitmap(sign, null);
//                printerService.setAlignment(0, null);
            } else {
                printerService.lineWrap(2, null);
            }
            printSeparateLine();
        }
        // 打印免密标识
        if (freePWDData != null && freePWDData.length() > 0) {
            printerService.printTextWithFont(freePWDData + "\n", "", zhSize, null);
            printSeparateLine();
        }
    }

    // 打印本人确认以上交易同意记入本卡帐户
    public void printReceipt(boolean isPrintEnglish, int zhSize, int enSize) throws Exception {
        String temp = getString(R.string.receipt_19);
        printerService.printTextWithFont(temp + "\n", "", zhSize, null);
        if (isPrintEnglish) {
            setHeight(0x12);
            printerService.printTextWithFont("I ACKNOWLEDGE SATISFACTORY RECEIPT OF \nRELATIVE GOODS/SERVICES\n", "", enSize, null);
        }
    }

    // 打印服务热线和网址
    public void printServerAndWeb(boolean isPrintEnglish, int zhSize, int enSize) throws Exception {
        // 是否打印渠道信息
            // 打印服务热线
            String hotLine = "021-61079307";
            if (hotLine != null && hotLine.trim().length() > 0) {
                String temp = getString(R.string.hotLine);
                printerService.printTextWithFont(temp, "", zhSize, null);
                // 打印英文的服务热线
                if (isPrintEnglish) {
                    printerService.printTextWithFont("(CUSTOMER SERVICE HOT LINE):", "", enSize, null);
                } else {
                    printerService.printTextWithFont(":", "", zhSize, null);
                }
                temp = TextUtils.isEmpty(hotLine) ? "" : hotLine;
                printerService.printTextWithFont(temp + "\n", "", zhSize, null);
            }
            // 打印网址
            String website = "www.sunmi.com";
            if (website != null && website.trim().length() > 0) {
                printerService.printTextWithFont(website + "\n", "", enSize, null);
            }
    }

    public void printFooter(ICallback callback, int size, String info) throws RemoteException {
        printerService.printTextWithFont("\n", "", size, callback);
//        printerService.printTextWithFont(info + "\n", "", size, null);
        printerService.printerInit(null);

        printerService.cutPaper(null);
    }

    /**
     * 打印分隔线
     */
    public void printSeparateLine() throws Exception {
        printerService.setAlignment(1, null);
        printerService.printTextWithFont("-----------------------------------------------\n", "", 24, null);
        printerService.setAlignment(0, null);
    }

    /**
     * 获取设备名称、AP版本号、设配SN号
     */
    private String getNameVersionSn() {
        StringBuilder sbAboutInfo = new StringBuilder();
        sbAboutInfo
                .append("Sunmi-" + Build.MODEL)
                .append("  ")
                .append("v1.5.66")
                .append("  ")
                .append("T208D94L40174");
        return sbAboutInfo.toString();
    }

    /**
     * 是否设置字体加粗
     */
    public void setBold(boolean dist) throws RemoteException {
        byte[] returnText = new byte[3]; // 加粗 ESC E
        returnText[0] = 0x1B;
        returnText[1] = 0x45;
        if (dist) {
            returnText[2] = 0x01; // 表示加粗
        } else {
            returnText[2] = 0x00;
        }
        printerService.sendRAWData(returnText, null);
    }

    /**
     * 设置字体行高
     */
    public void setHeight(int height) throws RemoteException {
        byte[] returnText = new byte[3];
        returnText[0] = 0x1B;
        returnText[1] = 0x33;
        returnText[2] = (byte) height;
        printerService.sendRAWData(returnText, null);
    }

    public String getString(int id) {
        return context.getString(id);
    }

    public String getString(int id, Object... formatArgs) {
        return context.getString(id, formatArgs);
    }

}
