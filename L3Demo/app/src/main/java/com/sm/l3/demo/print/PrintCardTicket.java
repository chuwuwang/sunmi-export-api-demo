package com.sm.l3.demo.print;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;

import com.sm.l3.demo.R;
import com.sm.l3.demo.socket.TransferExtra;
import com.sm.l3.demo.util.BitmapUtil;
import com.sm.l3.demo.util.ByteUtil;

import io.github.suzp1984.jbig.JbigCodec;
import io.github.suzp1984.jbig.JbigCodecFactory;

class PrintCardTicket extends BasePrint {

    private TransferExtra mPayDetail;

    PrintCardTicket() {
        super();
    }

    void printCardTicket(TransferExtra payDetail, BasePrintCallback callback, int pageNum, boolean isAgain) throws Exception {
        int smallSize = 16;
        int normalSize = 18;
        this.mPayDetail = payDetail;

        mPrinterService.enterPrinterBuffer(true);

        printLogo(mPayDetail.transactionPlatform);

        printAddress(mPayDetail.merchantName, normalSize);

        printLine();

        printUserInfo(normalSize);

        printCardInfo(normalSize);

        printOrderInfo(normalSize);

        printIcData(smallSize);

        printSignature(pageNum != 2, smallSize, normalSize);

        printLine();

        printProtectInfo(smallSize);

        printReprintText(isAgain, normalSize);

        printMerchantOrUser(pageNum, normalSize);

        printLine();

        printFooter(smallSize);

        mPrinterService.lineWrap(4, callback);
        mPrinterService.cutPaper(callback);
        mPrinterService.exitPrinterBufferWithCallback(true, callback);
    }

    private void printUserInfo(int size) throws Exception {
        String merchantNo = TextUtils.isEmpty(mPayDetail.merchantId) ? "- -" : mPayDetail.merchantId;
        merchantNo = getString(R.string.receipt_merchant_num) + merchantNo + "\n";
        mPrinterService.printTextWithFont(merchantNo, null, size, null);

        String terminalNo = TextUtils.isEmpty(mPayDetail.terminalId) ? "- -" : mPayDetail.terminalId;
        terminalNo = getString(R.string.receipt_terminal_num) + terminalNo + "\n";
        mPrinterService.printTextWithFont(terminalNo, null, size, null);
    }

    private void printCardInfo(int size) throws Exception {
        String temp = PrintUtil.getTransactionType(mPayDetail.transactionType) + "\n";
        setBold(true);
        mPrinterService.setAlignment(1, null);
        mPrinterService.printTextWithFont(temp, "", size + 10, null);
        mPrinterService.setAlignment(0, null);
        setBold(false);

        temp = "**** **** **** " + mPayDetail.cardNo.substring(mPayDetail.cardNo.length() - 4);
        switch (mPayDetail.cardType) {
            case "MAG":
                temp += "（S）";
                break;
            case "IC":
                temp += "（C）";
                break;
            case "NFC":
                temp += "（W）";
                break;
            default:
                temp += "（M）";
                break;
        }
        temp += "\n";
        setBold(true);
        mPrinterService.setAlignment(1, null);
        mPrinterService.printTextWithFont(temp, "", size + 10, null);
        mPrinterService.setAlignment(0, null);
        setBold(false);
    }

    private void printOrderInfo(int size) throws Exception {
        String batchNo = getString(R.string.receipt_batch_number) + mPayDetail.batchNo + "\n";
        mPrinterService.printTextWithFont(batchNo, null, size, null);

        String voucherNo = getString(R.string.receipt_voucher_number) + mPayDetail.voucherNo + "\n";
        mPrinterService.printTextWithFont(voucherNo, null, size, null);

        if (mPayDetail.authNo != null && mPayDetail.authNo.trim().length() > 0) {
            String authNo = getString(R.string.receipt_authorize_number) + mPayDetail.authNo + "\n";
            mPrinterService.printTextWithFont(authNo, null, size, null);
        }

        String referNo = getString(R.string.receipt_reference_number) + mPayDetail.referenceNo + "\n";
        mPrinterService.printTextWithFont(referNo, null, size, null);

        String transDate = TextUtils.isEmpty(mPayDetail.transDate) ? "- -" : mPayDetail.transDate.substring(0, 2) + "/" + mPayDetail.transDate.substring(2, 4);
        String transTime = TextUtils.isEmpty(mPayDetail.transTime) ? "- -" : mPayDetail.transTime.substring(0, 2) + ":" + mPayDetail.transTime.substring(2, 4) + ":" + mPayDetail.transTime.substring(4, 6);
        String date = getString(R.string.receipt_transaction_time) + SystemDateTime.getYYYY() + "/" + transDate + " " + transTime + "\n";
        mPrinterService.printTextWithFont(date, "", size, null);

        printLine();

        String amount = MoneyUtil.longCent2DoubleMoneyStr(mPayDetail.amount);
        if (mPayDetail.transactionType == 2 || mPayDetail.transactionType == 5 || mPayDetail.transactionType == 7) {
            amount = getString(R.string.receipt_revoke_amount) + amount + "\n";
        } else if (mPayDetail.transactionType == 3) {
            amount = getString(R.string.receipt_refund_amount) + amount + "\n";
        } else {
            amount = getString(R.string.receipt_amount) + amount + "\n";
        }
        setBold(true);
        mPrinterService.setAlignment(1, null);
        mPrinterService.printTextWithFont(amount, "", size + 8, null);
        mPrinterService.setAlignment(0, null);
        setBold(false);
    }

    private void printIcData(int size) throws Exception {
        boolean bool = TextUtils.equals(mPayDetail.cardType, "NFC") && TextUtils.equals(mPayDetail.cardType, "IC");
        if (bool) {
            return;
        }

        String temp = "TSI：F800 \n";
        mPrinterService.printTextWithFont(temp, null, size, null);

        temp = "TVR：0080047000 \n";
        mPrinterService.printTextWithFont(temp, null, size, null);

        temp = "TC：261B5B2850F5B9AD \n";
        mPrinterService.printTextWithFont(temp, null, size, null);

        temp = "AID：A000000333010101 \n";
        mPrinterService.printTextWithFont(temp, null, size, null);

        temp = "APP LABEL：P B O C Debit \n";
        mPrinterService.printTextWithFont(temp, null, size, null);
    }

    private void printSignature(boolean merchant, int smallSize, int normalSize) throws Exception {
        if (mPayDetail.amount < 100000 && mPayDetail.signHexData == null && mPayDetail.signHexData.trim().length() == 0) {
            printPIN(normalSize);
        } else {
            printSignature(merchant, smallSize);
        }
    }

    private void printPIN(int normalSize) throws Exception {
        printLine();

        setBold(true);
        mPrinterService.setAlignment(1, null);

        String temp = getString(R.string.receipt_no_pin) + "\n";
        mPrinterService.printTextWithFont(temp, "", normalSize + 8, null);

        temp = getString(R.string.receipt_no_signature) + "\n";
        mPrinterService.printTextWithFont(temp, "", normalSize + 8, null);

        mPrinterService.setAlignment(0, null);
        setBold(false);

        printLine();
    }

    private void printSignature(boolean merchant, int smallSize) throws Exception {
        if (merchant == false) {
            printLine();
            return;
        }

        printSeparateLine();

        String temp = getString(R.string.receipt_card_holder_sign) + "\n";
        mPrinterService.printTextWithFont(temp, "", smallSize, null);

        if (mPayDetail.signHexData != null && mPayDetail.signHexData.trim().length() > 0) {
            JbigCodec jbigCodec = JbigCodecFactory.getJbigCodec(JbigCodecFactory.CODEC.JNI_CODEC);
            byte[] bytes = ByteUtil.hexStr2Bytes(mPayDetail.signHexData);
            Bitmap[] bitmaps = jbigCodec.decode(bytes);
            Bitmap sign = bitmaps[0];
            sign = BitmapUtil.replaceBitmapColor(sign, Color.TRANSPARENT, Color.WHITE);
            mPrinterService.setAlignment(1, null);
            mPrinterService.printBitmap(sign, null);
            mPrinterService.setAlignment(0, null);
            printLine();
        } else {
            printLine(2);
        }

        printSeparateLine();
    }


}
