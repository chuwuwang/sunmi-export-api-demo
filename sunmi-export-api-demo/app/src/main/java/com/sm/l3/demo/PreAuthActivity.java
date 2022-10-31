package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class PreAuthActivity extends BaseActivity {

    private RadioGroup mRadioGroup;
    private EditText mEditVoucher;
    private EditText mEditMoney;
    private EditText mEditDate;
    private EditText mEditAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initView();
    }

    private void initView() {
        mRadioGroup = findViewById(R.id.radio_group);
        mEditDate = findViewById(R.id.edit_input_date);
        mEditAuth = findViewById(R.id.edit_input_auth);
        mEditMoney = findViewById(R.id.edit_input_money);
        mEditVoucher = findViewById(R.id.edit_input_voucher);

        Button ok = findViewById(R.id.btn_ok);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int transType = 3;
        int buttonId = mRadioGroup.getCheckedRadioButtonId();
        switch (buttonId) {
            case R.id.rb_pre_auth:
                transType = 3;
                break;
            case R.id.rb_pre_revoke:
                transType = 4;
                break;
            case R.id.rb_pre_complete:
                transType = 5;
                break;
            case R.id.rb_pre_complete_revoke:
                transType = 6;
                break;
        }
        long amount = 0;
        try {
            String text = mEditMoney.getText().toString();
            amount = Long.parseLong(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String oriAuthNo = mEditAuth.getText().toString();
        String oriTransDate = mEditDate.getText().toString();
        String oriVoucherNo = mEditVoucher.getText().toString();

        Intent intent = new Intent(CALL_EXTRA_ACTION);

        intent.putExtra("appId", BuildConfig.APPLICATION_ID);

        intent.putExtra("transType", transType);

        intent.putExtra("amount", amount);
        intent.putExtra("oriAuthNo", oriAuthNo);
        intent.putExtra("oriTransDate", oriTransDate);
        intent.putExtra("oriVoucherNo", oriVoucherNo);

        intent = addPaymentChannel(intent);
        intent = addUserCustomTicketContent(intent);

        startActivity(intent);
    }


}
