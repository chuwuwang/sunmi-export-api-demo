package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sm.l3.demo.print.PrintController;
import com.sm.l3.demo.print.PrintSettlementTicket;
import com.sm.l3.demo.socket.Settlement;
import com.sm.l3.demo.socket.TransferExtra;
import com.sm.l3.demo.util.ThreadPoolManager;

import java.lang.reflect.Type;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultView = findViewById(R.id.tv_result);

        Intent intent = getIntent();
        StringBuilder sb = new StringBuilder();

        int code = intent.getIntExtra("resultCode", -99999);
        if (code == 0) {
            sb.append("交易成功, 具体信息请查看控制台的Log \n");
        } else {
            sb.append("交易失败, 具体信息请查看控制台的Log \n");
        }

        sb.append("transType: " + intent.getIntExtra("transType", -99999) + "\n");
        sb.append("packageName: " + intent.getStringExtra("packageName") + "\n");
        sb.append("resultCode: " + intent.getIntExtra("resultCode", -99999) + "\n");
        sb.append("errorCode: " + intent.getIntExtra("errorCode", -99999) + "\n");
        sb.append("errorMsg: " + intent.getStringExtra("errorMsg") + "\n");
        sb.append("model: " + intent.getStringExtra("model") + "\n");
        sb.append("version: " + intent.getStringExtra("version") + "\n");
        sb.append("terminalId: " + intent.getStringExtra("terminalId") + "\n");
        sb.append("merchantName: " + intent.getStringExtra("merchantName") + "\n");
        sb.append("transDate: " + intent.getStringExtra("transDate") + "\n");
        sb.append("transTime: " + intent.getStringExtra("transTime") + "\n");
        sb.append("operatorId: " + intent.getStringExtra("operatorId") + "\n");
        sb.append("transId: " + intent.getStringExtra("transId") + "\n");
        sb.append("amount: " + intent.getLongExtra("amount", -99999L) + "\n");
        sb.append("voucherNo: " + intent.getStringExtra("voucherNo") + "\n");
        sb.append("batchNo: " + intent.getStringExtra("batchNo") + "\n");
        sb.append("referenceNo: " + intent.getStringExtra("referenceNo") + "\n");
        sb.append("authNo: " + intent.getStringExtra("authNo") + "\n");
        sb.append("qrOrderNo: " + intent.getStringExtra("qrOrderNo") + "\n");
        sb.append("cardNo: " + intent.getStringExtra("cardNo") + "\n");
        sb.append("cardType: " + intent.getStringExtra("cardType") + "\n");
        sb.append("accountType: " + intent.getStringExtra("accountType") + "\n");
        sb.append("issue: " + intent.getStringExtra("issue") + "\n");
        sb.append("acquire: " + intent.getStringExtra("acquire") + "\n");
        sb.append("answerCode: " + intent.getStringExtra("answerCode") + "\n");
        sb.append("transactionType: " + intent.getIntExtra("transactionType", -99999) + "\n");
        sb.append("transactionPlatform: " + intent.getIntExtra("transactionPlatform", -99999) + "\n");
        sb.append("qrCodeScanModel: " + intent.getIntExtra("qrCodeScanModel", -99999) + "\n");
        sb.append("qrCodeTransactionState: " + intent.getIntExtra("qrCodeTransactionState", -99999) + "\n");

        sb.append("\n 商户信息相关数据 \n");
        sb.append("merchantNameEn: " + intent.getStringExtra("merchantNameEn") + "\n");

        sb.append("\n 余额相关数据 \n");
        sb.append("balance: " + intent.getLongExtra("balance", -99999) + "\n");

        sb.append("\n 结算相关数据 \n");
        sb.append("transNum: " + intent.getIntExtra("transNum", -99999) + "\n");
        sb.append("totalAmount: " + intent.getLongExtra("totalAmount", -99999L) + "\n");
        sb.append("settleJson: " + intent.getStringExtra("settleJson") + "\n");

        String result = sb.toString();
        Log.e(TAG, "result: " + result);

        resultView.setText(result);

        List<Settlement> list = null;
        try {
            String json = intent.getStringExtra("settleJson");
            Type type = new TypeToken< List<Settlement> >() {}.getType();
            list = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int transType = intent.getIntExtra("transType", -99999);

        TransferExtra bean = (TransferExtra) intent.getSerializableExtra("bean");

        if (transType == 7 || transType == 18) {
            printSettlement(bean, list);
            return;
        }

        boolean b1 = TextUtils.equals(bean.answerCode, "00") && bean.transactionPlatform == 0;
        boolean b2 = TextUtils.equals(bean.answerCode, "00") && bean.transactionPlatform != 0 && bean.qrCodeTransactionState == 1;
        if (b1 || b2) {
            printOrder(bean);
        }
    }

    private void printOrder(TransferExtra bean) {
        ThreadPoolManager.executeInCachePool(
                () -> {
                    try {
                        int state = MyApplication.sunmiPrinterService.updatePrinterState();
                        if (state == 1) {
                            new PrintController().print(bean, null, 1, false);
                        } else {
                            showToast(R.string.error_printer);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToast(R.string.error_printer);
                    }
                }
        );
    }

    private void printSettlement(TransferExtra bean, List<Settlement> list) {
        ThreadPoolManager.executeInCachePool(
                () -> {
                    try {
                        int state = MyApplication.sunmiPrinterService.updatePrinterState();
                        if (state == 1) {
                            PrintSettlementTicket ticket = new PrintSettlementTicket();
                            ticket.printSettlementSummary(bean, list, null);

                            ticket.printSettlementDetail(bean, list, null);
                        } else {
                            showToast(R.string.error_printer);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToast(R.string.error_printer);
                    }
                }
        );
    }

    public void showToast(int resId) {
        runOnUiThread(
                () -> Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
        );
    }


}
