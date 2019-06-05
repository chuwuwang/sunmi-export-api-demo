package com.sm.l3.demo.socket;

public class Settlement {

    public String transId;          // 交易订单号
    public long amount;             // 交易金额
    public String voucherNo;        // 凭证号
    public String referenceNo;      // 参考号
    public String authNo;           // 授权码
    public String qrOrderNo;        // 扫码消费订单号
    public String batchNo;          // 批次号
    public String cardNo;           // 卡号
    public String cardType;         // 卡类型
    public String accountType;      // 账户类型
    public String issue;            // 发卡行
    public String acquire;          // 收单行
    public String answerCode;       // 银联响应码
    public int transactionType;     // 交易类型 “1”消费 “2”消费撤销 “3”退货 “4”预授权 “5”预授权撤销 “6”预授权完成 “7”预授权完成撤销
    public int transactionPlatform; // 交易平台 “0”：银行卡 “1”：支付宝 “2”：微信支付 “3”：口碑支付 “4”：银联钱包二维码
    public int qrCodeScanModel;     // 扫码模式
    public int qrCodeTransactionState; // 扫码支付状态 1：成功；-1：失败 2：支付中
    public String tradeDate;        // 交易日期
    public String tradeTime;        // 交易时间

}