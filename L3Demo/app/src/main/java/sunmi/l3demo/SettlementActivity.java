package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * @author Created by Lee64 on 2017/8/31.
 */

public class SettlementActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        final CheckBox isPrintCb = (CheckBox) findViewById(R.id.cb_settlement_print);
        final CheckBox isSettlePrintCb = (CheckBox) findViewById(R.id.cb_settlement_detail_print);
        Button ok = (Button) findViewById(R.id.ok_btn);
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent("sunmi.payment.L3");
                intent.putExtra("transType", 7);
                intent.putExtra("appId", getPackageName());
                intent.putExtra("transId", System.currentTimeMillis() + "");
                intent.putExtra("isPrintSettleTicket", isPrintCb.isChecked());
                intent.putExtra("isSettlementDetail", isSettlePrintCb.isChecked());
                startActivity(intent);
            }

        });
    }

}
