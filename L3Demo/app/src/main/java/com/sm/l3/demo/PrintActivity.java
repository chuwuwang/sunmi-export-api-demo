package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.sm.l3.demo.socket.WebSocketService;

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
        Button ok = findViewById(R.id.btn_ok);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String packageName = getPackageName();
        boolean isOnlyPrint = mCheckBoxOnlyPrint.isChecked();
        boolean isLastTrade = mCheckBoxLastTrade.isChecked();
        String oriVoucherNo = mEditVoucher.getText().toString();

        Intent intent = new Intent(CALL_EXTRA_ACTION);
        intent.putExtra("transType", 11);
        intent.putExtra("appId", packageName);

        intent.putExtra("isOnlyPrint", isOnlyPrint);
        intent.putExtra("isLastTrade", isLastTrade);
        intent.putExtra("oriVoucherNo", oriVoucherNo);

        String json = new Gson().toJson(intent);
        WebSocketService.getInstance().send(json);

        // startActivity(intent);
    }


}
