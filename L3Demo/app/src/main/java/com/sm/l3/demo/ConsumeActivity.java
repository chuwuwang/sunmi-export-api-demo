package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ConsumeActivity extends BaseActivity {

    private RadioGroup mRadioGroup;
    private EditText mEditAmount;
    private EditText mEditTransId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume);
        initView();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mEditAmount = (EditText) findViewById(R.id.edit_input_money);
        mEditTransId = (EditText) findViewById(R.id.edit_input_trans_id);
        findViewById(R.id.btn_ok).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int paymentType;
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
            default:
                paymentType = -1;
                break;
        }
        Intent intent = new Intent(CALL_EXTRA_ACTION);
        Bundle bundle = new Bundle();
        bundle.putInt("transType", 0);
        bundle.putInt("paymentType", paymentType);
        bundle.putString("appId", getPackageName() + "");

        String transId = mEditTransId.getText().toString();
        if (transId.trim().length() > 0) {
            bundle.putString("transId", transId);
        } else {
            bundle.putString("transId", System.currentTimeMillis() + "");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("payType", 0);
        map.put("username", "zh123");
        String toJson = new Gson().toJson(map);
        // bundle.putString("reserve", "{\"payType\":0}");

        try {
            String str = mEditAmount.getText().toString();
            long aLong = Long.parseLong(str);
            bundle.putLong("amount", aLong);
        } catch (Exception e) {
            e.printStackTrace();
        }

        intent.putExtras(bundle);

        // 添加用户自定义小票内容
        intent = addUserCustomTicketContent(intent);
        startActivity(intent);
    }


}
