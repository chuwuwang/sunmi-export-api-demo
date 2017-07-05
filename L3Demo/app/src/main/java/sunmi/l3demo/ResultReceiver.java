package sunmi.l3demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

/**
 * 结果接收者
 *
 * @author xurong on 2017/5/15.
 */
public class ResultReceiver extends BroadcastReceiver {

    private static final String TAG = "ResultReceiver";
    private String resultInfo;

    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("sunmi.payment.L3.RESULT")) {
            final int resultCode = intent.getIntExtra("resultCode", -1);
            long amount = intent.getLongExtra("amount", 0);
            // 原交易凭证号
            String voucherNo = intent.getStringExtra("voucherNo");
            // 原参考号
            String referenceNo = intent.getStringExtra("referenceNo");
            String date = intent.getStringExtra("transDate");
            String transId = intent.getStringExtra("transId");
            int transType = intent.getIntExtra("transType", -10);
            String batchNo = intent.getStringExtra("batchNo");
            String cardNo = intent.getStringExtra("cardNo");
            String cardType = intent.getStringExtra("cardType");
            String issue = intent.getStringExtra("issue");
            String terminalId = intent.getStringExtra("terminalId");
            String merchantId = intent.getStringExtra("merchantId");
            String merchantName = intent.getStringExtra("merchantName");
            String merchantNameEn = intent.getStringExtra("merchantNameEn");
            int paymentType = intent.getIntExtra("paymentType", -2);
            String transTime = intent.getStringExtra("transTime");
            int errorCode = intent.getIntExtra("errorCode", 0);
            final String errorMsg = intent.getStringExtra("errorMsg");
            long balance = intent.getLongExtra("balance", 0);
            int transNum = intent.getIntExtra("transNum", 0);
            long totalAmount = intent.getLongExtra("totalAmount", 0L);

            resultInfo = resultCode + "";
            if (amount != 0) {
                resultInfo = resultInfo + "\n amount:" + amount;
            }
            if (!TextUtils.isEmpty(voucherNo)) {
                resultInfo = resultInfo + "\n voucherNo:" + voucherNo;
            }
            if (!TextUtils.isEmpty(referenceNo)) {
                resultInfo = resultInfo + "\n referenceNo:" + referenceNo;
            }
            if (!TextUtils.isEmpty(batchNo)) {
                resultInfo = resultInfo + "\n batchNo:" + batchNo;
            }
            if (!TextUtils.isEmpty(cardNo)) {
                resultInfo = resultInfo + "\n cardNo:" + cardNo;
            }
            if (!TextUtils.isEmpty(cardType)) {
                resultInfo = resultInfo + "\n cardType:" + cardType;
            }
            if (!TextUtils.isEmpty(issue)) {
                resultInfo = resultInfo + "\n issue:" + issue;
            }
            if (!TextUtils.isEmpty(terminalId)) {
                resultInfo = resultInfo + "\n terminalId:" + terminalId;
            }
            if (!TextUtils.isEmpty(merchantId)) {
                resultInfo = resultInfo + "\n merchantId:" + merchantId;
            }
            if (!TextUtils.isEmpty(merchantName)) {
                resultInfo = resultInfo + "\n merchantName:" + merchantName;
            }
            if (paymentType != -2) {
                resultInfo = resultInfo + "\n paymentType:" + paymentType;
            }
            if (!TextUtils.isEmpty(date)) {
                resultInfo = resultInfo + "\n transDate:" + date;
            }
            if (!TextUtils.isEmpty(transTime)) {
                resultInfo = resultInfo + "\n transTime:" + transTime;
            }
            if (errorCode != 0) {
                resultInfo = resultInfo + "\n errorCode:" + errorCode;
            }
            if (!TextUtils.isEmpty(errorMsg)) {
                resultInfo = resultInfo + "\n errorMsg:" + errorMsg;
            }
            if (balance != 0) {
                resultInfo = resultInfo + "\n balance:" + balance;
            }
            if (!TextUtils.isEmpty(transId)) {
                resultInfo = resultInfo + "\n transId:" + transId;
            }
            if (!TextUtils.isEmpty(merchantNameEn)) {
                resultInfo = resultInfo + "\n merchantNameEn:" + merchantNameEn;
            }
            if (transNum != 0) {
                resultInfo = resultInfo + "\n transNum:" + transNum;
            }
            if (totalAmount != 0) {
                resultInfo = resultInfo + "\n totalAmount:" + totalAmount;
            }
            if (transType != -10) {
                resultInfo = resultInfo + "\n transType:" + transType;
            }

            Log.e(TAG, resultInfo);

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent myIntent = new Intent(context, ResultActivity.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    myIntent.putExtra("resultCode", resultCode);
                    myIntent.putExtra("resultInfo", resultInfo);
                    myIntent.putExtra("errorMsg", errorMsg);
                    context.startActivity(myIntent);
                }

            }, 500);
        }
    }


}
