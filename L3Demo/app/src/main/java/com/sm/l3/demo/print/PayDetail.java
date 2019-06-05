package com.sm.l3.demo.print;


import java.io.Serializable;


/**
 * 订单详情
 */
@SuppressWarnings("serial")
public class PayDetail implements Serializable {

    // 自增ID
    public Long PDID;

    // 发送包
    public byte[] sendBag;
    // 接收包
    public byte[] recvBag;

    /**
     * ICCInfo
     */
    // 应用ID
    public String AID = "";
    //ARQC
    public String ARQC = "";
    // 应用标签
    public String appLabel = "";
    // 应用首选名称
    public String appName = "";
    // TVR
    public String TVR = "";
    // TSI
    public String TSI = "";
    // ATC
    public String ATC = "";
    // TC
    public String TC = "";
    // 发卡行认证数据
    public String issuerVeriData = "";
    //脚本处理结果
    public String ScriptResult = "";
    //密文信息数据
    public String CID = "";
    // 脚本1长度
    public int script1Length;
    // 脚本2长度
    public int script2Length;
    // 脚本1内容(用于二磁授权处理)71
    public String script1Content = "";
    // 脚本2内容(用于二磁授权处理)72
    public String script2Content = "";
    // 二次授权处理确认包
    public String confirmBag = "";
    // 二次授权处理冲正包
    public String reversalBag = "";
    // 脚本上送状态 0无需脚本状态上送，1需要脚本状态上送，
    // 2需要脚本状态上送且上送成功，3需要脚本上送且上送失败
    public int scriptUploadStatus = 0;
    // ic卡55域数据
    public String ICC55 = "";
    //EMV数据
    public byte[] emvData;
    // ARPC状态
    public String ARPCStatus = "";
    // 发卡行应用数据
    public String bankAppDate = "";
    //是否执行了简易流程
    public boolean easyProcess = false;

    // 商户名
    public String merchantName = "";
    // 商户编号
    public String merchantNo = "";
    // 终端号
    public String terminalNo = "";
    // 收单机构代码（域 44）
    public String ACQcode = "";
    // 收单机构标识码
    public String AIICODE = "";
    // 发卡机构代码（域 44）
    public String IssuerCode = "";
    // PIN加密密文
    public String PINCipher = "";
    // 卡号
    public String CardNo = "";
    // 主账号序号（针对IC 卡）
    public String cardSerialNo = "";
    // 交易处理码（域 3）
    public String processCode = "";
    //附加金额，以分为单位
    public long additionalAmount;
    // 交易金额 ，以分为单位
    public long amount = 0;
    // 卡类型
    public int cardType;
    // 操作员代码（域 63.1）
    public String operatorNo = "";
    // 交易类型 (22:消费/冲正 23::消费撤销/冲正 25:退货
    // 10:预授权/冲正 11:预授权撤销/冲正 20:预授权完成/冲正 21:预授权完成撤销/冲正)
    // 1:消费 2：消费撤销；3：退货  4.预授权 5.预授权撤销 6.预授权完成 7.预授权完成撤销
    public String transType = "";
    //消息类型  (0800：终端签到请求)
    public String msgType = "";
    // 网络管理信息码
    public String netManageCode = "";
    // 卡有效期
    public String EXPDate = "";
    // 交易批次号
    public String BathNo = "";
    // 原交易批次号
    public String originalBathNo = "";
    // 凭证号(终端/POS流水号)
    public String voucherNo = "";
    // 清算日期
    public String settleDate = "";
    // 交易日期(MMDD)（主机返回）
    public String TradeDate = "";
    // 交易时间(HHmmss)(主机返回）
    public String TradeTime = "";
    // 本地交易时间戳（Unix时间戳）
    public Long TerminalDate;
    // 授权号（主机返回）
    public String authNo = "";
    // 交易参考号
    public String referNo = "";
    // 原授权号（主机返回）
    public String originalAuthNo = "";
    // 原交易参考号
    public String originalReferNo = "";
    // 货币代码
    public String currencyCode = "";
    // 国际信用卡公司代码 （域 63.1）
    public String cardOrgCode = "";
    // 备注
    public String reference = "";
    // 交易应答码（域 39）
    public String tradeAnswerCode = "96";
    // 交易结果描述
    public String tradeResultDes = "";
    // 是否需要冲正
    public boolean isNeedReversal;
    // 是否是外卡
    public boolean isForeignCard;
    // 是否离线交易
    public boolean isOffLine;
    // 是否IC卡交易
    public boolean isICCardTrans;
    // 是否已撤销
    public boolean isCanceled;
    // 是否已调整
    public boolean isAdjust;
    // 是否已冲正
    public boolean isReversaled;
    // 是否已上送(结算不平时,需要批上送)
    public boolean isUploaded;
    // 是否已打印
    public boolean isPrinted;
    //是否免密
    public boolean isFreePWD;
    //是否免签
    public boolean isFreeSign;
    // 原交易日期
    public String originalTransDate = "";
    // 原交易流水号
    public String originalPOSNum = "";
    // 冲正原因
    public String reversalReason = "";
    // 冲正授权码
    public String reversalAuthorize = "";

    // 支持的交易平台: 0：银行卡；1：支付宝；2:微信;3:口碑；4:银联钱包; 5:小米钱包
//    public int qrCodeTransPlatform; //TODO 准备废弃
    public int transPlatform;

    // 1:主扫；2：被扫；3：撤销；4：退货
//    public int qrCodeTransType;//TODO 准备废弃
    // 1:消费 2：消费撤销；3：退货  4.预授权 5.预授权撤销 6.预授权完成 7.预授权完成撤销 8.结算 9.签到 10.签退 11.交易查询
    public int transactionType;

    // 1.主扫 2.被扫
    public int qrCodeScanModel;

    // 商户交易号 POS系统保留
    public long transNum;
    // 支付二维码串数据
    public String payQRCode = "";               // 被扫时回填二维码数据
    // 微信、支付宝平台交易号
    public String thirdTransNum = "";           // 扫码支付成功时的订单号
    // 扫码交易状态 1：成功；-1：失败 2：支付中 （消费、撤销、退货交易成功后都要置QRCodeTransState=1；失败QRCodeTransState=-1，支付中QRCodeTransState=2）
    public int QRCodeTransState;                // 扫码支付后回填的状态
    public byte[] printDataFromServer;          // 服务器返回的打印数据 （比如通联47域）
    public Long originalAmount;                 // 原交易金额
    public String originalThirdTransNum = "";   // 原交易订单号

    //+ zdy,17/9/18,富有对于某笔特殊交易会下发不一样的商户号、终端号、商户名
    /**
     * 当前交易独有的商户号
     */
    public String tempMerchantNo = "";

    /**
     * 当前交易独有的商户名
     */
    public String tempMerchantName = "";

    /**
     * 当前交易独有的终端号
     */
    public String tempTerminalNo = "";

    //- zdy,17/9/18,富有对于某笔特殊交易会下发不一样的商户号、终端号、商户名

    /**
     * 持卡人姓名
     */
    public String cardHolderName = "";
    //+ xr,17/10/13,新增电子签名和电子签名是否已上送
    /**
     * 电子签名是否有效签名
     */
    public boolean eSignIsValid;
    /**
     * 电子签名Jbig十六进制数据
     */
    public String eSignJbigHex = "";
    /**
     * 电子签名是否上送
     */
    public boolean eSignIsUploaded;
    //- xr,17/10/13,新增电子签名和电子签名是否已上送

    //------------------------下面是口碑相关字段---------------------------------
    public String merchTradeNo;     // 口碑的商户订单号
    public String tradeNo;          // 通道交易订单号,支付宝/微信等通道的订单号	Y	81680718
    private String totalAmount;      //	总订单金额,单位为元	N
    private String receiptAmount;    // 商户实收金额,单位为元	Y
    private String buyerPayAmount;   //	客户实付金额,单位为元	Y
    private String channelFund;      //	通道优惠金额,单位为元	Y
    private String merchantFund;     //	商户优惠金额,单位为元	Y
    public String payTime;          // 支付时间	Y
    public String outTradeNo;       // (支付宝流水号)Api交易订单号,直连通道交易将作为商户订单号出现在客户的账单上显示为商户订单号	N
    public String storeName;        //	店铺名称	Y
    public String proxyTradeNo;     // 中间通道订单号,通过中间通道支付时,将作为商户订单号出现在客户的账单上显示为商户订单号	Y
    public String gmtRefundPay;     // 退款时间	Y
    public String payAmount;
    //-------------------------------------------------------------------------//

    // 自定义订单号（科脉订单号）
    public String customOrderNo = "";

    //------------------------下面是小米自有字段---------------------------------
    //支付方式  1:借记卡 2:贷记卡 3:外卡 11:支付宝 12:微信支付 13:小米钱包 21:银联二维码借记  22银联二维码贷记
    public String payType = "";
    //-------------------------------------------------------------------------//

    // 账户类型
    // "OA"：扫码
    // "CC"：贷记卡
    // "DC"：借记卡
    // "SCC"：准贷记卡
    // "EC"：电子现金
    // "MAG"：磁条卡
    // "VC"：Visa卡
    // "MC"：MasterCard 万事达卡
    // "AE"：美国运通卡
    // "JCB"：JCB卡是源自日本的世界通用国际信用卡
    // "RPC"：RuPay(India)印度卡
    public String accountType ;

    // 冲正应答码
    public String correctAnswerCode;
    // 是否已退货
    public boolean isReturnGood;
    public PayDetail(Long PDID, byte[] sendBag, byte[] recvBag, String AID, String ARQC, String appLabel,
                     String appName, String TVR, String TSI, String ATC, String TC, String issuerVeriData,
                     String ScriptResult, String CID, int script1Length, int script2Length, String script1Content,
                     String script2Content, String confirmBag, String reversalBag, int scriptUploadStatus, String ICC55,
                     byte[] emvData, String ARPCStatus, String bankAppDate, boolean easyProcess, String merchantName,
                     String merchantNo, String terminalNo, String ACQcode, String AIICODE, String IssuerCode,
                     String PINCipher, String CardNo, String cardSerialNo, String processCode, long additionalAmount,
                     long amount, int cardType, String operatorNo, String transType, String msgType, String netManageCode,
                     String EXPDate, String BathNo, String originalBathNo, String voucherNo, String settleDate,
                     String TradeDate, String TradeTime, Long TerminalDate, String authNo, String referNo,
                     String originalAuthNo, String originalReferNo, String currencyCode, String cardOrgCode,
                     String reference, String tradeAnswerCode, String tradeResultDes, boolean isNeedReversal,
                     boolean isForeignCard, boolean isOffLine, boolean isICCardTrans, boolean isCanceled, boolean isAdjust,
                     boolean isReversaled, boolean isUploaded, boolean isPrinted, boolean isFreePWD, boolean isFreeSign,
                     String originalTransDate, String originalPOSNum, String reversalReason, String reversalAuthorize,
                     int transPlatform, int transactionType, int qrCodeScanModel, long transNum, String payQRCode,
                     String thirdTransNum, int QRCodeTransState, byte[] printDataFromServer, Long originalAmount,
                     String originalThirdTransNum, String tempMerchantNo, String tempMerchantName, String tempTerminalNo,
                     String cardHolderName, boolean eSignIsValid, String eSignJbigHex, boolean eSignIsUploaded,
                     String merchTradeNo, String tradeNo, String totalAmount, String receiptAmount, String buyerPayAmount,
                     String channelFund, String merchantFund, String payTime, String outTradeNo, String storeName,
                     String proxyTradeNo, String gmtRefundPay, String payAmount, String customOrderNo, String payType,
                     String accountType, String correctAnswerCode, boolean isReturnGood) {
        this.PDID = PDID;
        this.sendBag = sendBag;
        this.recvBag = recvBag;
        this.AID = AID;
        this.ARQC = ARQC;
        this.appLabel = appLabel;
        this.appName = appName;
        this.TVR = TVR;
        this.TSI = TSI;
        this.ATC = ATC;
        this.TC = TC;
        this.issuerVeriData = issuerVeriData;
        this.ScriptResult = ScriptResult;
        this.CID = CID;
        this.script1Length = script1Length;
        this.script2Length = script2Length;
        this.script1Content = script1Content;
        this.script2Content = script2Content;
        this.confirmBag = confirmBag;
        this.reversalBag = reversalBag;
        this.scriptUploadStatus = scriptUploadStatus;
        this.ICC55 = ICC55;
        this.emvData = emvData;
        this.ARPCStatus = ARPCStatus;
        this.bankAppDate = bankAppDate;
        this.easyProcess = easyProcess;
        this.merchantName = merchantName;
        this.merchantNo = merchantNo;
        this.terminalNo = terminalNo;
        this.ACQcode = ACQcode;
        this.AIICODE = AIICODE;
        this.IssuerCode = IssuerCode;
        this.PINCipher = PINCipher;
        this.CardNo = CardNo;
        this.cardSerialNo = cardSerialNo;
        this.processCode = processCode;
        this.additionalAmount = additionalAmount;
        this.amount = amount;
        this.cardType = cardType;
        this.operatorNo = operatorNo;
        this.transType = transType;
        this.msgType = msgType;
        this.netManageCode = netManageCode;
        this.EXPDate = EXPDate;
        this.BathNo = BathNo;
        this.originalBathNo = originalBathNo;
        this.voucherNo = voucherNo;
        this.settleDate = settleDate;
        this.TradeDate = TradeDate;
        this.TradeTime = TradeTime;
        this.TerminalDate = TerminalDate;
        this.authNo = authNo;
        this.referNo = referNo;
        this.originalAuthNo = originalAuthNo;
        this.originalReferNo = originalReferNo;
        this.currencyCode = currencyCode;
        this.cardOrgCode = cardOrgCode;
        this.reference = reference;
        this.tradeAnswerCode = tradeAnswerCode;
        this.tradeResultDes = tradeResultDes;
        this.isNeedReversal = isNeedReversal;
        this.isForeignCard = isForeignCard;
        this.isOffLine = isOffLine;
        this.isICCardTrans = isICCardTrans;
        this.isCanceled = isCanceled;
        this.isAdjust = isAdjust;
        this.isReversaled = isReversaled;
        this.isUploaded = isUploaded;
        this.isPrinted = isPrinted;
        this.isFreePWD = isFreePWD;
        this.isFreeSign = isFreeSign;
        this.originalTransDate = originalTransDate;
        this.originalPOSNum = originalPOSNum;
        this.reversalReason = reversalReason;
        this.reversalAuthorize = reversalAuthorize;
        this.transPlatform = transPlatform;
        this.transactionType = transactionType;
        this.qrCodeScanModel = qrCodeScanModel;
        this.transNum = transNum;
        this.payQRCode = payQRCode;
        this.thirdTransNum = thirdTransNum;
        this.QRCodeTransState = QRCodeTransState;
        this.printDataFromServer = printDataFromServer;
        this.originalAmount = originalAmount;
        this.originalThirdTransNum = originalThirdTransNum;
        this.tempMerchantNo = tempMerchantNo;
        this.tempMerchantName = tempMerchantName;
        this.tempTerminalNo = tempTerminalNo;
        this.cardHolderName = cardHolderName;
        this.eSignIsValid = eSignIsValid;
        this.eSignJbigHex = eSignJbigHex;
        this.eSignIsUploaded = eSignIsUploaded;
        this.merchTradeNo = merchTradeNo;
        this.tradeNo = tradeNo;
        this.totalAmount = totalAmount;
        this.receiptAmount = receiptAmount;
        this.buyerPayAmount = buyerPayAmount;
        this.channelFund = channelFund;
        this.merchantFund = merchantFund;
        this.payTime = payTime;
        this.outTradeNo = outTradeNo;
        this.storeName = storeName;
        this.proxyTradeNo = proxyTradeNo;
        this.gmtRefundPay = gmtRefundPay;
        this.payAmount = payAmount;
        this.customOrderNo = customOrderNo;
        this.payType = payType;
        this.accountType = accountType;
        this.correctAnswerCode = correctAnswerCode;
        this.isReturnGood = isReturnGood;
    }
    public PayDetail() {
    }
    public Long getPDID() {
        return this.PDID;
    }
    public void setPDID(Long PDID) {
        this.PDID = PDID;
    }
    public byte[] getSendBag() {
        return this.sendBag;
    }
    public void setSendBag(byte[] sendBag) {
        this.sendBag = sendBag;
    }
    public byte[] getRecvBag() {
        return this.recvBag;
    }
    public void setRecvBag(byte[] recvBag) {
        this.recvBag = recvBag;
    }
    public String getAID() {
        return this.AID;
    }
    public void setAID(String AID) {
        this.AID = AID;
    }
    public String getARQC() {
        return this.ARQC;
    }
    public void setARQC(String ARQC) {
        this.ARQC = ARQC;
    }
    public String getAppLabel() {
        return this.appLabel;
    }
    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }
    public String getAppName() {
        return this.appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public String getTVR() {
        return this.TVR;
    }
    public void setTVR(String TVR) {
        this.TVR = TVR;
    }
    public String getTSI() {
        return this.TSI;
    }
    public void setTSI(String TSI) {
        this.TSI = TSI;
    }
    public String getATC() {
        return this.ATC;
    }
    public void setATC(String ATC) {
        this.ATC = ATC;
    }
    public String getTC() {
        return this.TC;
    }
    public void setTC(String TC) {
        this.TC = TC;
    }
    public String getIssuerVeriData() {
        return this.issuerVeriData;
    }
    public void setIssuerVeriData(String issuerVeriData) {
        this.issuerVeriData = issuerVeriData;
    }
    public String getScriptResult() {
        return this.ScriptResult;
    }
    public void setScriptResult(String ScriptResult) {
        this.ScriptResult = ScriptResult;
    }
    public String getCID() {
        return this.CID;
    }
    public void setCID(String CID) {
        this.CID = CID;
    }
    public int getScript1Length() {
        return this.script1Length;
    }
    public void setScript1Length(int script1Length) {
        this.script1Length = script1Length;
    }
    public int getScript2Length() {
        return this.script2Length;
    }
    public void setScript2Length(int script2Length) {
        this.script2Length = script2Length;
    }
    public String getScript1Content() {
        return this.script1Content;
    }
    public void setScript1Content(String script1Content) {
        this.script1Content = script1Content;
    }
    public String getScript2Content() {
        return this.script2Content;
    }
    public void setScript2Content(String script2Content) {
        this.script2Content = script2Content;
    }
    public String getConfirmBag() {
        return this.confirmBag;
    }
    public void setConfirmBag(String confirmBag) {
        this.confirmBag = confirmBag;
    }
    public String getReversalBag() {
        return this.reversalBag;
    }
    public void setReversalBag(String reversalBag) {
        this.reversalBag = reversalBag;
    }
    public int getScriptUploadStatus() {
        return this.scriptUploadStatus;
    }
    public void setScriptUploadStatus(int scriptUploadStatus) {
        this.scriptUploadStatus = scriptUploadStatus;
    }
    public String getICC55() {
        return this.ICC55;
    }
    public void setICC55(String ICC55) {
        this.ICC55 = ICC55;
    }
    public byte[] getEmvData() {
        return this.emvData;
    }
    public void setEmvData(byte[] emvData) {
        this.emvData = emvData;
    }
    public String getARPCStatus() {
        return this.ARPCStatus;
    }
    public void setARPCStatus(String ARPCStatus) {
        this.ARPCStatus = ARPCStatus;
    }
    public String getBankAppDate() {
        return this.bankAppDate;
    }
    public void setBankAppDate(String bankAppDate) {
        this.bankAppDate = bankAppDate;
    }
    public boolean getEasyProcess() {
        return this.easyProcess;
    }
    public void setEasyProcess(boolean easyProcess) {
        this.easyProcess = easyProcess;
    }
    public String getMerchantName() {
        return this.merchantName;
    }
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    public String getMerchantNo() {
        return this.merchantNo;
    }
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }
    public String getTerminalNo() {
        return this.terminalNo;
    }
    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }
    public String getACQcode() {
        return this.ACQcode;
    }
    public void setACQcode(String ACQcode) {
        this.ACQcode = ACQcode;
    }
    public String getAIICODE() {
        return this.AIICODE;
    }
    public void setAIICODE(String AIICODE) {
        this.AIICODE = AIICODE;
    }
    public String getIssuerCode() {
        return this.IssuerCode;
    }
    public void setIssuerCode(String IssuerCode) {
        this.IssuerCode = IssuerCode;
    }
    public String getPINCipher() {
        return this.PINCipher;
    }
    public void setPINCipher(String PINCipher) {
        this.PINCipher = PINCipher;
    }
    public String getCardNo() {
        return this.CardNo;
    }
    public void setCardNo(String CardNo) {
        this.CardNo = CardNo;
    }
    public String getCardSerialNo() {
        return this.cardSerialNo;
    }
    public void setCardSerialNo(String cardSerialNo) {
        this.cardSerialNo = cardSerialNo;
    }
    public String getProcessCode() {
        return this.processCode;
    }
    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }
    public long getAdditionalAmount() {
        return this.additionalAmount;
    }
    public void setAdditionalAmount(long additionalAmount) {
        this.additionalAmount = additionalAmount;
    }
    public long getAmount() {
        return this.amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }
    public int getCardType() {
        return this.cardType;
    }
    public void setCardType(int cardType) {
        this.cardType = cardType;
    }
    public String getOperatorNo() {
        return this.operatorNo;
    }
    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }
    public String getTransType() {
        return this.transType;
    }
    public void setTransType(String transType) {
        this.transType = transType;
    }
    public String getMsgType() {
        return this.msgType;
    }
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    public String getNetManageCode() {
        return this.netManageCode;
    }
    public void setNetManageCode(String netManageCode) {
        this.netManageCode = netManageCode;
    }
    public String getEXPDate() {
        return this.EXPDate;
    }
    public void setEXPDate(String EXPDate) {
        this.EXPDate = EXPDate;
    }
    public String getBathNo() {
        return this.BathNo;
    }
    public void setBathNo(String BathNo) {
        this.BathNo = BathNo;
    }
    public String getOriginalBathNo() {
        return this.originalBathNo;
    }
    public void setOriginalBathNo(String originalBathNo) {
        this.originalBathNo = originalBathNo;
    }
    public String getVoucherNo() {
        return this.voucherNo;
    }
    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }
    public String getSettleDate() {
        return this.settleDate;
    }
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }
    public String getTradeDate() {
        return this.TradeDate;
    }
    public void setTradeDate(String TradeDate) {
        this.TradeDate = TradeDate;
    }
    public String getTradeTime() {
        return this.TradeTime;
    }
    public void setTradeTime(String TradeTime) {
        this.TradeTime = TradeTime;
    }
    public Long getTerminalDate() {
        return this.TerminalDate;
    }
    public void setTerminalDate(Long TerminalDate) {
        this.TerminalDate = TerminalDate;
    }
    public String getAuthNo() {
        return this.authNo;
    }
    public void setAuthNo(String authNo) {
        this.authNo = authNo;
    }
    public String getReferNo() {
        return this.referNo;
    }
    public void setReferNo(String referNo) {
        this.referNo = referNo;
    }
    public String getOriginalAuthNo() {
        return this.originalAuthNo;
    }
    public void setOriginalAuthNo(String originalAuthNo) {
        this.originalAuthNo = originalAuthNo;
    }
    public String getOriginalReferNo() {
        return this.originalReferNo;
    }
    public void setOriginalReferNo(String originalReferNo) {
        this.originalReferNo = originalReferNo;
    }
    public String getCurrencyCode() {
        return this.currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public String getCardOrgCode() {
        return this.cardOrgCode;
    }
    public void setCardOrgCode(String cardOrgCode) {
        this.cardOrgCode = cardOrgCode;
    }
    public String getReference() {
        return this.reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public String getTradeAnswerCode() {
        return this.tradeAnswerCode;
    }
    public void setTradeAnswerCode(String tradeAnswerCode) {
        this.tradeAnswerCode = tradeAnswerCode;
    }
    public String getTradeResultDes() {
        return this.tradeResultDes;
    }
    public void setTradeResultDes(String tradeResultDes) {
        this.tradeResultDes = tradeResultDes;
    }
    public boolean getIsNeedReversal() {
        return this.isNeedReversal;
    }
    public void setIsNeedReversal(boolean isNeedReversal) {
        this.isNeedReversal = isNeedReversal;
    }
    public boolean getIsForeignCard() {
        return this.isForeignCard;
    }
    public void setIsForeignCard(boolean isForeignCard) {
        this.isForeignCard = isForeignCard;
    }
    public boolean getIsOffLine() {
        return this.isOffLine;
    }
    public void setIsOffLine(boolean isOffLine) {
        this.isOffLine = isOffLine;
    }
    public boolean getIsICCardTrans() {
        return this.isICCardTrans;
    }
    public void setIsICCardTrans(boolean isICCardTrans) {
        this.isICCardTrans = isICCardTrans;
    }
    public boolean getIsCanceled() {
        return this.isCanceled;
    }
    public void setIsCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }
    public boolean getIsAdjust() {
        return this.isAdjust;
    }
    public void setIsAdjust(boolean isAdjust) {
        this.isAdjust = isAdjust;
    }
    public boolean getIsReversaled() {
        return this.isReversaled;
    }
    public void setIsReversaled(boolean isReversaled) {
        this.isReversaled = isReversaled;
    }
    public boolean getIsUploaded() {
        return this.isUploaded;
    }
    public void setIsUploaded(boolean isUploaded) {
        this.isUploaded = isUploaded;
    }
    public boolean getIsPrinted() {
        return this.isPrinted;
    }
    public void setIsPrinted(boolean isPrinted) {
        this.isPrinted = isPrinted;
    }
    public boolean getIsFreePWD() {
        return this.isFreePWD;
    }
    public void setIsFreePWD(boolean isFreePWD) {
        this.isFreePWD = isFreePWD;
    }
    public boolean getIsFreeSign() {
        return this.isFreeSign;
    }
    public void setIsFreeSign(boolean isFreeSign) {
        this.isFreeSign = isFreeSign;
    }
    public String getOriginalTransDate() {
        return this.originalTransDate;
    }
    public void setOriginalTransDate(String originalTransDate) {
        this.originalTransDate = originalTransDate;
    }
    public String getOriginalPOSNum() {
        return this.originalPOSNum;
    }
    public void setOriginalPOSNum(String originalPOSNum) {
        this.originalPOSNum = originalPOSNum;
    }
    public String getReversalReason() {
        return this.reversalReason;
    }
    public void setReversalReason(String reversalReason) {
        this.reversalReason = reversalReason;
    }
    public String getReversalAuthorize() {
        return this.reversalAuthorize;
    }
    public void setReversalAuthorize(String reversalAuthorize) {
        this.reversalAuthorize = reversalAuthorize;
    }
    public int getTransPlatform() {
        return this.transPlatform;
    }
    public void setTransPlatform(int transPlatform) {
        this.transPlatform = transPlatform;
    }
    public int getTransactionType() {
        return this.transactionType;
    }
    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }
    public int getQrCodeScanModel() {
        return this.qrCodeScanModel;
    }
    public void setQrCodeScanModel(int qrCodeScanModel) {
        this.qrCodeScanModel = qrCodeScanModel;
    }
    public long getTransNum() {
        return this.transNum;
    }
    public void setTransNum(long transNum) {
        this.transNum = transNum;
    }
    public String getPayQRCode() {
        return this.payQRCode;
    }
    public void setPayQRCode(String payQRCode) {
        this.payQRCode = payQRCode;
    }
    public String getThirdTransNum() {
        return this.thirdTransNum;
    }
    public void setThirdTransNum(String thirdTransNum) {
        this.thirdTransNum = thirdTransNum;
    }
    public int getQRCodeTransState() {
        return this.QRCodeTransState;
    }
    public void setQRCodeTransState(int QRCodeTransState) {
        this.QRCodeTransState = QRCodeTransState;
    }
    public byte[] getPrintDataFromServer() {
        return this.printDataFromServer;
    }
    public void setPrintDataFromServer(byte[] printDataFromServer) {
        this.printDataFromServer = printDataFromServer;
    }
    public Long getOriginalAmount() {
        return this.originalAmount;
    }
    public void setOriginalAmount(Long originalAmount) {
        this.originalAmount = originalAmount;
    }
    public String getOriginalThirdTransNum() {
        return this.originalThirdTransNum;
    }
    public void setOriginalThirdTransNum(String originalThirdTransNum) {
        this.originalThirdTransNum = originalThirdTransNum;
    }
    public String getTempMerchantNo() {
        return this.tempMerchantNo;
    }
    public void setTempMerchantNo(String tempMerchantNo) {
        this.tempMerchantNo = tempMerchantNo;
    }
    public String getTempMerchantName() {
        return this.tempMerchantName;
    }
    public void setTempMerchantName(String tempMerchantName) {
        this.tempMerchantName = tempMerchantName;
    }
    public String getTempTerminalNo() {
        return this.tempTerminalNo;
    }
    public void setTempTerminalNo(String tempTerminalNo) {
        this.tempTerminalNo = tempTerminalNo;
    }
    public String getCardHolderName() {
        return this.cardHolderName;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    public boolean getESignIsValid() {
        return this.eSignIsValid;
    }
    public void setESignIsValid(boolean eSignIsValid) {
        this.eSignIsValid = eSignIsValid;
    }
    public String getESignJbigHex() {
        return this.eSignJbigHex;
    }
    public void setESignJbigHex(String eSignJbigHex) {
        this.eSignJbigHex = eSignJbigHex;
    }
    public boolean getESignIsUploaded() {
        return this.eSignIsUploaded;
    }
    public void setESignIsUploaded(boolean eSignIsUploaded) {
        this.eSignIsUploaded = eSignIsUploaded;
    }
    public String getMerchTradeNo() {
        return this.merchTradeNo;
    }
    public void setMerchTradeNo(String merchTradeNo) {
        this.merchTradeNo = merchTradeNo;
    }
    public String getTradeNo() {
        return this.tradeNo;
    }
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
    public String getTotalAmount() {
        return this.totalAmount;
    }
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getReceiptAmount() {
        return this.receiptAmount;
    }
    public void setReceiptAmount(String receiptAmount) {
        this.receiptAmount = receiptAmount;
    }
    public String getBuyerPayAmount() {
        return this.buyerPayAmount;
    }
    public void setBuyerPayAmount(String buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }
    public String getChannelFund() {
        return this.channelFund;
    }
    public void setChannelFund(String channelFund) {
        this.channelFund = channelFund;
    }
    public String getMerchantFund() {
        return this.merchantFund;
    }
    public void setMerchantFund(String merchantFund) {
        this.merchantFund = merchantFund;
    }
    public String getPayTime() {
        return this.payTime;
    }
    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
    public String getOutTradeNo() {
        return this.outTradeNo;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    public String getStoreName() {
        return this.storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public String getProxyTradeNo() {
        return this.proxyTradeNo;
    }
    public void setProxyTradeNo(String proxyTradeNo) {
        this.proxyTradeNo = proxyTradeNo;
    }
    public String getGmtRefundPay() {
        return this.gmtRefundPay;
    }
    public void setGmtRefundPay(String gmtRefundPay) {
        this.gmtRefundPay = gmtRefundPay;
    }
    public String getPayAmount() {
        return this.payAmount;
    }
    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }
    public String getCustomOrderNo() {
        return this.customOrderNo;
    }
    public void setCustomOrderNo(String customOrderNo) {
        this.customOrderNo = customOrderNo;
    }
    public String getPayType() {
        return this.payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public String getAccountType() {
        return this.accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getCorrectAnswerCode() {
        return this.correctAnswerCode;
    }
    public void setCorrectAnswerCode(String correctAnswerCode) {
        this.correctAnswerCode = correctAnswerCode;
    }
    public boolean getIsReturnGood() {
        return this.isReturnGood;
    }
    public void setIsReturnGood(boolean isReturnGood) {
        this.isReturnGood = isReturnGood;
    }


}
