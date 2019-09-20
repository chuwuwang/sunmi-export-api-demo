package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.sm.l3.demo.socket.UsbDeviceService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SaleActivity extends BaseActivity {

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

        String packageName = getPackageName();
        String transId = mEditTransId.getText().toString();

        Intent intent = new Intent(CALL_EXTRA_ACTION);
        Bundle bundle = new Bundle();
        bundle.putInt("transType", 0);
        bundle.putString("transId", transId);
        bundle.putString("appId", packageName);
        bundle.putInt("paymentType", paymentType);

        try {
            String amount = mEditAmount.getText().toString();
            long aLong = Long.parseLong(amount);
            bundle.putLong("amount", aLong);
        } catch (Exception e) {
            e.printStackTrace();
        }

        intent.putExtras(bundle);

        // 添加用户自定义小票内容
        intent = addUserCustomTicketContent(intent);

        Map<String, Object> map = new HashMap<>();
        try {
            Set<String> keySet = intent.getExtras().keySet();
            for (String key : keySet) {
                Object obj = intent.getExtras().get(key);
                if (obj != null) {
                    String value = obj.toString();
                    Log.e("nsz", "key = " + key + " || value = " + value);
                } else {
                    Log.e("nsz", "key = " + key + " || value = null");
                }
                map.put(key, obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String json = new Gson().toJson(map);
        Log.e("nsz", json);
        UsbDeviceService.send(json);

        // startActivity(intent);
    }


}
