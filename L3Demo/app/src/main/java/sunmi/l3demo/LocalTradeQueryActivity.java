package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 本地交易记录查询
 *
 * @author Created by Lee64 on 2017/8/31.
 */

public class LocalTradeQueryActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_trade_query);
        initView();
    }

    private void initView() {
        final EditText voucherEdit = (EditText) findViewById(R.id.edit_voucher);
        final EditText tradeEdit = (EditText) findViewById(R.id.edit_trade);
        Button button = (Button) findViewById(R.id.btn_ok);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String packageName = getPackageName();
                String transId = tradeEdit.getText().toString();
                String voucherNo = voucherEdit.getText().toString();
                Intent intent = new Intent("sunmi.payment.L3");
                intent.putExtra("transType", 18);
                intent.putExtra("appId", packageName);
                intent.putExtra("transId", transId);
                intent.putExtra("oriVoucherNo", voucherNo);
                startActivity(intent);
            }

        });
    }


}