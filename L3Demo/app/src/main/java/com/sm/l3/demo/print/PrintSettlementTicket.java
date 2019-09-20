package com.sm.l3.demo.print;

import android.text.TextUtils;

import com.sm.l3.demo.R;
import com.sm.l3.demo.socket.Settlement;
import com.sm.l3.demo.socket.SumTotal;
import com.sm.l3.demo.socket.TransferExtra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PrintSettlementTicket extends BasePrint {

    private int[] mColsWidth = {
            14, 12, 13
    };
    private int[] mColsAlign = {
            0, 1, 1
    };

    public PrintSettlementTicket() {
        super();
    }

    public void printSettlementSummary(TransferExtra bean, List<Settlement> list, BasePrintCallback callback) throws Exception {
        int smallSize = 18;
        int normalSize = 20;

        mPrinterService.enterPrinterBuffer(true);

        printTitle(bean, true, normalSize);

        printSeparateLine();

        String[] colsTextArr = {
                getString(R.string.receipt_settlement_summary_type),
                getString(R.string.receipt_settlement_summary_pens),
                getString(R.string.receipt_settlement_summary_amount)
        };
        mPrinterService.setFontSize(smallSize, null);
        mPrinterService.printColumnsString(colsTextArr, mColsWidth, mColsAlign, null);

        printCardList(list, smallSize);

        printCodeList(list, smallSize);

        printSeparateLine();

        printTotal(bean, smallSize);

        printLine();

        printFooter(smallSize);

        mPrinterService.lineWrap(4, callback);
        mPrinterService.cutPaper(callback);
        mPrinterService.exitPrinterBufferWithCallback(true, callback);
    }

    public void printSettlementDetail(TransferExtra bean, List<Settlement> list, BasePrintCallback callback) throws Exception {
        int smallSize = 18;
        int normalSize = 20;

        mPrinterService.enterPrinterBuffer(true);

        printTitle(bean, false, normalSize);

        printSeparateLine();

        String[] colsTextArr = {
                getString(R.string.receipt_settlement_detail_voucher_num),
                getString(R.string.receipt_settlement_detail_type),
                getString(R.string.receipt_settlement_detail_card_num),
                getString(R.string.receipt_settlement_detail_amount)
        };
        int[] colsWidth = {
                2, 2, 4, 2
        };
        int[] colsAlign = {
                1, 1, 1, 1
        };
        mPrinterService.setFontSize(smallSize, null);
        mPrinterService.printColumnsString(colsTextArr, colsWidth, colsAlign, null);

        if (list != null && list.size() > 0) {

            printSeparateLine();

            for (int i = 0; i < list.size(); i++) {
                Settlement item = list.get(i);
                String number;
                if (item.transactionPlatform == 0) {
                    number = intercept(item.cardNo);
                } else {
                    number = item.qrOrderNo;
                }
                String[] text = {
                        item.voucherNo,
                        PrintUtil.getTransactionPlatform(item.transactionPlatform) + PrintUtil.getTransactionType(item.transactionType, true),
                        number,
                        MoneyUtil.longCent2DoubleMoneyStr(item.amount)
                };
                mPrinterService.setFontSize(smallSize - 2, null);
                mPrinterService.printColumnsString(text, colsWidth, colsAlign, null);
            }
        }

        printSeparateLine();

        printLine();

        printFooter(smallSize);

        mPrinterService.lineWrap(4, callback);
        mPrinterService.cutPaper(callback);
        mPrinterService.exitPrinterBufferWithCallback(true, callback);
    }

    private void printCardList(List<Settlement> list, int size) throws Exception {
        if (list == null || list.size() == 0) {
            return;
        }
        List<Settlement> tempList = new ArrayList<>();
        for (Settlement settlement : list) {
            boolean bool = settlement.transactionPlatform == 0 && TextUtils.equals(settlement.answerCode, "00");
            if (bool) {
                tempList.add(settlement);
            }
        }
        if (tempList.size() == 0) {
            return;
        }

        List<SumTotal> sumTotalList = new ArrayList<>();
        SumTotal saleSum = new SumTotal();
        SumTotal voidSum = new SumTotal();
        SumTotal refundSum = new SumTotal();
        SumTotal saleComSum = new SumTotal();
        SumTotal saleComVoidSum = new SumTotal();
        for (Settlement item : tempList) {
            if (item.transactionType == 1) {
                saleSum.platform = "0";
                saleSum.transType = "1";
                long amountSum = Long.parseLong(saleSum.sumMoney);
                amountSum += item.amount;
                int timeSum = Integer.parseInt(saleSum.transTimes);
                timeSum++;
                saleSum.sumMoney = String.valueOf(amountSum);
                saleSum.transTimes = String.valueOf(timeSum);
            } else if (item.transactionType == 2) {
                voidSum.platform = "0";
                voidSum.transType = "2";
                long amountSum = Long.parseLong(voidSum.sumMoney);
                amountSum += item.amount;
                int timeSum = Integer.parseInt(voidSum.transTimes);
                timeSum++;
                voidSum.sumMoney = String.valueOf(amountSum);
                voidSum.transTimes = String.valueOf(timeSum);
            } else if (item.transactionType == 3) {
                refundSum.platform = "0";
                refundSum.transType = "3";
                long amountSum = Long.parseLong(refundSum.sumMoney);
                amountSum += item.amount;
                int timeSum = Integer.parseInt(refundSum.transTimes);
                timeSum++;
                refundSum.sumMoney = String.valueOf(amountSum);
                refundSum.transTimes = String.valueOf(timeSum);
            } else if (item.transactionType == 6) {
                saleComSum.platform = "0";
                saleComSum.transType = "6";
                long amountSum = Long.parseLong(saleComSum.sumMoney);
                amountSum += item.amount;
                int timeSum = Integer.parseInt(saleComSum.transTimes);
                timeSum++;
                saleComSum.sumMoney = String.valueOf(amountSum);
                saleComSum.transTimes = String.valueOf(timeSum);
            } else if (item.transactionType == 7) {
                saleComVoidSum.platform = "0";
                saleComVoidSum.transType = "7";
                long amountSum = Long.parseLong(saleComVoidSum.sumMoney);
                amountSum += item.amount;
                int timeSum = Integer.parseInt(saleComVoidSum.transTimes);
                timeSum++;
                saleComVoidSum.sumMoney = String.valueOf(amountSum);
                saleComVoidSum.transTimes = String.valueOf(timeSum);
            }
        }
        sumTotalList.add(saleSum);
        sumTotalList.add(voidSum);
        sumTotalList.add(refundSum);
        sumTotalList.add(saleComSum);
        sumTotalList.add(saleComVoidSum);

        printSeparateLine();

        String temp = getString(R.string.receipt_card) + "\n";
        setBold(true);
        mPrinterService.printTextWithFont(temp, null, size + 4, null);
        setBold(false);

        for (SumTotal item : sumTotalList) {
            boolean equals = TextUtils.equals("0", item.transTimes);
            if (equals) continue;
            int parseInt = Integer.parseInt(item.transType);
            String transactionType = PrintUtil.getTransactionType(parseInt, true);
            String[] texts = {
                    transactionType,
                    item.transTimes,
                    transMoneyValue(item.sumMoney)
            };
            mPrinterService.setFontSize(size, null);
            mPrinterService.printColumnsString(texts, mColsWidth, mColsAlign, null);
        }
    }

    private void printCodeList(List<Settlement> list, int size) throws Exception {
        if (list == null || list.size() == 0) {
            return;
        }
        List<Settlement> tempList = new ArrayList<>();
        for (Settlement settlement : list) {
            boolean bool = settlement.transactionPlatform != 0 && TextUtils.equals(settlement.answerCode, "00") && settlement.qrCodeTransactionState == 1;
            if (bool) {
                tempList.add(settlement);
            }
        }
        if (tempList.size() == 0) {
            return;
        }

        List<SumTotal> sumTotalList = new ArrayList<>();
        SumTotal AL = new SumTotal();
        SumTotal AD = new SumTotal();
        SumTotal AR = new SumTotal();
        SumTotal WX = new SumTotal();
        SumTotal WD = new SumTotal();
        SumTotal WR = new SumTotal();
        SumTotal UX = new SumTotal();
        SumTotal UD = new SumTotal();
        SumTotal UR = new SumTotal();

        for (Settlement item : tempList) {
            // AliPay
            if (item.transactionType == 1 && item.transactionPlatform == 1) {
                AL.platform = "1";
                AL.transType = "1";
                long amountSum = Long.parseLong(AL.sumMoney);
                amountSum += item.amount;
                int timeSum = Integer.parseInt(AL.transTimes);
                timeSum++;
                AL.sumMoney = String.valueOf(amountSum);
                AL.transTimes = String.valueOf(timeSum);
            } else if (item.transactionType == 2 && item.transactionPlatform == 1) {
                AD.platform = "1";
                AD.transType = "2";
                long amountSum = Long.parseLong(AD.sumMoney);
                amountSum += item.amount;
                int timeSum = Integer.parseInt(AD.transTimes);
                timeSum++;
                AD.sumMoney = String.valueOf(amountSum);
                AD.transTimes = String.valueOf(timeSum);
            } else if (item.transactionType == 3 && item.transactionPlatform == 1) {
                AR.platform = "1";
                AR.transType = "3";
                long amountSum = Long.parseLong(AR.sumMoney);
                amountSum += item.amount;
                int timeSum = Integer.parseInt(AR.transTimes);
                timeSum++;
                AR.sumMoney = String.valueOf(amountSum);
                AR.transTimes = String.valueOf(timeSum);
            }
            // WeChat
            else if (item.transactionType == 1 && item.transactionPlatform == 2) {
                WX.platform = "2";
                WX.transType = "1";
                long amountSum = Long.parseLong(WX.sumMoney);
                amountSum += item.amount;
                int timeSum = Integer.parseInt(WX.transTimes);
                timeSum++;
                WX.sumMoney = String.valueOf(amountSum);
                WX.transTimes = String.valueOf(timeSum);
            } else if (item.transactionType == 2 && item.transactionPlatform == 2) {
                WD.platform = "2";
                WD.transType = "2";
                long amountSum = Long.parseLong(WD.sumMoney);
                amountSum += item.amount;
                int timeSum = Integer.parseInt(WD.transTimes);
                timeSum++;
                WD.sumMoney = String.valueOf(amountSum);
                WD.transTimes = String.valueOf(timeSum);
            } else if (item.transactionType == 3 && item.transactionPlatform == 2) {
                WR.platform = "2";
                WR.transType = "3";
                long amountSum = Long.parseLong(WR.sumMoney);
                amountSum += item.amount;
                int timeSum = Integer.parseInt(WR.transTimes);
                timeSum++;
                WR.sumMoney = String.valueOf(amountSum);
                WR.transTimes = String.valueOf(timeSum);
            }
            // UnionPay
            else if (item.transactionType == 1 && item.transactionPlatform == 4) {
                UX.platform = "4";
                UX.transType = "1";
                long amountSum = Long.parseLong(UX.sumMoney);
                amountSum += item.amount;
                int timeSum = Integer.parseInt(UX.transTimes);
                timeSum++;
                UX.sumMoney = String.valueOf(amountSum);
                UX.transTimes = String.valueOf(timeSum);
            } else if (item.transactionType == 2 && item.transactionPlatform == 4) {
                UD.platform = "4";
                UD.transType = "2";
                long amountSum = Long.parseLong(UD.sumMoney);
                amountSum += item.amount;
                int timeSum = Integer.parseInt(UD.transTimes);
                timeSum++;
                UD.sumMoney = String.valueOf(amountSum);
                UD.transTimes = String.valueOf(timeSum);
            } else if (item.transactionType == 3 && item.transactionPlatform == 4) {
                UR.platform = "4";
                UR.transType = "3";
                long amountSum = Long.parseLong(UR.sumMoney);
                amountSum += item.amount;
                int timeSum = Integer.parseInt(UR.transTimes);
                timeSum++;
                UR.sumMoney = String.valueOf(amountSum);
                UR.transTimes = String.valueOf(timeSum);
            }

        }
        sumTotalList.add(AL);
        sumTotalList.add(AD);
        sumTotalList.add(AR);
        sumTotalList.add(WX);
        sumTotalList.add(WD);
        sumTotalList.add(WR);
        sumTotalList.add(UX);
        sumTotalList.add(UD);
        sumTotalList.add(UR);

        printSeparateLine();

        String temp = getString(R.string.receipt_scan) + "\n";
        setBold(true);
        mPrinterService.printTextWithFont(temp, null, size + 4, null);
        setBold(false);

        for (SumTotal item : sumTotalList) {
            boolean equals = TextUtils.equals("0", item.transTimes);
            if (equals) continue;
            int platform = Integer.parseInt(item.platform);
            int transType = Integer.parseInt(item.transType);
            String transactionType = PrintUtil.getTransactionPlatform(platform) + PrintUtil.getTransactionType(transType, true);
            String[] texts = {
                    transactionType,
                    item.transTimes,
                    transMoneyValue(item.sumMoney)
            };
            mPrinterService.setFontSize(size, null);
            mPrinterService.printColumnsString(texts, mColsWidth, mColsAlign, null);
        }
    }

    private void printTotal(TransferExtra bean, int size) throws Exception {
        if (bean == null) {
            return;
        }
        String[] saleTotal = {
                getString(R.string.receipt_settlement_total),
                String.valueOf(bean.transNum),
                MoneyUtil.longCent2DoubleMoneyStr(bean.totalAmount)
        };
        mPrinterService.setFontSize(size + 2, null);
        mPrinterService.printColumnsString(saleTotal, mColsWidth, mColsAlign, null);
    }

    private void printTitle(TransferExtra bean, boolean isSummary, int size) throws Exception {
        printLogo(0);

        printSeparateLine();

        String title;
        if (isSummary) {
            title = getString(R.string.receipt_settlement_summary) + "\n";
        } else {
            title = getString(R.string.receipt_settlement_detail) + "\n";
        }
        mPrinterService.setAlignment(1, null);
        mPrinterService.printTextWithFont(title, null, size + 10, null);
        mPrinterService.setAlignment(0, null);

        printSeparateLine();

        printLine();

        printAddress(bean.merchantName, size);

        printLine();

        String merchantNo = TextUtils.isEmpty(bean.merchantId) ? "- -" : bean.merchantId;
        merchantNo = getString(R.string.receipt_merchant_num) + merchantNo + "\n";
        mPrinterService.printTextWithFont(merchantNo, null, size, null);

        String terminalNo = TextUtils.isEmpty(bean.terminalId) ? "- -" : bean.terminalId;
        terminalNo = getString(R.string.receipt_terminal_num) + terminalNo + "\n";
        mPrinterService.printTextWithFont(terminalNo, null, size, null);

        String batchNo = getString(R.string.receipt_batch_number) + bean.batchNo + "\n";
        mPrinterService.printTextWithFont(batchNo, null, size, null);

        String date = getString(R.string.receipt_transaction_time) + bean.transDate + " " + bean.transTime + "\n";
        mPrinterService.printTextWithFont(date, null, size, null);
    }

    private String transMoneyValue(String str) {
        return new BigDecimal(str).movePointLeft(2).toPlainString();
    }

    private String intercept(String value) {
        if (value == null) return "";
        if (value.length() < 9) return value;
        int length = value.length();
        String s1 = value.substring(0, 6);
        String s2 = value.substring(length - 4, length);
        return s1 + "******" + s2;
    }


}
