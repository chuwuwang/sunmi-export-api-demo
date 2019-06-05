package com.sm.l3.demo.print;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.sm.l3.demo.R;
import com.sunmi.peripheral.printer.ICallback;
import com.sunmi.peripheral.printer.SunmiPrinterService;


/**
 * 签购单打印内容封装类
 *
 * @author longx on 2017/6/16.
 */

public class BankTicketPrinter extends BasePrint {

    private static final String TAG = "BankTicketPrinter";

    private String smallTicketTail; // 自定义内容

    /**
     * 如果通道不需要打印 Sunmi-P1  ...等信息，需要打印定制的内容则重写该方法F
     */
    public String getSmallTicketTail() {
        return smallTicketTail;
    }

    public void setSmallTicketTail(String smallTicketTail) {
        this.smallTicketTail = smallTicketTail;
    }

    private long noPswLimit; // 免密额度
    private long noSignLimit; // 免签额度
    private PayDetail payDetail;

    public BankTicketPrinter(Context context, SunmiPrinterService printerService) {
        super(context, printerService);
    }

    public void printBankTicket(PayDetail payDetail, final ICallback callback, int pageNum, boolean isAgain) throws Exception {
        printTicket(payDetail, callback, pageNum, isAgain);
    }

    // 经典小票打印
    private void printTicket(PayDetail payDetail, final ICallback callback, int pageNum, boolean isAgain) throws Exception {
        this.payDetail = payDetail;
        int enSize = 14;
        int zhSize = 18;
        // 是否打印英文
        boolean isPrintEnglish = false;
//        if (slipPrintSettings.getSelectSalesSlipFont() > 0) {
            // 大号字体的签购单
            enSize += 4;
            zhSize += 4;
//        }

        printerService.enterPrinterBuffer(true);
        printerService.printerInit(null);

        printLogo(isPrintEnglish, callback);

        // 是否打印副标题
        boolean isPrintSubtitle = true;
        if (isPrintSubtitle) {
            printerService.setAlignment(1, null);
            setHeight(0x1A);
            String appName = getString(R.string.app_name);
            printerService.printTextWithFont(appName + "\n", "", zhSize, null);
            printerService.setAlignment(0, null);
        }

        // 打印存根信息
        boolean isMerchant = printMerchantOrUser(pageNum, isPrintEnglish, 18);

        // 打印商户信息
        printMerchantInfo(isPrintEnglish, zhSize, enSize);

        printIssuingAndAcceptBank(isPrintEnglish, zhSize, enSize);

        printCardNumber(isPrintEnglish, zhSize, enSize);

        printPayInfo(isPrintEnglish, zhSize, enSize);

        // 小票需要打印持卡人姓名+借贷记卡标示
        printCardHoldName(true, zhSize);

        // 打印备注
        printRemark(isPrintEnglish, zhSize, enSize);

        // 打印原凭证号 or 原参考号
        printVoucherOrReferNo(isPrintEnglish, payDetail, zhSize, enSize);

        // 打印重打印标签
        printAgainText(isAgain, isPrintEnglish, zhSize + 6);

        printTCAndAid(14);

        // 是否支持电子签名，且存在有效签名
        if (payDetail.isFreeSign) {
            doubleFreePrinterType(payDetail, zhSize);
        } else {
            // 是否是 非免签，且支持电子签名，且存在有效签名
            boolean flag = false;
            String jBigHex = null;
            if (flag) {
                jBigHex = payDetail.getESignJbigHex();
            }
            // 免密标识
            String freePWDData = null;
            if (payDetail.isFreePWD) {
                String moneyStr = MoneyUtils.longCent2DoubleMoneyStr(noPswLimit);
                freePWDData = String.format(getString(R.string.noPsw_noSign_flag), moneyStr);
            }
            // 打印持卡人签名
            printCardHolderSign(isPrintEnglish, isMerchant, jBigHex, freePWDData, zhSize, enSize);
        }

        printReceipt(isPrintEnglish, zhSize, enSize);

        // 打印服务热线和网址
        printServerAndWeb(isPrintEnglish, zhSize, enSize);


        // 打印小票底部信息
        String smallTicketTail = getSmallTicketTail();
        printFooter(callback, 18, smallTicketTail);

        printerService.lineWrap(4, callback);
        printerService.exitPrinterBufferWithCallback(true, callback);
    }

    // 打印签购单抬头
    private void printLogo(boolean isPrintEnglish, final ICallback callback) throws Exception {
        printerService.setAlignment(1, null);
        setHeight(0x1A);

        boolean isPrintChannelInfo = true;
        int rise = 3;
        String riseString = "sunmi";

        if (isPrintChannelInfo) {
            if (rise == 0) {
                String temp = TextUtils.isEmpty(riseString) ? getString(R.string.receipt_title) : riseString;
                temp = getString(R.string.receipt_1, temp);
                printerService.printTextWithFont(temp + "\n", "", 36, null);
                if (isPrintEnglish) {
                    temp = getString(R.string.receipt_1_e);
                    printerService.printTextWithFont(temp + "\n", "", 36, null);
                }
            } else if (rise == 2) {
                Resources res = context.getResources();
                BitmapFactory.Options opts = new BitmapFactory.Options();
                Bitmap unionPay = BitmapFactory.decodeResource(res, R.drawable.abc, opts);
                printerService.printBitmap(unionPay, callback);
                printerService.lineWrap(1, callback);
            } else {
                String temp = getString(R.string.receipt_1, getString(R.string.receipt_title));
                printerService.printTextWithFont(temp + "\n", "", 36, null);
                if (isPrintEnglish) {
                    temp = getString(R.string.receipt_1_e);
                    printerService.printTextWithFont(temp + "\n", "", 36, null);
                }
            }
        } else {
            String temp = TextUtils.isEmpty(riseString) ? getString(R.string.receipt_title) : riseString;
            temp = getString(R.string.receipt_1, temp);
            printerService.printTextWithFont(temp + "\n", "", 36, null);
            if (isPrintEnglish) {
                temp = getString(R.string.receipt_1_e);
                printerService.printTextWithFont(temp + "\n", "", 36, null);
            }
        }
    }

    /**
     * 打印商户信息
     */
    private void printMerchantInfo(boolean isPrintEnglish, int zhSize, int enSize) throws Exception {
        // 打印商户名称
        String temp = TextUtils.isEmpty(payDetail.tempMerchantName) ? payDetail.merchantName : payDetail.tempMerchantName;
        temp = TextUtils.isEmpty(temp) ? "--" : temp;
        temp = getString(R.string.receipt_3) + temp;
        printerService.printTextWithFont(temp + "\n", "", zhSize, null);
        // 打印英文商户名称
        if (isPrintEnglish) {
            setHeight(0x12);
            printerService.printTextWithFont("MERCHANT NAME\n", "", enSize, null);
        }
        setHeight(0x1A);
        printerService.setFontSize(zhSize, null);
        // 打印商户号
        temp = TextUtils.isEmpty(payDetail.tempMerchantNo) ? payDetail.merchantNo : payDetail.tempMerchantNo;
        temp = getString(R.string.receipt_4) + temp;
        printerService.printTextWithFont(temp + "\n", "", zhSize, null);
        // 打印英文商户号
        if (isPrintEnglish) {
            setHeight(0x12);
            printerService.printTextWithFont("MERCHANT NO.\n", "", enSize, null);
        }
        setHeight(0x1A);
        printerService.setFontSize(zhSize, null);
        // 打印终端号
        temp = TextUtils.isEmpty(payDetail.tempTerminalNo) ? payDetail.terminalNo : payDetail.tempTerminalNo;
        String[] colsTextArr = {
                getString(R.string.receipt_5) + temp,
                getString(R.string.receipt_20) + payDetail.operatorNo
        };
        int[] colsWidthArr = {7, 5};
        int[] colsAlign = {0, 0};
        printerService.printColumnsString(colsTextArr, colsWidthArr, colsAlign, null);
        // 打印英文终端号
        if (isPrintEnglish) {
            setHeight(0x12);
            printerService.setFontSize(enSize, null);
            String[] texts = {"TERMINAL NO.", "OPERATOR NO."};
            printerService.printColumnsString(texts, colsWidthArr, colsAlign, null);
        }
        setHeight(0x1A);
        printerService.setFontSize(zhSize, null);
    }

    private void printIssuingAndAcceptBank(boolean isPrintEnglish, int zhSize, int enSize) throws Exception {
//        SalesSlipPrintSettings slipPrintSettings = SalesSlipPrintSettingsOperation.getInstance().getSalesSlipPrintSettings();
        String cardBank;
        String issuingBank;
        // 收单行
        boolean b = true;
        String acceptCode = payDetail.ACQcode.trim();
        if (b) {
            cardBank = BankInfo.getName(acceptCode);
        } else {
            cardBank = BankInfo.getCode(acceptCode);
        }
        cardBank = TextUtils.isEmpty(cardBank) ? "SPDB" : cardBank;
        cardBank = cardBank.trim();
        // 发卡行
        b = false;
        String issuerCode = payDetail.IssuerCode.trim();
        if (b) {
            issuingBank = BankInfo.getName(issuerCode);
        } else {
            issuingBank = BankInfo.getCode(issuerCode);
        }
        issuingBank = issuingBank.trim();

        printerService.setFontSize(zhSize, null);
        String[] colsTextArr = {
                getString(R.string.f_bank, issuingBank),
                getString(R.string.s_bank, cardBank)
        };
        int[] colsWidthArr = {7, 5};
        int[] colsAlign = {0, 0};
        printerService.printColumnsString(colsTextArr, colsWidthArr, colsAlign, null);
        // 打印英文的ISS,ACQ
        if (isPrintEnglish) {
            printerService.setFontSize(enSize, null);
            setHeight(0x12);
            String[] texts = {"ISS", "ACQ"};
            printerService.printColumnsString(texts, colsWidthArr, colsAlign, null);
        }
    }

    private void printCardNumber(boolean isPrintEnglish, int zhSize, int enSize) throws Exception {
        setHeight(0x1A);
        printerService.setFontSize(zhSize, null);
        String temp = getString(R.string.receipt_7);
        printerService.printTextWithFont(temp, "", zhSize, null);
        if (isPrintEnglish) {
            printerService.printTextWithFont("(CARD NO.):\n", "", enSize, null);
        } else {
            printerService.printTextWithFont("\n", "", enSize, null);
        }
        setBold(true);
//        if (AidlConstants.CardType.MAGNETIC.getValue() == payDetail.cardType) {
//            temp = "(S)";
//        } else if (AidlConstants.CardType.IC.getValue() == payDetail.cardType) {
//            temp = "(I)";
//        } else if (AidlConstants.CardType.NFC.getValue() == payDetail.cardType) {
            temp = "(C)";
//        } else {
//            temp = "(M)";
//        }
        // 增加借贷记卡标识 1:借记卡 2:贷记卡 3:外卡
        if (payDetail.payType != null && payDetail.payType.length() > 0) {
            switch (payDetail.payType) {
                case "1":
                    temp = temp + getString(R.string.paytype_debit_card);
                    break;
                case "2":
                    temp = temp + getString(R.string.paytype_credit_card);
                    break;
                case "3":
                    temp = temp + getString(R.string.paytype_outside_card);
                    break;
            }
        }
        String card;
        if (payDetail.transactionType == 4) {
            boolean isShieldAuthorizationCard =true;
            if (isShieldAuthorizationCard) {
                card = NumberUtil.intercept(payDetail.CardNo);
            } else {
                card = payDetail.CardNo;
                StringBuilder tempSb = new StringBuilder();
                for (int i = 1; i <= card.length(); i++) {
                    char charAt = card.charAt(i - 1);
                    tempSb.append(charAt);
                    if (i % 4 == 0) {
                        tempSb.append(" ");
                    }
                }
                card = tempSb.toString();
            }
        } else {
            card = NumberUtil.intercept(payDetail.CardNo);
        }
        printerService.printTextWithFont(card + temp + "\n", "", zhSize + 8, null);
        setBold(false);
    }

    private void printPayInfo(boolean isPrintEnglish, int zhSize, int enSize) throws Exception {
        // 打印交易时间和卡有效期
        setHeight(0x1E);
        printerService.setFontSize(18, null);
        String transDate = TextUtils.isEmpty(payDetail.TradeDate) ? "" : payDetail.TradeDate.substring(0, 2)
                + "/" + payDetail.TradeDate.substring(2, 4);
        String transTime = TextUtils.isEmpty(payDetail.TradeTime) ? "" : payDetail.TradeTime.substring(0, 2)
                + ":" + payDetail.TradeTime.substring(2, 4) + ":" + payDetail.TradeTime.substring(4, 6);
        String expDate;
        if ( TextUtils.isEmpty(payDetail.EXPDate) ) {
            expDate = "";
        } else if ( "0000".equals(payDetail.EXPDate) ) {
            expDate = "00/00";
        } else {
            expDate = "20" + payDetail.EXPDate.substring(0, 2) + "/" + payDetail.EXPDate.substring(2, 4);
        }
        String time = getString(R.string.receipt_12) + SystemDateTime.getYYYY() + "/" + transDate + " " + transTime;
        String[] zhDates = {
                time,
                getString(R.string.receipt_8) + expDate
        };
        int[] colsWidthArr = {8, 5};
        int[] colsAlign = {0, 0};
        printerService.printColumnsString(zhDates, colsWidthArr, colsAlign, null);
        // 打印英文的交易时间和卡有效期
        if (isPrintEnglish) {
            setHeight(0x12);
            printerService.setFontSize(enSize, null);
            String[] enDates = {"TRADE TIME", "EXP DATE"};
            printerService.printColumnsString(enDates, colsWidthArr, colsAlign, null);
        }

        // 打印交易类型
        setHeight(0x1E);
        printerService.setFontSize(30, null);
        String typeC = "C";
        String typeE = "E";
        String type = getString(R.string.make7, typeC);
        type = isPrintEnglish ? type : type + "\n";
        setBold(true);
        printerService.printTextWithFont(type, "", zhSize + 6, null);
        if (isPrintEnglish) {
            printerService.printTextWithFont(typeE + "\n", "", enSize, null);
        }
        setBold(false);
        // 打印英文的交易类型
        if (isPrintEnglish) {
            setHeight(0x12);
            printerService.printTextWithFont("TRANS TYPE\n", "", enSize, null);
        }

        // 打印批次号和参考号
        int[] newColsWidthArr = {7, 5};
        setHeight(0x1E);
        printerService.setFontSize(22, null);
        String[] zh_batch_refer = {
                getString(R.string.receipt_11) + payDetail.referNo,
                getString(R.string.receipt_10) + payDetail.BathNo
        };
        printerService.printColumnsString(zh_batch_refer, newColsWidthArr, colsAlign, null);
        if (isPrintEnglish) {
            setHeight(0x12);
            printerService.setFontSize(enSize, null);
            String[] en_batch_refer = {"REFER NO.", "BATCH NO."};
            printerService.printColumnsString(en_batch_refer, newColsWidthArr, colsAlign, null);
        }

        // 打印授权码和凭证号
        setHeight(0x1E);
        printerService.setFontSize(22, null);
        String auth = TextUtils.isEmpty(payDetail.authNo) ? "       " : payDetail.authNo;
        String authNo = getString(R.string.receipt_14) + auth + "       ";
        if (payDetail.getTransactionType() == 4) {
            setBold(true);
            printerService.printTextWithFont(authNo, "", 22, null);
            setBold(false);
        } else {
            printerService.printTextWithFont(authNo, "", 22, null);
        }
        String voucher = getString(R.string.receipt_15) + payDetail.voucherNo;
        printerService.printTextWithFont(voucher + "\n", "", 22, null);
        // 打印英文的授权码和凭证号
        if (isPrintEnglish) {
            setHeight(0x12);
            printerService.setFontSize(enSize, null);
            String[] en_auth_voucher = {"AUTH NO.", "VOUCHER NO."};
            printerService.printColumnsString(en_auth_voucher, newColsWidthArr, colsAlign, null);
        }

        // 打印金额
        setHeight(0x1E);
        printerService.setFontSize(zhSize + 14, null);
        long amount;
        if (payDetail.transactionType == 2 || payDetail.transactionType == 3 ||
                payDetail.transactionType == 7 || payDetail.transactionType == 5) {
            boolean isPrintNegative = false;
            if (isPrintNegative) {
                amount = payDetail.amount * -1;
            } else {
                amount = payDetail.amount;
            }
        } else {
            amount = payDetail.amount;
        }
        String[] amount_texts = {
                getString(R.string.receipt_16),
                CurrencyInfo.getName(payDetail.currencyCode) + " " + String.format("%.2f", amount / 100.0)
        };
        int[] amount_width = {3, 6};
        int[] amount_align = {0, 2};
        setBold(true);
        printerService.printColumnsString(amount_texts, amount_width, amount_align, null);
        setBold(false);
        if (isPrintEnglish) {
            setHeight(0x12);
            printerService.printTextWithFont("AMOUNT\n", "", enSize, null);
        }
    }

    private void printCardHoldName(boolean isPrintCardHoldName, int zhSize) throws Exception {
        // 小票需要打印持卡人姓名+借贷记卡标示
        if (isPrintCardHoldName && payDetail.cardHolderName != null && payDetail.cardHolderName.trim().length() > 0) {
            setHeight(0x1A);
            printerService.setAlignment(0, null);
            String cardHolderName = getString(R.string.receipt_24) + payDetail.cardHolderName.trim();
            printerService.printTextWithFont(cardHolderName + "\n", "", zhSize, null);
        }
    }

    /**
     * 打印tc和aid
     */
    private void printTCAndAid(int enSize) throws Exception {
        if (true) {
            printSeparateLine();
            setHeight(0x12);
            printerService.setFontSize(enSize, null);
            int[] colsWidthArr = {7, 5};
            int[] colsAlign = {0, 0};
            String[] aid = {
                    getString(R.string.arqc) + payDetail.getARQC(),
                    getString(R.string.aid) + payDetail.getAID()
            };
            printerService.printColumnsString(aid, colsWidthArr, colsAlign, null);
            String[] atc = {
                    getString(R.string.tvr) + payDetail.getTVR(),
                    getString(R.string.atc) + payDetail.getATC()
            };
            printerService.printColumnsString(atc, colsWidthArr, colsAlign, null);
            String[] app_name = {
                    getString(R.string.paydetail_app_lable) + payDetail.getAppLabel(),
                    getString(R.string.paydetail_app_name) + payDetail.getAppName()
            };
            printerService.printColumnsString(app_name, colsWidthArr, colsAlign, null);
            String[] tc = {
                    getString(R.string.tc) + payDetail.getTC(),
                    getString(R.string.tsi) + payDetail.getTSI()
            };
            printerService.printColumnsString(tc, colsWidthArr, colsAlign, null);
        }
    }

    /**
     * 免密面签的不同情况下的打印
     */
    private void doubleFreePrinterType(PayDetail payDetail, int size) throws Exception {
        // 是否免密
        boolean isFreePwd = payDetail.isFreePWD;
        // 是否免签
        boolean isFreeSign = payDetail.isFreeSign;
        if (isFreePwd && isFreeSign) {
            // 免密免签
            long limit = noPswLimit < noSignLimit ? noPswLimit : noSignLimit;
            String moneyStr = MoneyUtils.longCent2DoubleMoneyStr(limit);
            String temp = String.format(getString(R.string.noPsw_noSign_flag), moneyStr) ;
            printerService.printTextWithFont(temp + "\n", "", size + 4, null);
        } else if (isFreeSign) {
            // 非免密免签
            String moneyStr = MoneyUtils.longCent2DoubleMoneyStr(noSignLimit);
            String temp = String.format(getString(R.string.noPsw_noSign_flag), moneyStr) ;
            printerService.printTextWithFont(temp + "\n", "", size + 4, null);
        }
        printSeparateLine();
    }


}
