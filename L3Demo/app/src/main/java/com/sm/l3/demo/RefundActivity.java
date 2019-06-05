package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.sm.l3.demo.socket.WebSocketService;

public class RefundActivity extends BaseActivity {

    private RadioGroup mRadioGroup;
    private EditText mEditTransId;
    private EditText mEditReference;
    private EditText mEditDate;
    private EditText mEditAmount;
    private CheckBox mCheckBoxManagePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);
        initView();
    }

    private void initView() {
        mRadioGroup = findViewById(R.id.radio_group);
        mEditTransId = findViewById(R.id.edit_input_trans_id);
        mEditReference = findViewById(R.id.edit_input_reference);
        mEditDate = findViewById(R.id.edit_input_date);
        mEditAmount = findViewById(R.id.edit_input_money);
        mCheckBoxManagePwd = findViewById(R.id.cb_manage_pwd);
        Button okBtn = findViewById(R.id.btn_ok);
        okBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int paymentType = 0;
        int buttonId = mRadioGroup.getCheckedRadioButtonId();
        switch (buttonId) {
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

        String packageName = getPackageName();
        String transId = mEditTransId.getText().toString();

        String amount = mEditAmount.getText().toString();
        String oriTransDate = mEditDate.getText().toString();
        boolean isManagePwd = mCheckBoxManagePwd.isChecked();
        String referenceNo = mEditReference.getText().toString();

        Intent intent = new Intent(CALL_EXTRA_ACTION);
        intent.putExtra("transType", 2);
        intent.putExtra("transId", transId);
        intent.putExtra("appId", packageName);

        intent.putExtra("paymentType", paymentType);
        intent.putExtra("isManagePwd", isManagePwd);

        if (paymentType != 0) {
            intent.putExtra("oriQROrderNo", referenceNo);
        } else {
            intent.putExtra("oriReferenceNo", referenceNo);
        }
        try {
            long aLong = Long.parseLong(amount);
            intent.putExtra("amount", aLong);
        } catch (Exception e) {
            e.printStackTrace();
        }
        intent.putExtra("oriTransDate", oriTransDate);

        intent = addUserCustomTicketContent(intent);

        String json = new Gson().toJson(intent);
        WebSocketService.getInstance().send(json);

        // startActivity(intent);
    }


}
