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
        Button button = (Button) findViewById(R.id.ok_btn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent("sunmi.payment.L3");
                intent.putExtra("transId", System.currentTimeMillis() + "");
                intent.putExtra("transType", 17);
                intent.putExtra("appId", getPackageName());
                intent.putExtra("oriVoucherNo", voucherNoEditText.getText().toString());
                int parseInt;
                try {
                    parseInt = Integer.parseInt(lastEditText.getText().toString());
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
