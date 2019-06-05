package com.sm.l3.demo.print;

import android.text.TextUtils;

import com.sm.l3.demo.R;
import com.sm.l3.demo.socket.TransferExtra;

class PrintCodeTicket extends BasePrint {

    private TransferExtra.Bean mPayDetail;

    PrintCodeTicket() {
        super();
    }

    void printCodeTicket(TransferExtra.Bean payDetail, boolean isAgain, int pageNum, BasePrintCallback callback) throws Exception {
        int smallSize = 16;
        int normalSize = 18;
        this.mPayDetail = payDetail;

        mPrinterService.enterPrinterBuffer(true);

        printLogo(mPayDetail.transactionPlatform);

        printAddress(mPayDetail.merchantName, normalSize);

        printLine();

        printUserInfo(normalSize);

        printSeparateLine();

        printOrderInfo(normalSize);

        printSeparateLine();

        // 打印支付类型
        String platform = PrintUtil.getTransactionPlatform(payDetail.transactionPlatform);
        if (payDetail.qrCodeScanModel == 1) {
            platform = getString(R.string.receipt_merchant_scan) + " - " + platform + "\n";
        } else {
            platform = getString(R.string.receipt_user_scan) + " - " + platform + "\n";
        }
        setBold(true);
        mPrinterService.setAlignment(1, null);
        mPrinterService.printTextWithFont(platform, null, normalSize + 2, null);
        mPrinterService.setAlignment(0, null);
        setBold(false);

        printSeparateLine();

        printCode();

        printProtectInfo(smallSize);

        printReprintText(isAgain, normalSize);

        printMerchantOrUser(pageNum, normalSize);

        printLine();

        printFooter(smallSize);

        mPrinterService.lineWrap(4, callback);
        mPrinterService.cutPaper(callback);
        mPrinterService.exitPrinterBufferWithCallback(true, callback);
    }

    private void printOrderInfo(int size) throws Exception {
        String type;
        if (mPayDetail.transactionType == 2) {
            type = getString(R.string.sale_void);
        } else if (mPayDetail.transactionType == 3) {
            type = getString(R.string.refund);
        } else {
            type = getString(R.string.sale);
        }
        type = getString(R.string.receipt_type) + type + "\n";
        setBold(true);
        mPrinterService.printTextWithFont(type, null, size + 10, null);
        setBold(false);

        String voucherNo = getString(R.string.receipt_voucher_number) + mPayDetail.voucherNo + "\n";
        mPrinterService.printTextWithFont(voucherNo, null, size, null);

        String referNo = getString(R.string.receipt_reference_number) + mPayDetail.referenceNo + "\n";
        mPrinterService.printTextWithFont(referNo, null, size, null);

        String thirdTradeNo = getString(R.string.receipt_order_number) + mPayDetail.qrOrderNo + "\n";
        mPrinterService.printTextWithFont(thirdTradeNo, null, size, null);

        String date = getString(R.string.receipt_transaction_time) + getTimeByPayDetail(mPayDetail) + "\n";
        mPrinterService.printTextWithFont(date, null, size, null);

        String amount = MoneyUtil.longCent2DoubleMoneyStr(mPayDetail.amount);
        if (mPayDetail.transactionType == 2) {
            amount = getString(R.string.receipt_revoke_amount) + amount + "\n";
        } else if (mPayDetail.transactionType == 3) {
            amount = getString(R.string.receipt_refund_amount) + amount + "\n";
        } else {
            amount = getString(R.string.receipt_amount) + amount + "\n";
        }
        setBold(true);
        mPrinterService.printTextWithFont(amount, null, size + 10, null);
        setBold(false);
    }

    private void printCode() throws Exception {
        if (mPayDetail.qrOrderNo == null || mPayDetail.qrOrderNo.length() == 0) {
            return;
        }
        printLine(2);
        mPrinterService.setAlignment(1, null);
        mPrinterService.printQRCode(mPayDetail.qrOrderNo, 5, 2, null);
        mPrinterService.setAlignment(0, null);
        printLine(2);
    }

    private void printUserInfo(int size) throws Exception {
        String merchantNo = TextUtils.isEmpty(mPayDetail.merchantId) ? "- -" : mPayDetail.merchantId;
        merchantNo = getString(R.string.receipt_merchant_num) + merchantNo + "\n";
        mPrinterService.printTextWithFont(merchantNo, null, size, null);

        String terminalNo = TextUtils.isEmpty(mPayDetail.terminalId) ? "- -" : mPayDetail.terminalId;
        terminalNo = getString(R.string.receipt_terminal_num) + terminalNo + "\n";
        mPrinterService.printTextWithFont(terminalNo, null, size, null);
    }


}
