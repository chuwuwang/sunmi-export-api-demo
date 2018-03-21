package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * 结算界面
 *
 * @author Created by Lee64 on 2017/8/31.
 */

public class SettlementActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        final CheckBox isPrintCb = (CheckBox) findViewById(R.id.cb_settlement_print);
        final CheckBox isSettlePrintCb = (CheckBox) findViewById(R.id.cb_settlement_detail_print);
        Button ok = (Button) findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent("sunmi.payment.L3");
                intent.putExtra("transType", 7);
                intent.putExtra("transId", "Sunmi_123456789");
                intent.putExtra("appId", getPackageName());
                intent.putExtra("isPrintTicket", isPrintCb.isChecked());
                intent.putExtra("isSettlementDetail", isSettlePrintCb.isChecked());
                startActivity(intent);
            }

        });
    }

}
