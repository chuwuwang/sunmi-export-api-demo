package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author xurong on 2017/5/15.
 */

public class ResultActivity extends Activity {

    private static final String TAG = "ResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView tvResult = (TextView) findViewById(R.id.tv_result);

        Intent intent = getIntent();
        StringBuilder sb = new StringBuilder();

        int code = intent.getIntExtra("resultCode", -99999);
        if (code == 0) {
            sb.append("交易成功, 具体信息请查看控制台的Log \n");
            Toast.makeText(this, "交易成功, 具体信息请查看控制台的Log", Toast.LENGTH_SHORT).show();
        } else {
            sb.append("交易失败, 具体信息请查看控制台的Log \n");
            Toast.makeText(this, "交易失败, 具体信息请查看控制台的Log", Toast.LENGTH_SHORT).show();
        }

        sb.append("transType: " + intent.getIntExtra("transType", -99999) + "\n");
        sb.append("appId: " + intent.getStringExtra("appId") + "\n");
        sb.append("resultCode: " + intent.getIntExtra("resultCode", -99999) + "\n");
        sb.append("errorCode: " + intent.getIntExtra("errorCode", -99999) + "\n");
        sb.append("errorMsg: " + intent.getStringExtra("errorMsg") + "\n");
        sb.append("model: " + intent.getStringExtra("model") + "\n");
        sb.append("version: " + intent.getStringExtra("version") + "\n");
        sb.append("tusn: " + intent.getStringExtra("tusn") + "\n");
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
        sb.append("paymentType: " + intent.getIntExtra("paymentType", -99999) + "\n");
        sb.append("answerCode: " + intent.getStringExtra("answerCode") + "\n");
        sb.append("transactionType: " + intent.getIntExtra("transactionType", -99999) + "\n");
        sb.append("transactionPlatform: " + intent.getIntExtra("transactionPlatform", -99999) + "\n");
        sb.append("qrCodeScanModel: " + intent.getIntExtra("qrCodeScanModel", -99999) + "\n");
        sb.append("qrCodeTransactionState: " + intent.getIntExtra("qrCodeTransactionState", -99999) + "\n");

        String result = sb.toString();
        Log.e(TAG, "result: " + result);

        tvResult.setText(result);

    }


}
