package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * @author Created by Lee64 on 2017/8/31.
 */

public class PrintActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        final EditText editText = (EditText) findViewById(R.id.input_ori_voucher_edt);
        final CheckBox isLastPrintCb = (CheckBox) findViewById(R.id.cb_last_print);
        Button ok = (Button) findViewById(R.id.ok_btn);
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent("sunmi.payment.L3");
                intent.putExtra("transType", 11);
                intent.putExtra("appId", getPackageName());
                intent.putExtra("transId", System.currentTimeMillis() + "");
                intent.putExtra("isLastTrade", isLastPrintCb.isChecked());
                intent.putExtra("oriVoucherNo", editText.getText().toString());
                startActivity(intent);
            }

        });
    }

}
