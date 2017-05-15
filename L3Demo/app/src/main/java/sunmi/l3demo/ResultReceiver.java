package sunmi.l3demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 结果接收者
 * Created by xurong on 2017/5/15.
 */
public class ResultReceiver extends BroadcastReceiver {

    private static final String TAG = "ResultReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("sunmi.payment.L3Route.RESULT")) {
            int resultCode = intent.getIntExtra("resultCode", -1);
            long amount = intent.getLongExtra("amount", 0);
            // 原交易凭证号
            String voucherNo = intent.getStringExtra("voucherNo");
            // 原参考号
            String referenceNo = intent.getStringExtra("referenceNo");
            String date = intent.getStringExtra("transDate");
            String transId = intent.getStringExtra("transId");
            String batchNo = intent.getStringExtra("batchNo");
            String cardNo = intent.getStringExtra("cardNo");
            String cardType = intent.getStringExtra("cardType");
            String issue = intent.getStringExtra("issue");
            String terminalId = intent.getStringExtra("terminalId");
            String merchantId = intent.getStringExtra("merchantId");
            String merchantName = intent.getStringExtra("merchantName");
            String merchantNameEn = intent.getStringExtra("merchantNameEn");
            int paymentType = intent.getIntExtra("paymentType", 1);
            String transTime = intent.getStringExtra("transTime");
            int errorCode = intent.getIntExtra("errorCode",0);
            final String errorMsg = intent.getStringExtra("errorMsg");
            long balance = intent.getLongExtra("balance", 0);
            int transNum = intent.getIntExtra("transNum", 0);
            long totalAmount = intent.getLongExtra("totalAmount", 0L);

            if (resultCode == 0) {
                // 交易成功
                Toast.makeText(context, "交易成功, 具体信息请查看控制台的Log", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "交易成功");

            } else if (resultCode == -1) {
                // 交易失败
//                mHandler.postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
//                    }
//
//                }, 1000);
                Log.e(TAG, errorMsg);
            }
            Log.e(TAG, "resultCode:" + resultCode + "\namount:" + amount + "\nvoucherNo:" + voucherNo
                    + "\nreferenceNo:" + referenceNo + "\nbatchNo:" + batchNo + "\ncardNo:" + cardNo + "\ncardType:"
                    + cardType + "\nissue:" + issue + "\nterminalId:" + terminalId + "\nmerchantId:" + merchantId
                    + "\nmerchantName:" + merchantName + "\npaymentType:" + paymentType + "\ntransDate:" + date
                    + "\ntransTime:" + transTime + "\nerrorCode:" + errorCode + "\nerrorMsg:" + errorMsg
                    + "\nbalance:" + balance + "\ntransId:" + transId + "\nmerchantNameEn:" + merchantNameEn
                    + "\ntransNum:" + transNum + "\ntotalAmount:" + totalAmount);
        }
    }
}
