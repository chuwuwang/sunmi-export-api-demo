package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.sm.l3.demo.socket.WebSocketActivity;
import com.sm.l3.demo.socket.WebSocketService;

public class MainActivity extends BaseActivity {

    private Button mBtnConsume;
    private Button mBtnRevoke;
    private Button mBtnReturnGood;
    private Button mBtnPreAuth;
    private Button mBtnSettlement;
    private Button mBtnSignIn;
    private Button mBtnQueryBalance;
    private Button mBtnSystemManager;
    private Button mBtnPrint;
    private Button mBtnInquiryMerchant;
    private Button mBtnSignOut;
    private Button mBtnTradeInquiryOnline;
    private Button mBtnTradeInquiryLocal;
    private Button mBtnCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnSignIn = findViewById(R.id.btn_sign_in);
        mBtnConsume = findViewById(R.id.btn_consume);
        mBtnRevoke = findViewById(R.id.btn_revoke);
        mBtnPreAuth = findViewById(R.id.btn_pre_auth);
        mBtnReturnGood = findViewById(R.id.btn_return_goods);
        mBtnSettlement = findViewById(R.id.btn_settlement);
        mBtnQueryBalance = findViewById(R.id.btn_query_balance);
        mBtnSystemManager = findViewById(R.id.btn_system_manager);
        mBtnPrint = findViewById(R.id.btn_print);
        mBtnInquiryMerchant = findViewById(R.id.btn_query_merchant);
        mBtnSignOut = findViewById(R.id.btn_sign_out);
        mBtnTradeInquiryOnline = findViewById(R.id.btn_query_trade_online);
        mBtnTradeInquiryLocal = findViewById(R.id.btn_query_trade_local);
        mBtnCustom = findViewById(R.id.btn_custom);

        mBtnSettlement.setOnClickListener(this);
        mBtnQueryBalance.setOnClickListener(this);
        mBtnSystemManager.setOnClickListener(this);
        mBtnPrint.setOnClickListener(this);
        mBtnInquiryMerchant.setOnClickListener(this);
        mBtnSignOut.setOnClickListener(this);
        mBtnCustom.setOnClickListener(this);
        mBtnSignIn.setOnClickListener(this);
        mBtnConsume.setOnClickListener(this);
        mBtnRevoke.setOnClickListener(this);
        mBtnPreAuth.setOnClickListener(this);
        mBtnReturnGood.setOnClickListener(this);
        mBtnTradeInquiryOnline.setOnClickListener(this);
        mBtnTradeInquiryLocal.setOnClickListener(this);

        findViewById(R.id.btn_web_socket_setting).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_web_socket_setting) {
            openActivity(WebSocketActivity.class);
            return;
        }

        setEnable(false);
        boolean isOpen = true;

        String packageName = getPackageName();
        String transId = System.currentTimeMillis() + "";

        Intent intent = new Intent(CALL_EXTRA_ACTION);

        if (id == R.id.btn_consume) {
            // 消费
            isOpen = false;
            openActivity(CustomActivity.class);
        } else if (id == R.id.btn_revoke) {
            // 撤销
            isOpen = false;
            openActivity(VoidActivity.class);
        } else if (id == R.id.btn_return_goods) {
            // 退货
            isOpen = false;
            openActivity(RefundActivity.class);
        } else if (id == R.id.btn_pre_auth) {
            // 预授权
            isOpen = false;
            openActivity(PreAuthActivity.class);
        } else if (id == R.id.btn_settlement) {
            // 结算
            isOpen = false;
            openActivity(SettlementActivity.class);
        } else if (id == R.id.btn_sign_in) {
            // 签到
            intent.putExtra("transType", 8);
        } else if (id == R.id.btn_query_balance) {
            // 余额查询
            intent.putExtra("transType", 9);
        } else if (id == R.id.btn_system_manager) {
            // 系统管理
            intent.putExtra("transType", 10);
        } else if (id == R.id.btn_print) {
            // 打印
            isOpen = false;
            openActivity(PrintActivity.class);
        } else if (id == R.id.btn_query_merchant) {
            // 商户信息查询
            intent.putExtra("transType", 13);
        } else if (id == R.id.btn_sign_out) {
            // 签退
            intent.putExtra("transType", 15);
        } else if (id == R.id.btn_query_trade_online) {
            // 联机交易记录查询
            isOpen = false;
            openActivity(InquiryByOnlineActivity.class);
        } else if (id == R.id.btn_query_trade_local) {
            // 本地交易记录查询
            isOpen = false;
            openActivity(InquiryByLocalActivity.class);
        } else if (id == R.id.btn_custom) {
            isOpen = false;
            openActivity(SaleActivity.class);
        }

        if (isOpen) {
            intent.putExtra("transId", transId);
            intent.putExtra("appId", packageName);

            String json = new Gson().toJson(intent);
            Log.e("nsz", "request json:" + json);
            WebSocketService.getInstance().send(json);

            // startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setEnable(true);
    }

    private void setEnable(boolean enable) {
        mBtnSignIn.setEnabled(enable);
        mBtnConsume.setEnabled(enable);
        mBtnRevoke.setEnabled(enable);
        mBtnPreAuth.setEnabled(enable);
        mBtnReturnGood.setEnabled(enable);
        mBtnSettlement.setEnabled(enable);
        mBtnQueryBalance.setEnabled(enable);
        mBtnSystemManager.setEnabled(enable);
        mBtnPrint.setEnabled(enable);
        mBtnInquiryMerchant.setEnabled(enable);
        mBtnSignOut.setEnabled(enable);
        mBtnCustom.setEnabled(enable);
        mBtnTradeInquiryOnline.setEnabled(enable);
        mBtnTradeInquiryLocal.setEnabled(enable);
    }

    private void openActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }


}
