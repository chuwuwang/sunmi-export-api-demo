package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 扫码交易查询
 *
 * @author Created by Lee64 on 2017/8/31.
 */

public class QrCodeTransactionQueryActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_transaction_query);
        final EditText editText = (EditText) findViewById(R.id.input_ori_voucher_edt);
        Button button = (Button) findViewById(R.id.btn_ok);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent("sunmi.payment.L3");
                intent.putExtra("transType", 16);
                String transId = System.currentTimeMillis() + "";
                intent.putExtra("transId", transId);
                String packageName = getPackageName();
                intent.putExtra("appId", packageName);
                String oriVoucherNo = editText.getText().toString();
                intent.putExtra("oriVoucherNo", oriVoucherNo);
                startActivity(intent);
            }

        });
    }


}
