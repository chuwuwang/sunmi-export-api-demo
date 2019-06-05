package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.sm.l3.demo.print.BankTicketPrinter;
import com.sm.l3.demo.print.PayDetail;
import com.sm.l3.demo.socket.WebSocketService;
import com.sunmi.peripheral.printer.ICallback;

public class PrintActivity extends BaseActivity {

    private EditText mEditVoucher;
    private CheckBox mCheckBoxLastTrade;
    private CheckBox mCheckBoxOnlyPrint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        initView();
    }

    private void initView() {
        mEditVoucher = findViewById(R.id.edit_input_voucher);
        mCheckBoxLastTrade = findViewById(R.id.cb_last_print);
        mCheckBoxOnlyPrint = findViewById(R.id.cb_only_print);
        Button ok = findViewById(R.id.btn_ok);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String packageName = getPackageName();
        boolean isOnlyPrint = mCheckBoxOnlyPrint.isChecked();
        boolean isLastTrade = mCheckBoxLastTrade.isChecked();
        String oriVoucherNo = mEditVoucher.getText().toString();

        Intent intent = new Intent(CALL_EXTRA_ACTION);
        intent.putExtra("transType", 11);
        intent.putExtra("appId", packageName);

        intent.putExtra("isOnlyPrint", isOnlyPrint);
        intent.putExtra("isLastTrade", isLastTrade);
        intent.putExtra("oriVoucherNo", oriVoucherNo);

        String json = new Gson().toJson(intent);
        WebSocketService.getInstance().send(json);

        // startActivity(intent);


        BankTicketPrinter bankTicketPrinter = new BankTicketPrinter(this,MyApplication.sunmiPrinterService);
        try {
            bankTicketPrinter.printBankTicket(new Gson().fromJson(PrintActivity.json, PayDetail.class), new ICallback.Stub() {
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
            },1,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static final String json = "{\"ACQcode\":\"\",\"AID\":\"\",\"AIICODE\":\"\",\"AON\":\"\",\"ARPCStatus\":\"\",\"ARQC\":\"\",\"ATC\":\"\",\"BathNo\":\"000001\",\"CID\":\"\",\"CardNo\":\"377187115008260\",\"EXPDate\":\"2209\",\"ICC55\":\"\",\"IssuerCode\":\"\",\"PDID\":7,\"PINCipher\":\"BBB597B5E990EC3A\",\"QRCodeTransState\":0,\"ScriptResult\":\"\",\"TC\":\"\",\"TSI\":\"\",\"TVR\":\"\",\"TerminalDate\":1559716293,\"TradeDate\":\"0605\",\"TradeTime\":\"143134\",\"accountType\":\"UNK\",\"additionalAmount\":0,\"amount\":1,\"appLabel\":\"\",\"appName\":\"\",\"authNo\":\"345117\",\"bankAppDate\":\"\",\"cardHolderName\":\"NI SHEN ZHOU              \",\"cardOrgCode\":\"\",\"cardSerialNo\":\"\",\"cardType\":1,\"confirmBag\":\"\",\"currencyCode\":\"156\",\"customOrderNo\":\"\",\"eSignIsUploaded\":false,\"eSignIsValid\":true,\"eSignJbigHex\":\"000001000000016D000000A0000000040800031CFF02C1E65DFF024A2EF7A83530FF02AAC198B6FF02EFE2FF02DE67AFFF026845B4FF026274FF02F4F24C0DB4FF02030306038908FF02A9359C8A86BB02FDFF020364AC24FF02B964FC476A44FF02B83A6B02593E934DFF028C5B01EC098176FF02A0E759178E0EEDFF0270B146456467EA7AD1BCC98EE9F9E37CD75434149C0715DC6558F9D76D2DEBF0FF0246407A833EEF4347EE3C163F88C526D58E379165AD79420A17F7BA64FE3E03EEFF025D39300B2633F3D1742BB11A4325C14E7DEBD40B9E52B6DA1FE28EC85F7B33F05760FF021A7A5BB9C63EB9FF0038AA68C28D3543DE04A3BA8C915B67B0183FACF51BD1ACFF020351C528ABEB353F227902F3D34F81C233F67F6530FF02009C376A6D69838AB1DA39B680FF0299562FEC0D1C64E02755900EBCAE80FF0290A355C47F40336B72D397B0EA20FF02B9EC1405C7D9269BBBE705FE774EA945C0FF021F2DF2A3B4AAA7010F3D813AFF0232DEDD3568F54EBA8455E819138F8480FF02424212919118C1AD582C5FC0FF021AB41076E3E2FF02A6D8BEA9F27F10FF028F425F34952DE7FF0261024910FF02FF02FF02FF02FF02FF02FF02FF02FF02\",\"easyProcess\":false,\"isAdjust\":false,\"isCanceled\":false,\"isForeignCard\":false,\"isFreePWD\":false,\"isFreeSign\":false,\"isICCardTrans\":false,\"isNeedReversal\":false,\"isOffLine\":false,\"isPrinted\":true,\"isReturnGood\":false,\"isReversaled\":false,\"isUploaded\":false,\"issuerVeriData\":\"\",\"merchantName\":\"ˢ��ʱ��\",\"merchantNo\":\"820290258140001\",\"msgType\":\"\",\"netManageCode\":\"000\",\"operatorNo\":\"01\",\"originalAuthNo\":\"\",\"originalBathNo\":\"\",\"originalPOSNum\":\"\",\"originalReferNo\":\"\",\"originalThirdTransNum\":\"\",\"originalTransDate\":\"\",\"payQRCode\":\"\",\"payType\":\"\",\"processCode\":\"000000\",\"qrCodeScanModel\":0,\"recvBag\":[0,-72,96,0,3,0,0,96,49,0,16,0,0,2,16,48,56,0,-128,30,-48,-128,17,0,0,0,0,0,0,0,0,1,0,0,39,20,49,52,6,5,0,0,22,79,71,97,-76,52,-110,93,-37,94,-28,-68,-63,95,-38,-28,71,48,48,48,52,52,56,51,49,53,48,50,50,51,52,53,49,49,55,48,48,50,57,48,49,48,57,55,54,56,50,48,50,57,48,50,53,56,49,52,48,48,48,49,100,-58,-42,-73,-94,-46,-8,-48,-48,40,-76,-5,41,32,32,32,32,32,32,32,32,32,32,32,32,-67,-69,-46,-41,-77,-55,-71,-90,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,49,53,54,0,17,0,0,0,1,0,0,57,49,52,65,69,69,49,66],\"referNo\":\"000448315022\",\"reference\":\"\",\"reversalAuthorize\":\"\",\"reversalBag\":\"\",\"reversalReason\":\"\",\"script1Content\":\"\",\"script1Length\":0,\"script2Content\":\"\",\"script2Length\":0,\"scriptUploadStatus\":0,\"sendBag\":[0,-26,96,0,3,0,0,96,49,0,16,0,0,2,0,48,32,4,-64,16,-62,-104,49,0,0,0,0,0,0,0,0,1,0,0,39,2,16,0,18,0,64,79,71,97,-76,52,-110,93,-37,-127,102,-13,27,-119,-94,-77,-97,31,-58,110,-55,-25,90,-19,-4,42,78,-101,122,47,-36,62,27,28,-66,49,72,-94,-126,-54,48,50,57,48,49,48,57,55,54,56,50,48,50,57,48,50,53,56,49,52,48,48,48,49,0,19,82,86,61,52,48,56,124,84,86,61,48,48,48,49,53,54,-69,-75,-105,-75,-23,-112,-20,58,38,0,0,0,0,0,0,0,0,117,65,50,48,55,48,48,49,48,48,50,48,52,48,50,48,50,49,48,48,48,48,52,51,48,52,80,76,48,49,49,57,51,84,48,48,53,56,54,48,51,48,48,54,48,48,56,50,54,48,48,52,48,48,56,56,50,56,68,68,69,53,66,48,53,48,48,56,50,48,49,57,48,54,48,53,0,19,34,0,0,1,0,6,0,52,48,70,55,66,51,51,57],\"settleDate\":\"\",\"tempMerchantName\":\"\",\"tempMerchantNo\":\"\",\"tempTerminalNo\":\"\",\"terminalNo\":\"29010976\",\"thirdTransNum\":\"\",\"track1\":\"\",\"track2\":\"377187115008260\\u003d220910117092233400000\",\"track3\":\"\",\"tradeAnswerCode\":\"00\",\"tradeResultDes\":\"\",\"transNum\":1559716283,\"transPlatform\":0,\"transType\":\"22\",\"transactionType\":1,\"voucherNo\":\"000027\"}\n";

}
