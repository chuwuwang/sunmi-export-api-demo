package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QueryByLocalActivity extends BaseActivity {

    private EditText mEditVoucher;
    private EditText mEditTransId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_local);
        initView();
    }

    private void initView() {
        mEditVoucher = (EditText) findViewById(R.id.edit_input_voucher);
        mEditTransId = (EditText) findViewById(R.id.edit_input_trade);
        Button button = (Button) findViewById(R.id.btn_ok);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("sunmi.payment.L3");
        intent.putExtra("transType", 18);
        intent.putExtra("appId", getPackageName());
        intent.putExtra("transId", mEditTransId.getText().toString());
        intent.putExtra("oriVoucherNo", mEditVoucher.getText().toString());
        startActivity(intent);
    }

}