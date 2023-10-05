package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class SaleActivity extends BaseActivity {

    private EditText mEditAmount;
    private EditText mEditTransId;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        initView();
    }

    private void initView() {
        mRadioGroup = findViewById(R.id.radio_group);
        mEditAmount = findViewById(R.id.edit_input_money);
        mEditTransId = findViewById(R.id.edit_input_trans_id);

        findViewById(R.id.btn_ok).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int paymentType = -1;
        int buttonId = mRadioGroup.getCheckedRadioButtonId();
        switch (buttonId) {
            case R.id.rb_user_optional:
                paymentType = -1;
                break;
            case R.id.rb_bank_card:
                paymentType = 0;
                break;
            case R.id.rb_aliPay_scan:
                paymentType = 1;
                break;
            case R.id.rb_aliPay_code:
                paymentType = 2;
                break;
            case R.id.rb_weChat_scan:
                paymentType = 3;
                break;
            case R.id.rb_weChat_code:
                paymentType = 4;
                break;
            case R.id.rb_union_scan:
                paymentType = 5;
                break;
            case R.id.rb_union_code:
                paymentType = 6;
                break;
            case R.id.rb_scan_and_scan:
                paymentType = 7;
                break;
        }
        long amount = 0;
        try {
            String text = mEditAmount.getText().toString();
            amount = Long.parseLong(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String transId = mEditTransId.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("appId", BuildConfig.APPLICATION_ID);
        intent.putExtra("amount", amount);
        intent.putExtra("transId", transId);
        intent.putExtra("transType", 0);
        intent.putExtra("paymentType", paymentType);
        intent = addPaymentChannel(intent);
        intent = addUserCustomTicketContent(intent);
        startActivity(intent);
    }

}
