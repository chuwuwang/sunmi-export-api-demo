package com.sm.l3.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ResultReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        intent.setClass(context, ResultActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
