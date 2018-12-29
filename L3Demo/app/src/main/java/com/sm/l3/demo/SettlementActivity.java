package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SettlementActivity extends BaseActivity {

    private CheckBox mCbPrint;
    private CheckBox mCbDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        initView();
    }

    private void initView() {
        mCbPrint = (CheckBox) findViewById(R.id.cb_print);
        mCbDetail = (CheckBox) findViewById(R.id.cb_detail);
        Button ok = (Button) findViewById(R.id.btn_ok);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("sunmi.payment.L3");
        intent.putExtra("transType", 7);
        intent.putExtra("transId", System.currentTimeMillis() + "");
        intent.putExtra("appId", getPackageName());
        intent.putExtra("isSettlementTicket", mCbPrint.isChecked());
        intent.putExtra("isSettlementDetail", mCbDetail.isChecked());
        startActivity(intent);
    }

}
