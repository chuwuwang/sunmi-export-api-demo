package com.sm.l3.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Set;

public class ResultReceiver extends BroadcastReceiver {

    private static final String TAG = "ResultReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Set<String> keySet = intent.getExtras().keySet();
            for (String key : keySet) {
                String temp;
                Object obj = intent.getExtras().get(key);
                if (obj != null) {
                    String value = obj.toString();
                    temp = "key = " + key + " || value = " + value;
                } else {
                    temp = "key = " + key + " || value = null";
                }
                Log.e(TAG, temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        intent.setClass(context, ResultActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}