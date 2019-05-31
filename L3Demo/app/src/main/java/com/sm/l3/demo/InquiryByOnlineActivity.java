package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class InquiryByOnlineActivity extends BaseActivity {

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
        String packageName = getPackageName();
        String transId = System.currentTimeMillis() + "";

        boolean isLastTrade = mCheckBoxLastTrade.isChecked();
        String oriVoucherNo = mEditVoucher.getText().toString();
        String lastTradeType = mEditLastType.getText().toString();

        Intent intent = new Intent(CALL_EXTRA_ACTION);
        intent.putExtra("transType", 16);
        intent.putExtra("transId", transId);
        intent.putExtra("appId", packageName);

        intent.putExtra("oriVoucherNo", oriVoucherNo);

        try {
            int parseInt = Integer.parseInt(lastTradeType);
            intent.putExtra("lastTradeType", parseInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        intent.putExtra("isLastTrade", isLastTrade);

        intent = addUserCustomTicketContent(intent);

        startActivity(intent);
    }


}
