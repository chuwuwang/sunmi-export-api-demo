package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
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
        mEditVoucher = (EditText) findViewById(R.id.edit_input_voucher);
        mCheckBoxLastTrade = (CheckBox) findViewById(R.id.cb_last_print);
        mCheckBoxOnlyPrint = (CheckBox) findViewById(R.id.cb_only_print);
        Button ok = (Button) findViewById(R.id.btn_ok);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("sunmi.payment.L3");
        intent.putExtra("transType", 11);
        intent.putExtra("appId", getPackageName());
        intent.putExtra("isOnlyPrint", mCheckBoxOnlyPrint.isChecked());
        intent.putExtra("isLastTrade", mCheckBoxLastTrade.isChecked());
        intent.putExtra("oriVoucherNo", mEditVoucher.getText().toString());
        startActivity(intent);
    }


}
