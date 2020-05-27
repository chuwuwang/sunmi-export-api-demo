package com.sm.l3.demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;

public class LZActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.pos.router.payment.ACTION_RESULT");
        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            boolean equals = TextUtils.equals(action, "com.pos.router.payment.ACTION_RESULT");
            if (equals) {
                int resultCode = intent.getIntExtra("resultCode", -1);
                String errorMsg = intent.getStringExtra("errorMsg");
                String answerCode = intent.getStringExtra("answerCode");
                int transactionPlatform = intent.getIntExtra("transactionPlatform", -1);
                int qrCodeTransactionState = intent.getIntExtra("qrCodeTransactionState", -1);
                if (resultCode == 0 && TextUtils.equals(answerCode, "00") &&
                        transactionPlatform == 0) {
                    // card payment is successful
                } else if (resultCode == 0 && TextUtils.equals(answerCode, "00") &&
                        transactionPlatform != 0 && qrCodeTransactionState == 1) {
                    // e-wallet payment is successful
                } else {
                    // failed
                }
            }
        }

    };

    private void sale() {
        Intent intent = new Intent("com.pos.router.payment.ACTION_PAY");
        intent.putExtra("appId", BuildConfig.APPLICATION_ID);
        intent.putExtra("amount", 1L);
        intent.putExtra("transType", 0);
        intent.putExtra("paymentType", 0);
        intent.putExtra("transId", "P0123456789");
        startActivity(intent);
    }

}