package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

public class InquiryByLocalActivity extends BaseActivity {

    private EditText mEditVoucher;
    private EditText mEditTransId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_local);
        initView();
    }

    private void initView() {
        mEditVoucher = findViewById(R.id.edit_input_voucher);
        mEditTransId = findViewById(R.id.edit_input_trade);

        findViewById(R.id.btn_ok).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String transId = mEditTransId.getText().toString();
        String oriVoucherNo = mEditVoucher.getText().toString();

        Intent intent = new Intent(CALL_EXTRA_ACTION);

        intent.putExtra("appId", BuildConfig.APPLICATION_ID);

        intent.putExtra("transId", transId);

        intent.putExtra("transType", 18);
        intent.putExtra("oriVoucherNo", oriVoucherNo);

        startActivity(intent);
    }


}