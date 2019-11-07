package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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
        mCbPrint = findViewById(R.id.cb_print);
        mCbDetail = findViewById(R.id.cb_detail);
        findViewById(R.id.btn_ok).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean isSettlementTicket = mCbPrint.isChecked();
        boolean isSettlementDetail = mCbDetail.isChecked();

        Intent intent = new Intent(CALL_EXTRA_ACTION);

        intent.putExtra("appId", BuildConfig.APPLICATION_ID);

        intent.putExtra("transType", 7);
        intent.putExtra("isSettlementTicket", isSettlementTicket);
        intent.putExtra("isSettlementDetail", isSettlementDetail);
        startActivity(intent);
    }


}
