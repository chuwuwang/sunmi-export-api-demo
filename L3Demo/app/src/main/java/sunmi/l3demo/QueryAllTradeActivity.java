package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 交易查询
 *
 * @author Created by Lee64 on 2017/8/31.
 */

public class QueryAllTradeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_transaction_query);
        final EditText voucherNoEditText = (EditText) findViewById(R.id.input_ori_voucher_edt);
        final EditText lastEditText = (EditText) findViewById(R.id.input_last);
        lastEditText.setVisibility(View.VISIBLE);
        Button button = (Button) findViewById(R.id.btn_ok);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent("sunmi.payment.L3");
                intent.putExtra("transType", 17);
                String transId = System.currentTimeMillis() + "";
                intent.putExtra("transId", transId);
                String packageName = getPackageName();
                intent.putExtra("appId", packageName);

                String oriVoucherNo = voucherNoEditText.getText().toString();
                intent.putExtra("oriVoucherNo", oriVoucherNo);
                int parseInt;
                try {
                    String str = lastEditText.getText().toString();
                    parseInt = Integer.parseInt(str);
                } catch (Exception e) {
                    parseInt = 0;
                }
                intent.putExtra("lastTradeType", parseInt);
                intent.putExtra("isSupportTransactionQuery", true);
                startActivity(intent);
            }

        });
    }


}
