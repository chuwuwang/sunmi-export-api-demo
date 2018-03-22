package sunmi.l3demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * 交易查询
 *
 * @author Created by Lee64 on 2017/8/31.
 */

public class QueryByOnlineActivity extends BaseActivity {

    private EditText mEditVoucher;
    private EditText mEditLastType;
    private CheckBox mCheckBoxLastTrade;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_online);
        mEditVoucher = (EditText) findViewById(R.id.edit_input_voucher);
        mEditLastType = (EditText) findViewById(R.id.edit_input_last_type);
        mCheckBoxLastTrade = (CheckBox) findViewById(R.id.cb_last_print);
        Button button = (Button) findViewById(R.id.btn_ok);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("sunmi.payment.L3");
        intent.putExtra("transType", 16);
        intent.putExtra("appId", getPackageName());
        intent.putExtra("transId", System.currentTimeMillis() + "");
        intent.putExtra("oriVoucherNo", mEditVoucher.getText().toString());
        try {
            int parseInt = Integer.parseInt(mEditLastType.getText().toString());
            intent.putExtra("lastTradeType", parseInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        intent.putExtra("isLastTrade", mCheckBoxLastTrade.isChecked());

        // 添加用户自定义小票内容
        intent = addUserCustomTicketContent(intent);

        startActivity(intent);
    }


}
