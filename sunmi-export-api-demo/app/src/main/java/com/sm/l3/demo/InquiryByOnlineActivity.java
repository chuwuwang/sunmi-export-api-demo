package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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

        mEditVoucher = findViewById(R.id.edit_input_voucher);
        mEditLastType = findViewById(R.id.edit_input_last_type);
        mCheckBoxLastTrade = findViewById(R.id.cb_last_print);

        findViewById(R.id.btn_ok).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean isLastTrade = mCheckBoxLastTrade.isChecked();
        String oriVoucherNo = mEditVoucher.getText().toString();
        String lastTradeType = mEditLastType.getText().toString();

        Intent intent = new Intent(CALL_EXTRA_ACTION);

        intent.putExtra("appId", BuildConfig.APPLICATION_ID);

        intent.putExtra("transType", 16);
        intent.putExtra("oriVoucherNo", oriVoucherNo);
        try {
            int value = Integer.parseInt(lastTradeType);
            intent.putExtra("lastTradeType", value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        intent.putExtra("isLastTrade", isLastTrade);

        intent.putExtra("isShowResult", false);

        intent = addUserCustomTicketContent(intent);

        startActivity(intent);
    }


}
