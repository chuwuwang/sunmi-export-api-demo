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
 * 打印界面
 *
 * @author Created by Lee64 on 2017/8/31.
 */

public class PrintActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        initView();
    }

    private void initView() {
        final EditText voucherEdit = (EditText) findViewById(R.id.edit_voucher);
        final CheckBox lastPrintCb = (CheckBox) findViewById(R.id.cb_last_print);
        final CheckBox onlyPrintCb = (CheckBox) findViewById(R.id.cb_only_print);
        onlyPrintCb.setChecked(true);
        Button ok = (Button) findViewById(R.id.ok_btn);
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String packageName = getPackageName();
                boolean lastPrint = lastPrintCb.isChecked();
                boolean onlyPrint = onlyPrintCb.isChecked();
                String voucherNo = voucherEdit.getText().toString();
                Intent intent = new Intent("sunmi.payment.L3");
                intent.putExtra("transType", 11);
                intent.putExtra("appId", packageName);
                intent.putExtra("isOnlyPrint", onlyPrint);
                intent.putExtra("isLastTrade", lastPrint);
                intent.putExtra("oriVoucherNo", voucherNo);
                startActivity(intent);
            }

        });
    }


}
