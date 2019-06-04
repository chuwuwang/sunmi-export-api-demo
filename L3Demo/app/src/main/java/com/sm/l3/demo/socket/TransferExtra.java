package com.sm.l3.demo.socket;

public class TransferExtra {

    public Extras mExtras;

    public class Extras {

        public Bean mMap;

    }

    public class Bean {

        public int transType = -99999;
        public String packageName;

        public String errorMsg;
        public int errorCode = -99999;
        public int resultCode = -99999;

        public String model;
        public String version;
        public String terminalId;
        public String merchantId;
        public String merchantName;

        public String transTime;
        public String transDate;
        public String operatorId;

        public long amount = 99999L;

        public String authNo;
        public String batchNo;
        public String transId;
        public String voucherNo;
        public String qrOrderNo;
        public String referenceNo;

        public String issue;
        public String cardNo;
        public String acquire;
        public String cardType;
        public String accountType;

        public String answerCode;
        public int paymentType = -99999;

        public int transactionType = -99999;
        public int transactionPlatform = -99999;

        public int qrCodeScanModel = -99999;
        public int qrCodeTransactionState = -99999;

        // 商户信息相关数据
        public String merchantNameEn;

        // 余额相关数据
        public long balance = -99999;

        // 结算相关数据
        public int transNum = -99999;
        public long totalAmount = -99999L;
        public String settleJson;
    }


}
