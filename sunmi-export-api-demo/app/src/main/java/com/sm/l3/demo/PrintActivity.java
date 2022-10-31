package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class PrintActivity extends BaseActivity {

    private EditText mEditVoucher;
    private CheckBox mCheckBoxLastTrade;
    private CheckBox mCheckBoxOnlyPrint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        initView();
    }

    private void initView() {
        mEditVoucher = findViewById(R.id.edit_input_voucher);
        mCheckBoxLastTrade = findViewById(R.id.cb_last_print);
        mCheckBoxOnlyPrint = findViewById(R.id.cb_only_print);

        findViewById(R.id.btn_ok).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean isOnlyPrint = mCheckBoxOnlyPrint.isChecked();
        boolean isLastTrade = mCheckBoxLastTrade.isChecked();
        String oriVoucherNo = mEditVoucher.getText().toString();

        Intent intent = new Intent(CALL_EXTRA_ACTION);

        intent.putExtra("appId", BuildConfig.APPLICATION_ID);

        intent.putExtra("transType", 11);
        intent.putExtra("isOnlyPrint", isOnlyPrint);
        intent.putExtra("isLastTrade", isLastTrade);
        intent.putExtra("oriVoucherNo", oriVoucherNo);

        startActivity(intent);
    }


}
