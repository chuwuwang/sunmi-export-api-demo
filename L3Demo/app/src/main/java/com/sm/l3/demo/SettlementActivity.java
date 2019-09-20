package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.gson.Gson;
import com.sm.l3.demo.socket.WebSocketService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        Button ok = findViewById(R.id.btn_ok);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String packageName = getPackageName();
        String transId = System.currentTimeMillis() + "";

        boolean isSettlementTicket = mCbPrint.isChecked();
        boolean isSettlementDetail = mCbDetail.isChecked();

        Intent intent = new Intent(CALL_EXTRA_ACTION);
        intent.putExtra("transType", 7);
        intent.putExtra("transId", transId);
        intent.putExtra("appId", packageName);

        intent.putExtra("isSettlementTicket", isSettlementTicket);
        intent.putExtra("isSettlementDetail", isSettlementDetail);

        Map<String, Object> map = new HashMap<>();
        try {
            Set<String> keySet = intent.getExtras().keySet();
            for (String key : keySet) {
                Object obj = intent.getExtras().get(key);
                map.put(key, obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String json = new Gson().toJson(map);
        WebSocketService.getInstance().send(json);

        // startActivity(intent);
    }


}
