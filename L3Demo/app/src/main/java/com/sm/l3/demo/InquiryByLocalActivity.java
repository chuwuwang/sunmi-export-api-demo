package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.sm.l3.demo.socket.WebSocketService;

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
        Button button = findViewById(R.id.btn_ok);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String packageName = getPackageName();
        String transId = mEditTransId.getText().toString();
        String oriVoucherNo = mEditVoucher.getText().toString();

        Intent intent = new Intent(CALL_EXTRA_ACTION);
        intent.putExtra("transType", 18);
        intent.putExtra("transId", transId);
        intent.putExtra("appId", packageName);

        intent.putExtra("oriVoucherNo", oriVoucherNo);

        String json = new Gson().toJson(intent);
        WebSocketService.getInstance().send(json);

        // startActivity(intent);
    }


}